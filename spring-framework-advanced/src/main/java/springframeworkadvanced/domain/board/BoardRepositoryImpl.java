package springframeworkadvanced.domain.board;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    private Long sequence = 1L;
    private Map<Long, Board> boardMap = new HashMap<>();

    @Override
    public Board save(Board board) {
        board.setId(sequence++);
        boardMap.put(board.getId(), board);
        return board;
    }

    @Override
    public Optional<Board> findById(Long id) {
        Board board = boardMap.get(id);
        return Optional.ofNullable(board);
    }

    @Override
    public void deleteById(Long id) {
        boardMap.remove(id);
    }

    @Override
    public List<Board> findAll() {
        List<Board> result = new ArrayList<>();
        boardMap.forEach((k, v) -> {
            result.add(v);
        });
        return result;
    }

    @Override
    public long count() {
        return boardMap.size();
    }
}
