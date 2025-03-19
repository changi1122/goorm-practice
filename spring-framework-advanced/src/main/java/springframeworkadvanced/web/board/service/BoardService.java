package springframeworkadvanced.web.board.service;


import springframeworkadvanced.domain.board.Board;
import springframeworkadvanced.web.board.dto.BoardRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardService {

    Board create(BoardRequestDto request, LocalDateTime now);

    Board update(Long id, BoardRequestDto request, LocalDateTime now);

    void delete(Long id);

    Board read(Long id);

    List<Board> list();

    Long count();
}
