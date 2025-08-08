package btic.fullstack502.app_20250807

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//  Retrofit 클래스 타입의 객체를 생성하기 위한 object
//  xml 방식과 json 방식을 동시에 제공하는 서버에 접속하고자 할 경우 xml과 json 방식에 대한 object를 각각 따로 생성하는 것이 좋음
object KobisRetrofitClientJson {

//  실제 서버에서 접속할 base url 선언
  private val BASE_URL = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"

//  Retrofit 타입의 객체 생성, Builder 패턴으로 생성
  val instance: KobisDailyBoxOfficeAPI by lazy {
    Retrofit.Builder()
      .baseUrl(BASE_URL) // 접속할 서버 url 설정
//      서버의 응답 데이터를 파싱할 라이브러리 설정
//      현재는 Json 데이터를 파싱하기 때문에 Gson 라이브러리 사용
//      Retrofit 에서 Gson 방식으로 컨버팅하는 Converter-Gson 라이브러리에서 제공하는 GsonConverterFactory 를 사용
      .addConverterFactory(GsonConverterFactory.create())
      .build() // Retrofit 클래스 타입의 객체 생성
//      생성된 Retrofit 객체를 사용하여 서비스 인터페이스를 구현한 객체를 생성
      .create(KobisDailyBoxOfficeAPI::class.java)
  }
}