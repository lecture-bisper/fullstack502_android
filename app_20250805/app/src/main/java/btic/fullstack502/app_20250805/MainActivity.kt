package btic.fullstack502.app_20250805

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250805.databinding.ActivityMainBinding
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

    binding.btnReceiver.setOnClickListener {
      val intent = Intent(this, ReceiverActivity::class.java)
      startActivity(intent)
    }

    binding.btnService.setOnClickListener {
      val intent = Intent(this, ServiceActivity::class.java)
      startActivity(intent)
    }

    binding.btnProvider.setOnClickListener {
      val intent = Intent(this, ProviderActivity::class.java)
      startActivity(intent)
    }
  }
}











