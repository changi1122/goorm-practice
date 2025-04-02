package databaseorm.web.board.service;

import databaseorm.domain.board.Board;
import databaseorm.domain.board.dao.BoardMapper;
import databaseorm.domain.board.dao.BoardRepository;
import databaseorm.domain.user.User;
import databaseorm.web.board.dto.BoardRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Primary
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceHybridImpl implements BoardService {

    private BoardRepository boardRepository;
    private BoardMapper boardMapper;

    @Override
    @Transactional
    public Board create(BoardRequestDto request, LocalDateTime now, User author) {
        Board post = request.toEntity();
        post.setCreatedAt(now);
        post.setAuthor(author);
        return boardRepository.save(post);
    }

    @Override
    @Transactional
    public Board update(Long id, BoardRequestDto request, LocalDateTime now, User author) {
        Board post = boardMapper.selectBoardById(id);

        if (!post.getAuthor().equals(author))
            throw new RuntimeException("글 작성자가 아님");

        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setCategory(request.getCategory());
        post.setLastEditedAt(now);

        return boardRepository.save(post);
    }

    @Override
    @Transactional
    public void delete(Long id, User author) {
        Board post = boardMapper.selectBoardById(id);
        if (!post.getAuthor().equals(author))
            throw new RuntimeException("글 작성자가 아님");

        boardRepository.deleteById(id);
    }

    @Override
    public Board read(Long id) {
        return boardMapper.selectBoardById(id);
    }

    @Override
    public List<Board> list() {
        return boardMapper.selectBoardAll();
    }

    @Override
    public List<Board> listByCategory(String category) {
        return boardMapper.selectBoardByCategory(category);
    }

    @Override
    public Long count() {
        return boardMapper.count();
    }
}
