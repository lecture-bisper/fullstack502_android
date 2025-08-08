package bitc.full502.backend_server.mapper;

import bitc.full502.backend_server.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
  List<BoardDTO> selectBoardList();

  BoardDTO selectBoardDetail(int boardIdx);
}
