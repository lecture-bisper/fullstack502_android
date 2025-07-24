package btic.fullstack502.kotlin_20250724

import android.graphics.Typeface
import android.os.Bundle
import android.renderscript.Sampler
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

//    소스코드로 화면 UI 그리기

    val name = TextView(this).apply {
      typeface = Typeface.DEFAULT_BOLD
      text = "아무 글자나 입력하세요"
    }

    val image = ImageView(this).also {
      it.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dog01))
    }

    val address = TextView(this).apply {
      typeface = Typeface.DEFAULT_BOLD
      text = "강아지 사진"
    }

    val layout = LinearLayout(this).apply {
      orientation = LinearLayout.VERTICAL
      gravity = Gravity.CENTER

      addView(name, WRAP_CONTENT, WRAP_CONTENT)
      addView(image, WRAP_CONTENT, WRAP_CONTENT)
      addView(address, WRAP_CONTENT, WRAP_CONTENT)
    }

    setContentView(layout)
  }
}