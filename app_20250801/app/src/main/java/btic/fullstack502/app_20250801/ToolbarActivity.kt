package btic.fullstack502.app_20250801

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250801.databinding.ActivityToolbarBinding

class ToolbarActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityToolbarBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    툴바 사용 설정
    setSupportActionBar(binding.toolbar)
//    supportActionBar?.title = getString(R.string.app_name)

//    툴바의 뒤로가기 버튼 설정 2가지
//    1. AndroidManifest 파일의 엑티비티 속성 중 parentActivityName 에 이동할 부모 엑티비티명을 입력, 자식 엑티비티의 코틀린 소스코드 부분에 supportActionBar?.setDisplayHomeAsUpEnabled(true) 를 사용
//    2. 자식 엑티비티의 코틀린 소스코드 부분에 supportActionBar?.setDisplayHomeAsUpEnabled(true) 를 사용하고, onSupportNavigateUp() 메소드를 오버라이딩하여 사용
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

//  2번 방식을 사용할 경우 오버라이딩할 메소드
//  뒤로가기 버튼 클릭 시 추가로 작업할 것이 있을 경우 사용함
  override fun onSupportNavigateUp(): Boolean {
    super.onSupportNavigateUp()

    Log.d("**fullstack502**", "툴바의 뒤로가기 버튼 클릭!!")
    Toast.makeText(this, "뒤로가기 버튼 클릭", Toast.LENGTH_SHORT).show()

//  실제 뒤로 가기 버튼 기능을 실행
    onBackPressedDispatcher.onBackPressed()

    return true
  }
}









