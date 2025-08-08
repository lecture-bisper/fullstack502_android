package bitc.full502.backend_server.service;

import bitc.full502.backend_server.dto.BoardDTO;
import bitc.full502.backend_server.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
  private final BoardMapper boardMapper;

  @Override
  public List<BoardDTO> selectBoardList() {
    return boardMapper.selectBoardList();
  }

  @Override
  public BoardDTO selectBoardDetail(int boardIdx) {
    return boardMapper.selectBoardDetail(boardIdx);
  }
}














