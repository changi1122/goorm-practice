package databaseorm.domain.board.dao;

import databaseorm.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByCategory(String category);

    boolean existsByTitle(String title);
}
