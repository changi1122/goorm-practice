package springframeworkadvanced.web.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springframeworkadvanced.domain.board.Board;
import springframeworkadvanced.domain.board.BoardRepository;
import springframeworkadvanced.web.board.dto.BoardRequestDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board create(BoardRequestDto request, LocalDateTime now) {
        Board post = request.toEntity();
        post.setCreatedAt(now);

        return boardRepository.save(post);
    }

    @Override
    public Board update(Long id, BoardRequestDto request, LocalDateTime now) {
        Board post = boardRepository.findById(id).orElseThrow();

        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setCategory(request.getCategory());
        post.setLastEditedAt(now);

        return boardRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public Board read(Long id) {
        return boardRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Board> list() {
        return boardRepository.findAll();
    }

    @Override
    public Long count() {
        return boardRepository.count();
    }
}
