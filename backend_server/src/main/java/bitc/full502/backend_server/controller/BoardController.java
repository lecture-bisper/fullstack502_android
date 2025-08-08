package bitc.full502.backend_server.controller;

import bitc.full502.backend_server.dto.BoardDTO;
import bitc.full502.backend_server.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/backend")
@RestController
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/")
  public String index() {
    return "안드로이드 앱을 위한 백엔드 서버";
  }

  @GetMapping("/board/boardList")
  public Object boardList() {

    List<BoardDTO> boardList = boardService.selectBoardList();

    return boardList;
  }

  @GetMapping("/board/boardDetail")
  public Object boardDetail(@RequestParam("boardIdx") int boardIdx) {

    BoardDTO board = boardService.selectBoardDetail(boardIdx);

    return board;
  }
}














