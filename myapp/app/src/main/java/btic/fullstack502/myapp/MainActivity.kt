package btic.fullstack502.myapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// 안드로이드 앱을 구성하는 엑티비티 클래스
//  AppCompatActivity 클래스를 상속받아 화면 구성을 위한 엑티비티 클래스를 선언함
class MainActivity : AppCompatActivity() {
  
//  엑티비티가 처음 생성된 후 자동으로 실행되는 메소드
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//  엑티비티 화면이 상단의 베터리 상태 부분과 하단의 안드로이드 버튼 영역까지 표시하도록 하는 설정
    enableEdgeToEdge()

//  화면 UI 정보를 가지고 있는 엑티비티 xml 파일과 연동
    setContentView(R.layout.activity_main)

//  엑티비티의 내용이 화면에 출력 시 네비게이션바와 겹치지 않게 하는 설정
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }
  }
}