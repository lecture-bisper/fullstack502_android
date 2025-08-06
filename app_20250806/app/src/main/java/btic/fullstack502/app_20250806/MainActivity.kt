package btic.fullstack502.app_20250806

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250806.databinding.ActivityMainBinding
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

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
    binding.btnDb.setOnClickListener {
      val intent = Intent(this, DatabaseActivity::class.java)
      startActivity(intent)
    }

    binding.btnFile.setOnClickListener {
      val intent = Intent(this, FileActivity::class.java)
      startActivity(intent)
    }
  }
}