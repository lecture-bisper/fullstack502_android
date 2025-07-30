package btic.fullstack502.intentfiltermodule

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.intentfiltermodule.databinding.ActivityMainBinding
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
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

    binding.btnCall.setOnClickListener {
//      외부 앱의 엑티비티 클래스를 사용 시 클래스명을 알 수 없기 때문에 오류 발생
//      val intent = Intent(this, CalculatorActivity::class.java)

//      외부 앱의 엑티비티 클래스를 사용하려면 해당 앱의 AndroidManifast.xml 파일에 등록된 Intent Filter 를 통해서 제공한 엑티비티 클래스만 사용할 수 있음
      val intent = Intent("IntentFilterTest")
      intent.setPackage("btic.fullstack502.app_20250730")

      startActivity(intent)
    }
  }
}










