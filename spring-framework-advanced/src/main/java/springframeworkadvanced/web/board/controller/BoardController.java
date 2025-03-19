package springframeworkadvanced.web.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
