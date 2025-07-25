package btic.fullstack502.app_20250725

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

//    View 클래스로 구성된 Content View 파일을 가져옴
//    전체 UI 를 가지고 있는 ViewGroup (layout) 을 가져옴
    setContentView(R.layout.activity_main)

//    findViewById() : setContentView() 를 통해서 연동된 엑티비티의 UI 안에서 id 속성값을 기반으로 View 태그를 검색하여 가져오는 명령어
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    val btn2_show = findViewById<Button>(R.id.btn2_show)
    val btn2_target = findViewById<Button>(R.id.btn2_target)
    val btn2_hide = findViewById<Button>(R.id.btn2_hide)
    val btn3_show = findViewById<Button>(R.id.btn3_show)
    val btn3_target = findViewById<Button>(R.id.btn3_target)
    val btn3_hide = findViewById<Button>(R.id.btn3_hide)

    btn2_show.setOnClickListener {
      btn2_target.visibility = View.VISIBLE
    }

    btn2_hide.setOnClickListener {
      btn2_target.visibility = View.INVISIBLE
    }

    btn3_show.setOnClickListener {
      btn3_target.visibility = View.VISIBLE
    }

    btn3_hide.setOnClickListener {
      btn3_target.visibility = View.GONE
    }
  }
}







