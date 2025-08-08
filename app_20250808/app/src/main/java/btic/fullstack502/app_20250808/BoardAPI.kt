package btic.fullstack502.app_20250808

import btic.fullstack502.app_20250808.data.BoardItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//  서버와 통신 시 사용할 메소드를 정의하는 인터페이스
interface BoardAPI {

//  반환값은 서버에서 전달받을 데이터 타입을 설정
  @GET("board/boardList")
  fun getSelectBoardList(): Call<List<BoardItem>> // 리스트 타입으로 전달받음

  @GET("board/boardDetail")
  fun getSelectBoardDetail(@Query("boardIdx") boardIdx: Int) : Call<BoardItem>
}









