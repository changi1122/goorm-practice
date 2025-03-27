package springframeworkadvanced.web.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springframeworkadvanced.domain.board.Board;
import springframeworkadvanced.domain.board.BoardRepository;
import springframeworkadvanced.domain.user.User;
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
    public Board create(BoardRequestDto request, LocalDateTime now, User author) {
        Board post = request.toEntity();
        post.setCreatedAt(now);
        post.setAuthor(author);

        return boardRepository.save(post);
    }

    @Override
    public Board update(Long id, BoardRequestDto request, LocalDateTime now, User author) {
        Board post = boardRepository.findById(id).orElseThrow();

        if (!post.getAuthor().equals(author))
            throw new RuntimeException("글 작성자가 아님");

        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setCategory(request.getCategory());
        post.setLastEditedAt(now);

        return boardRepository.save(post);
    }

    @Override
    public void delete(Long id, User author) {
        Board post = boardRepository.findById(id).orElseThrow();
        if (!post.getAuthor().equals(author))
            throw new RuntimeException("글 작성자가 아님");

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
