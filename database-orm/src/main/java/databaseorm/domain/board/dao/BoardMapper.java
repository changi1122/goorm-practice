package databaseorm.domain.board.dao;

import databaseorm.domain.board.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    int insertBoard(Board post);

    int updateBoard(Long id, Board post);

    List<Board> selectBoardAll();

    List<Board> selectBoardByCategory(String category);

    Board selectBoardById(Long id);

    int deleteBoard(Long id);

    long count();
}
