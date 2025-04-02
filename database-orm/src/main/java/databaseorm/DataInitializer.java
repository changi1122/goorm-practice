package databaseorm;

import databaseorm.domain.board.dao.BoardRepository;
import databaseorm.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import databaseorm.domain.board.Board;
import databaseorm.domain.common.UserRole;
import databaseorm.domain.user.User;

import java.time.LocalDateTime;

/*
 * 실행시 게시판 샘플 데이터 추가하는 컴포넌트
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserRepository userRepository, BoardRepository boardRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        User user01 = userRepository.findByLoginId("user01").orElseGet(
                () -> userRepository.save(createUser("user01", "pass01"))
        );
        User user02 = userRepository.findByLoginId("user02").orElseGet(
                () -> userRepository.save(createUser("user02", "pass02"))
        );

        if (!boardRepository.existsByTitle("글 제목 1"))
            boardRepository.save(createBoard("글 제목 1", user01));

        if (!boardRepository.existsByTitle("글 제목 2"))
            boardRepository.save(createBoard("글 제목 2", user01));

        if (!boardRepository.existsByTitle("글 제목 3"))
            boardRepository.save(createBoard("글 제목 3", user02));

        if (!boardRepository.existsByTitle("글 제목 4"))
            boardRepository.save(createBoard("글 제목 4", user02));
    }

    private User createUser(String loginId, String password) {
        User user = new User();
        user.setLoginId(loginId);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(loginId);
        user.setUserRole(UserRole.USER);
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    private Board createBoard(String title, User author) {
        return new Board(
                null,
                title,
                "<p>hello world</p>",
                "카테고리",
                author,
                LocalDateTime.of(2025, 3, 19, 13, 10)
        );
    }
}
