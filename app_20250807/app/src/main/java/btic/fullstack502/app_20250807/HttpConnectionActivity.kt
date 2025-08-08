package btic.fullstack502.app_20250807

import android.content.ContextParams
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250807.data.Kobis
import btic.fullstack502.app_20250807.data.KobisJson
import btic.fullstack502.app_20250807.databinding.ActivityHttpConnectionBinding
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call

class HttpConnectionActivity : AppCompatActivity() {

  private val binding by lazy { ActivityHttpConnectionBinding.inflate(layoutInflater) }

//  Volley 라이브러리의 RequestQueue(서버 요청자) 객체 생성
  private val queue by lazy { Volley.newRequestQueue(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    initEvent()
  }

  private fun initEvent() {

//    Volley 문자열 데이터 요청
    binding.btnVolleyRequestString.setOnClickListener {
//      사용자가 입력한 url 가져오기
      val url = binding.etServerUrl.text.toString()

//      Volley 라이브러리를 사용한 문자열 방식 데이터 가져오기, StringRequest() 사용
      val stringRequest = object: StringRequest(
//        Get 방식으로 통신
        Request.Method.GET,
//        접속할 서버 url
        url,
//        통신 성공 시 동작
        Response.Listener<String> {
          Log.d("**fullstack502**", "### 서버에서 데이터 받아옴 ###")
          binding.tvResult.text = it
          Log.d("**fullstack502**", "### $it ###")
        },
//        통신 실패 시 동작
        Response.ErrorListener { error -> Log.d("**fullstack502**", "### error : $error ###")}
      )
//      통신 방식이 POST 일 경우 getParams() 메소드를 사용하여 데이터를 전달
      {
        override fun getParams(): Map<String?, String?>? {
          return mutableMapOf("num1" to "100", "num2" to "200")
        }
      }

//      실제 Volley 라이브러리가 동작할 RequestQueue 에 StringRequest 객체를 추가
//      Volley 라이브러리가 비동기 방식으로 지정한 서버에 접속하여 통신을 진행
      queue.add(stringRequest)
    }

//    Volley Json 데이터 요청
    binding.btnVolleyRequestJson.setOnClickListener {
      val url = binding.etServerUrl.text.toString()

//      Volley 로 Json 데이터 요청 시 JsonObjectRequest, JsonArrayRequest 방식 이 있음
//      JsonObjectRequest : 서버에서 전달받는 데이터가 Json Object 타입일 경우, {} 로 시작
//      JsonArrayRequest : 서버에서 전달받는 데이터가 Json Array 타입일 경우, [] 로 시작
      val jsonRequest = JsonObjectRequest(
        Request.Method.GET,
        url,
        null,
//        통신 성공 시 실행할 코드 블럭
        {
          Log.d("**fullstack502**", "### Json 방식으로 데이터 가져오기 성공!! ###")
          Log.d("**fullstack502**", "### $it ###")

//          Json 데이터를 파싱하기 위한 Gson 클래스 타입의 객체 생성, 
          val gson = Gson()
//          Gson을 이용하여 Json 데이터를 파싱, Json 데이터와 연동될 최상위 data class를 설정
//          fromJson() : json 문자열을 지정한 데이터 타입으로 변환
//          toJson() : 지정한 데이터 타입을 json 문자열로 변환
          val kobis: Kobis = gson.fromJson(it.toString(), Kobis::class.java)
//          Kobis 클래스 타입으로 변환 데이터 중에서 필요한 데이터 부분만 가져오기
          val dailyBoxOfficeList = kobis.boxOfficeResult?.dailyBoxOfficeList
          var result = ""
//          반복문을 사용하여 필요한 데이터만 출력
          for (item in dailyBoxOfficeList!!) {
            Log.d("**fullstack502**", "### 제목 : ${item.movieNm} ###")
            result += "제목 : ${item.movieNm}\n"
          }

          binding.tvResult.text = result
        },
//        통신 실패 시 실행할 코드 블럭
        {
          error -> Log.d("**fullstack502**", "### Json 방식으로 데이터 가져오기 오류\nError : $error")
        }
      )

      queue.add(jsonRequest)
    }

    binding.btnRetrofitRequestString.setOnClickListener {

    }

    binding.btnRetrofitRequestJson.setOnClickListener {
//      사용자가 지정한 인터페이스를 사용하는 Retrofit 객체 생성
      val api = KobisRetrofitClientJson.instance
//      사용자가 지정한 인터페이스의 메소드를 호출, Call 클래스 타입의 객체를 가져옴
      val call = api.getDailyBoxOfficeListJson(
        key = "c55013eadce1f0005fae142c556a228d",
        targetDt = "20250807"
      )

//      Call클래스 타입의 객체를 통해서 통신을 진행하고 결과를 받아옴
//      enqueue() : 지정한 서버와 통신을 시작하는 메소드
      call.enqueue(object: retrofit2.Callback<KobisJson> {
//        통신 성공 시 동작할 소스
        override fun onResponse(call: Call<KobisJson?>, response: retrofit2.Response<KobisJson?>) {
          if (response.isSuccessful) {
//            서버에서 받아온 데이터를 출력
            val kobisData = response.body()
            var result = ""

            kobisData?.boxOfficeResult?.dailyBoxOfficeList?.forEach {
              Log.d("**fullstack502**", "### 순위 : ${it.rank}, 제목 : ${it.movieNm}, 관객수 : ${it.audiCnt} ###")
              result += "순위 : ${it.rank}, 제목 : ${it.movieNm}, 관객수 : ${it.audiCnt}\n"
            }
            binding.tvResult.text = result

//            val boxOfficeResult = kobisData?.boxOfficeResult
//            val dailyBoxOfficeList = boxOfficeResult?.dailyBoxOfficeList
//
//            for (item in dailyBoxOfficeList!!) {
//              Log.d("**fullstack502**", "### 순위 : ${item.rank}, 제목 : ${item.movieNm}, 관객수 : ${item.audiCnt} ###")
//            }
          }
          else {
            Log.d("**fullstack502**", "### 응답 실패 : ${response.errorBody().toString()} ###")
          }
        }

//        통신 실패 시 실행할 소스
        override fun onFailure(call: Call<KobisJson?>, t: Throwable) {
          Log.d("**fullstack502**", "### 서버 요청 실패 : ${t.message} ###")
        }
      })
    }

    binding.btnUrlClear.setOnClickListener {
      binding.etServerUrl.setText("")
      binding.tvResult.text = ""
    }
  }
}