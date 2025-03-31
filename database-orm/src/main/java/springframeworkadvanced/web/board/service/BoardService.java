package springframeworkadvanced.web.board.service;


import springframeworkadvanced.domain.board.Board;
import springframeworkadvanced.domain.user.User;
import springframeworkadvanced.web.board.dto.BoardRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardService {

    Board create(BoardRequestDto request, LocalDateTime now, User author);

    Board update(Long id, BoardRequestDto request, LocalDateTime now, User author);

    void delete(Long id, User author);

    Board read(Long id);

    List<Board> list();

    Long count();
}
