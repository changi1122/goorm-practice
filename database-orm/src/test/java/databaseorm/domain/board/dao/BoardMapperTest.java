package databaseorm.domain.board.dao;

import databaseorm.DataInitializer;
import databaseorm.domain.board.Board;
import databaseorm.domain.common.UserRole;
import databaseorm.domain.user.User;
import databaseorm.domain.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardMapperTest {

    @Autowired
    BoardMapper boardMapper;
    @Autowired
    UserRepository userRepository;

    @MockitoBean
    DataInitializer dataInitializer;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .loginId("loginid")
                .password("password")
                .username("username")
                .userRole(UserRole.USER)
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        for (Board post : boardMapper.selectBoardAll()) {
            boardMapper.deleteBoard(post.getId());
        }
    }

    @Test
    @DisplayName("게시판 테이블에 새로운 게시글을 저장합니다.")
    void insertBoard() {
        // given
        User author = userRepository.findByLoginId("loginid").orElseThrow();
        Board post = createPost("제목", author);

        // when
        int col = boardMapper.insertBoard(post);

        // then
        assertThat(col).isEqualTo(1);

        List<Board> boardList = boardMapper.selectBoardAll();
        Board result = boardList.get(0);
        assertThat(result)
                .extracting("title", "category")
                .containsExactly("제목", "카테고리");
    }

    @Test
    @DisplayName("게시판 테이블의 게시글을 수정합니다.")
    void updateBoard() {
        // given
        User author = userRepository.findByLoginId("loginid").orElseThrow();
        Board post = createPost("제목", author);
        boardMapper.insertBoard(post);

        // when
        List<Board> boardList = boardMapper.selectBoardAll();
        Board selectPost = boardMapper.selectBoardById(boardList.get(0).getId());
        selectPost.setTitle("수정된 제목");
        selectPost.setBody("<p>수정 본문</p>");
        selectPost.setCategory("수정카테고리");

        int col = boardMapper.updateBoard(selectPost.getId(), selectPost);

        // then
        assertThat(col).isEqualTo(1);

        Board result = boardMapper.selectBoardById(selectPost.getId());
        assertThat(result)
                .extracting("title", "category", "body")
                .containsExactly("수정된 제목", "수정카테고리", "<p>수정 본문</p>");
    }

    @Test
    @DisplayName("게시판 테이블의 게시글을 삭제합니다.")
    void deleteBoard() {
        // given
        User author = userRepository.findByLoginId("loginid").orElseThrow();
        Board post = createPost("제목", author);
        boardMapper.insertBoard(post);

        // when
        List<Board> boardList = boardMapper.selectBoardAll();
        Board selectPost = boardMapper.selectBoardById(boardList.get(0).getId());

        int col = boardMapper.deleteBoard(selectPost.getId());

        // then
        assertThat(col).isEqualTo(1);

        List<Board> result = boardMapper.selectBoardAll();
        assertThat(result).hasSize(0);
    }

    private Board createPost(String title, User author) {
        return Board.builder()
                .title(title)
                .body("<p>본문</p>")
                .category("카테고리")
                .createdAt(LocalDateTime.now())
                .author(author)
                .build();
    }
}