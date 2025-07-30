package btic.fullstack502.app_20250730

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250730.databinding.ActivityDetailBinding
import btic.fullstack502.app_20250730.databinding.ActivityUserBinding

//  직접 엑티비티 파일 만들기 (코틀린 파일)
//  AppCompatActivity 클래스를 상속받음
class UserActivity : AppCompatActivity() {

//  생명주기 메소드인 onCreate() 를 반드시 오버라이딩하여 구현
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    enableEdgeToEdge()
    val binding = ActivityUserBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.user) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    Log.d("fullstack502", "----- UserActivity 화면 출력 -----")
  }
}









