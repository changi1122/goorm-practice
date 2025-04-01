package databaseorm.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import databaseorm.DataInitializer;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardRepositoryImplTest {

    @Autowired
    private BoardRepository boardRepository;

    @MockitoBean // 테스트시 샘플 데이터 추가하는 컴포넌트 제외
    private DataInitializer dataInitializer;

    @DisplayName("게시판에서 게시글을 ID로 조회합니다.")
    @Test
     void save() {
        // given
        Board post1 = createBoard("제목 1");

        // when
        post1 = boardRepository.save(post1);

        // then
        Board found = boardRepository.findById(post1.getId()).orElseThrow();
        assertThat(found.getTitle()).isEqualTo(post1.getTitle());
    }

    @DisplayName("게시판에서 게시글 전체 목록을 조회합니다.")
    @Test
    void findAll() {
        // given
        Board post1 = createBoard("제목 1");
        Board post2 = createBoard("제목 2");

        // when
        boardRepository.save(post1);
        boardRepository.save(post2);

        // then
        assertThat(boardRepository.findAll().size()).isEqualTo(2);
    }


    private Board createBoard(String title) {
        return new Board(
                null,
                title,
                "",
                "카테고리",
                null,
                LocalDateTime.of(2025, 3, 19, 13, 10)
        );
    }
}