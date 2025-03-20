package springframeworkadvanced.web.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springframeworkadvanced.domain.board.Board;
import springframeworkadvanced.web.board.service.BoardService;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = { "/", "/board" })
    public String listBoard(Model model) {
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

    @GetMapping("/board/new")
    public String boardForm(Model model) {
        Board post = new Board();

        post.setTitle("wefwef");
        post.setCategory("wefwefewf");
        post.setBody("wlkfejlwekjflwef");

        model.addAttribute("pageTitle", "새로운 글쓰기");
        model.addAttribute("action", "/board");
        model.addAttribute("post", post);

        return "board/boardForm";
    }
}
