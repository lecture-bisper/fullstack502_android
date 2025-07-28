package btic.fullstack502.app_20250728

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250728.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private var viewFlag: Boolean = false

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

    binding.btnImageShow.setOnClickListener {

      if (viewFlag) {
        binding.ivImageHide.visibility = View.INVISIBLE
        viewFlag = false
      }
      else {
        binding.ivImageHide.visibility = View.VISIBLE
        viewFlag = true
      }

//      binding.ivImageHide.visibility = View.VISIBLE
    }

    binding.ivImageHide.setOnClickListener {
      binding.ivImageHide.visibility = View.INVISIBLE
    }
  }
}









