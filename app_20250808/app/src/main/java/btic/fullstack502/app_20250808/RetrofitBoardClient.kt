package btic.fullstack502.app_20250808

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//  Retrofit 객체를 생성하기 쉽도록 만든 object 타입
object RetrofitBoardClient {

//  서버 주소 설정
//  서버 주소 설정 시 해당 서버의 ip 혹은 dns 주소를 입력
//  localhost 로 설정하면 서버가 아닌 현재 앱이 실행되고 있는 안드로이드 기기를 의미하게 됨
  private val BASE_URL = "http://10.100.202.2:8080/backend/"

//  Retrofit 객체 생성
  val instance: BoardAPI by lazy {
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create()) // 서버에서 전달받은 데이터를 변환할 컨버터 설정
      .build()
      .create(BoardAPI::class.java)
  }
}