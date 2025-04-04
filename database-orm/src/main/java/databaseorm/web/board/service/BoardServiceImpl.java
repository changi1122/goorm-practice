package databaseorm.web.board.service;

import databaseorm.domain.board.dao.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import databaseorm.domain.board.Board;
import databaseorm.domain.user.User;
import databaseorm.web.board.dto.BoardRequestDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

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
            throw new AccessDeniedException("글 작성자가 아님");

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
            throw new AccessDeniedException("글 작성자가 아님");

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
    public List<Board> listByCategory(String category) {
        return boardRepository.findByCategory(category);
    }

    @Override
    public Long count() {
        return boardRepository.count();
    }
}
