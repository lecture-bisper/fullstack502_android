package btic.fullstack502.app_20250730

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250730.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityCalculatorBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    Log.d("fullstack502", "----- CalculatorActivity 화면 -----")

//    intent : 현재 엑티비티로 전환 시 안드로이드 시스템에서 전달한 인텐트
    val calIntent = intent

//    전달받은 데이터 가져오기
    var num1 = calIntent.getIntExtra("num1", 0)
    var num2 = calIntent.getIntExtra("num2", 0)

//    전달받은 데이터를 UI 에 출력
    binding.edtNum1.setText(num1.toString())
    binding.edtNum2.setText(num2.toString())

    binding.tvNum1.text = num1.toString()
    binding.tvNum2.text = num2.toString()


//    예전 방식으로 데이터 돌려주기
    binding.btnResult1.setOnClickListener {
      val strNum1 = binding.edtNum1.text.toString()
      val strNum2 = binding.edtNum2.text.toString()

      num1 = strNum1.toInt()
      num2 = strNum2.toInt()
//      가져온 데이터 연산
      val result = num1 + num2

//      데이터 전달을 위한 인텐트 객체 생성, 예전 방식
//      현재 엑테비티가 종료되면 이전 엑티비티로 전환됨, 인텐트 객체 생성 시 엑티비티를 지정하지 않아도 됨
//      val resultIntent = Intent(this, MainActivity::class.java)
//      val resultIntent = Intent()
////      전달할 데이터 설정
//      resultIntent.putExtra("result", result)
      
//      데이터 전달을 위한 인텐트 객체 생성
      val resultIntent = Intent().apply {
        putExtra("result", result)
      }

//      현재 엑티비티 종료 후 복귀하는 엑티비티에 현재 엑티비티가 정상 종료라는 것을 알려주기 위한 값 설정
      setResult(Activity.RESULT_OK, resultIntent)
//      현재 엑티비티 종료
      finish()
    }

    binding.btnResult2.setOnClickListener {
      val strNum1 = binding.edtNum1.text.toString()
      val strNum2 = binding.edtNum2.text.toString()

      num1 = strNum1.toInt()
      num2 = strNum2.toInt()
      val result = num1 + num2

      val resultIntent = Intent().apply {
        putExtra("result", result)
      }
      setResult(RESULT_OK, resultIntent)
      finish()
    }
  }
}











