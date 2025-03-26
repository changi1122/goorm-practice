package springframeworkadvanced.web.board.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springframeworkadvanced.domain.board.Board;
import springframeworkadvanced.domain.model.DetailsUser;
import springframeworkadvanced.web.board.dto.BoardRequestDto;
import springframeworkadvanced.web.board.service.BoardService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/board")
    public String createBoard(@Valid BoardRequestDto post, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.post",
                    bindingResult
            );
            redirectAttributes.addFlashAttribute("post", post);
            return "redirect:/board/new";
        }

        Board createdPost = boardService.create(post, LocalDateTime.now());
        return "redirect:/board/" + createdPost.getId();
    }

    @PostMapping("/board/{id}")
    public String updateBoard(@PathVariable Long id, @Valid BoardRequestDto post, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.post",
                    bindingResult
            );
            redirectAttributes.addFlashAttribute("post", post);
            return "redirect:/board/" + id + "/edit";
        }

        Board updatedPost = boardService.update(id, post, LocalDateTime.now());
        return "redirect:/board/" + updatedPost.getId();
    }


    @GetMapping(value = { "/", "/board" })
    public String listBoard(Model model/*, @AuthenticationPrincipal DetailsUser user*/) {
        List<Board> boardList = boardService.list();
        model.addAttribute(boardList);

        return "board/board";
    }

    @GetMapping("/board/{id}")
    public String readBoard(@PathVariable Long id, Model model) {
        Board post = boardService.read(id);
        model.addAttribute("post", post);

        return "board/boardView";
    }

    @DeleteMapping("/board/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
    }


    @GetMapping("/board/new")
    public String boardForm(Model model) {

        if (!model.containsAttribute("post"))
            model.addAttribute("post", new Board());

        model.addAttribute("pageTitle", "새로운 글쓰기");
        model.addAttribute("action", "/board");

        return "board/boardForm";
    }

    @GetMapping("/board/{id}/edit")
    public String boardEditForm(@PathVariable Long id, Model model) {

        Board post = boardService.read(id);
        model.addAttribute("post", post);

        model.addAttribute("pageTitle", "글 수정");
        model.addAttribute("action", "/board/" + id);

        return "board/boardForm";
    }
}
