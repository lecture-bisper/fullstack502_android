package btic.fullstack502.app_20250808

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250808.data.BoardItem
import btic.fullstack502.app_20250808.databinding.ActivityHttpNetworkBinding
import retrofit2.Call
import retrofit2.Response

class HttpNetworkActivity : AppCompatActivity() {

  private val binding by lazy { ActivityHttpNetworkBinding.inflate(layoutInflater) }

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
    binding.btnClear.setOnClickListener {
      binding.etBoardIdx.setText("")
      binding.tvResult.text = ""
    }

//    레트로핏으로 통신하기
//    1. 통신 시 사용할 data class 생성
//    2. 통신 시 사용할 메소드를 가지고 있는 인터페이스 생성
//    3. Retrofit 객체를 쉽게 받아올 object 타입 생성
//    4. 통신할 메소드에서 Retrofit 객체 받아오기
//    5. Retrofit 객체를 통해서 인터페이스에서 제공한 메소드 호출 및 Call 객체 받아오기
//    6. Call 객체가 제공하는 enqueue() 를 호출하여 실제로 통신
    
    binding.btnGetBoardList.setOnClickListener {
//      서버와 통신 시 사용하는 인터페이스를 가지고 생성한 Retrofit 객체를 가져옴
      val api = RetrofitBoardClient.instance
//      Retrofit 객체를 사용하여 인터페이스에서 제공하는 메소드를 실행하여 Call 객체를 가져옴
      val call = api.getSelectBoardList()

//      Call 객체에서 제공하는 enqueue 를 호출하여 통신 시작
      call.enqueue(object : retrofit2.Callback<List<BoardItem>> {
        override fun onResponse(call: Call<List<BoardItem>?>, response: Response<List<BoardItem>?>) {
          if (response.isSuccessful) {
            val boardList = response.body()
            boardList?.forEach {
              Log.d("**fullstack502**", "글번호 : ${it.boardIdx}, 제목 : ${it.title}, 글쓴이 : ${it.createID}")
            }
          }
          else {
            Log.d("**fullstack502**", "응답 실패 : ${response.errorBody()?.toString()}")
          }
        }

        override fun onFailure(call: Call<List<BoardItem>?>, t: Throwable) {
          TODO("Not yet implemented")
        }

      })
    }

    binding.btnGetBoardDetail.setOnClickListener {
      val boardIdx = binding.etBoardIdx.text.toString().toInt()

      val api = RetrofitBoardClient.instance
      val call = api.getSelectBoardDetail(boardIdx)

      call.enqueue(object: retrofit2.Callback<BoardItem> {
        override fun onResponse(
          call: Call<BoardItem?>,
          response: Response<BoardItem?>
        ) {
          if (response.isSuccessful) {
            val board = response.body()
            Log.d("**fullstack502**", "${board?.boardIdx}, ${board?.title}")
          }
        }

        override fun onFailure(
          call: Call<BoardItem?>,
          t: Throwable
        ) {
          TODO("Not yet implemented")
        }
      })
    }
  }
}









