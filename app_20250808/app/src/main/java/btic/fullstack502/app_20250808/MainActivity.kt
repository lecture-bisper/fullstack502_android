package btic.fullstack502.app_20250808

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250808.databinding.ActivityMainBinding

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

    binding.btnNetwork.setOnClickListener {
      val intent = Intent(this, HttpNetworkActivity::class.java)
      startActivity(intent)
    }

    binding.btnGlide.setOnClickListener {
      val intent = Intent(this, GlideActivity::class.java)
      startActivity(intent)
    }
  }
}