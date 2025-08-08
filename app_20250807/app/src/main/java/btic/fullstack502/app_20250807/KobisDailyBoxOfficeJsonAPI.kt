package btic.fullstack502.app_20250807

import btic.fullstack502.app_20250807.data.KobisJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//  Retrofit2 라이브러리를 사용하여 서버와 통신 시 사용할 메소드를 정의하는 인터페이스
interface KobisDailyBoxOfficeAPI {

//  @GET(@POST, @PUT, @DELETE) : 서버와 통신 방식을 설정하는 어노테이션
//  () 안에 url을 입력 시 서브 url 로 동작하여 base url + sub url 형태로 사용하여 서버에 접속할 전체 URL이 됨

//  @GET 을 사용하여 get 방식으로 서버와 통신하고 base url + sub url 주소인 searchDailyBoxOfficeList.xml 로 접속 설정
//  전체 url은 http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json 로 설정됨
  @GET("searchDailyBoxOfficeList.xml")
  fun getDailyBoxOfficeListXml(
//  @Query : 전체 url 에서 ' ? ' 뒤에 있는 쿼리 스트링의 변수와 값을 지정하는 어노테이션
//  클라이언트에서 서버로 전달하는 데이터를 설정
    @Query("key") key: String,
    @Query("targetDt") targetDt: String
  ) : Call<KobisJson>

  @GET("searchDailyBoxOfficeList.json")
  fun getDailyBoxOfficeListJson(
    @Query("key") key: String,
    @Query("targetDt") targetDt: String
  ) : Call<KobisJson>
}









