package btic.fullstack502.app_20250730

import android.app.Activity
import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250730.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//  ActivityResultLauncher 를 사용하여 데이터를 돌려받을 경우 사용하는 콜백 함수
  private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    data ->
    if (data.resultCode == Activity.RESULT_OK) {
      val result = data.data?.getIntExtra("result", 0)
      Log.d("fullstack502", "ActivityResultLauncher 로 돌려받은 데이터 - result : $result")
      Toast.makeText(this, "최신 방식, 돌려받은 데이터 : $result", Toast.LENGTH_LONG).show()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    Log.d("fullstack502", "===== MainActivity 화면 출력 =====")

//    버튼 이벤트 등록
    binding.btnMove1.setOnClickListener {
      Log.d("fullstack502", "***** MainActivity 에서 DetailActivity 로 이동 *****")

//      Intent 를 사용하여 안드로이드 시스템에 지정한 엑티비티로 이동할 수 있도록 요청 설정
      val intent = Intent(this, DetailActivity::class.java)
//      startActivity() 를 사용하여 안드로이드 시스템에 Intent 정보를 전달
      startActivity(intent)
    }

    binding.btnMove2.setOnClickListener {
      Log.d("fullstack502", "***** MainActivity 에서 집접 만든 UserActivity로 이동 *****")
      val intent: Intent = Intent(this, UserActivity::class.java)
      startActivity(intent)
    }

//    버튼 이벤트 등록
    binding.btnMove3.setOnClickListener {
      Log.d("fullstack502", "***** MainAcitivity 에서 ParamActivity 로 매개변수와 함께 이동 *****")

//      putExtra(key, value) : 지정한 엑티비티로 데이터를 전달하는 메소드
//      key에 전달하는 데이터의 이름, value 에 전달하는 실제 데이터를 설정
      val intent = Intent(this, ParamActivity::class.java)
      intent.putExtra("data1", "hello world")
      intent.putExtra("data2", 100)
      startActivity(intent)
    }

    binding.btnMove4.setOnClickListener {
      val intent = Intent(this, CalculatorActivity::class.java)

      intent.putExtra("num1", 100)
      intent.putExtra("num2", 200)

//      Intent로 설정한 엑티비티로 화면 전환 요청하는 명령어
//      startActivity() : 단순 엑티비티 전환 명령어
//      startActivityForResult() : 지정한 엑티비티로 화면 전환 후 전환된 엑티비티에서 데이터를 돌려받는 명령어, 예전버전
//          onActivityResult() 메소드를 오버라이딩하여 사용 (돌려받는 데이터가 이곳으로 전달됨)
//      ActivityResultLauncher : 지정한 엑티비티로 화면 전환 후 전환된 엑티비티에서 데이터를 돌려받는 명령어, 최신버전
//          registerForActivityResult() 를 사용하여 콜백함수를 선언 후 돌려받은 데이터를 처리
      startActivityForResult(intent, 1000)
    }

//    ActivityResultLauncher 로 데이터 돌려받기
    binding.btnMove5.setOnClickListener {
      val intent = Intent(this, CalculatorActivity::class.java)

      intent.putExtra("num1", 100)
      intent.putExtra("num2", 200)

//      ActivityResultLauncher 호출
      launcher.launch(intent)
    }


    binding.btnUseIntentFilter.setOnClickListener {
//      명시적 인텐트를 사용하여 인텐트 객체 생성
//      val intent = Intent(this, IntentFilterActivity::class.java)

//      인텐트 필터를 사용하여 인텐트 객체 생성
//      인텐트 필터의 <action> 태그명으로 인텐트 객체 생성
      val intent = Intent("IntentFilterTest")
//      인텐트 필터를 통해서 연동된 클래스를 사용하기 때문에 전체 패키지명을 설정
      intent.setPackage("btic.fullstack502.app_20250730")

      startActivity(intent)
    }


    binding.btnLifeCycle.setOnClickListener {
      val intent = Intent(this, LifeCycleActivity::class.java)
      startActivity(intent)
    }
  }

//  startActivityForResult() 실행 후 지정한 엑티비티가 종료되면 자동 실행되는 콜백 함수, 이전 엑티비티가 전달하는 데이터를 가져옴
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

//  onActivityResult() 호출 시 종료된 엑티비티의 상태를 확인
    if (resultCode == Activity.RESULT_OK) {
//      종료된 엑티비티에서 전달한 데이터 가져오기
      val result = data?.getIntExtra("result", 0)
      Log.d("fullstack502", "***** startActivityForResult() 로 돌려받은 데이터 - result : $result")
      Toast.makeText(this, "예전방식, 돌려받은 데이터 : $result", Toast.LENGTH_SHORT).show()
    }
  }
}












