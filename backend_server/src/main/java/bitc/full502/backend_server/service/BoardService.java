package bitc.full502.backend_server.service;

import bitc.full502.backend_server.dto.BoardDTO;

import java.util.List;

public interface BoardService {

  List<BoardDTO> selectBoardList();

  BoardDTO selectBoardDetail(int boardIdx);
}
