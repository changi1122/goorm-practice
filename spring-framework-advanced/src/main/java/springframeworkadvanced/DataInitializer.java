package springframeworkadvanced;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframeworkadvanced.domain.board.Board;
import springframeworkadvanced.domain.board.BoardRepository;

import java.time.LocalDateTime;

/*
 * 실행시 게시판 샘플 데이터 추가하는 컴포넌트
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final BoardRepository boardRepository;

    @Autowired
    public DataInitializer(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        boardRepository.save(createBoard("글 제목 1"));
        boardRepository.save(createBoard("글 제목 2"));
        boardRepository.save(createBoard("글 제목 3"));
        boardRepository.save(createBoard("글 제목 4"));
    }

    private Board createBoard(String title) {
        return new Board(
                null,
                title,
                "<p>hello world</p>",
                "카테고리",
                LocalDateTime.of(2025, 3, 19, 13, 10)
        );
    }
}
