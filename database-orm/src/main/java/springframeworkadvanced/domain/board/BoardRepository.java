package springframeworkadvanced.domain.board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    Board save(Board board);
    Optional<Board> findById(Long id);
    void deleteById(Long id);
    List<Board> findAll();
    long count();
}
