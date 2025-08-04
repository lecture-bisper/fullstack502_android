package btic.fullstack502.app_20250804

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250804.databinding.ActivityMainBinding
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

    binding.btnToolbar.setOnClickListener {
      val intent = Intent(this, ToolbarActivity::class.java)
      startActivity(intent)
    }

    binding.btnFragment.setOnClickListener {
      val intent = Intent(this, FragmentActivity::class.java)
      startActivity(intent)
    }

    binding.btnRecyclerView.setOnClickListener {
      val intent = Intent(this, RecyclerViewActivity::class.java)
      startActivity(intent)
    }

    binding.btnRecyclerView2.setOnClickListener {
      val intent = Intent(this, RecyclerView2Activity::class.java)
      startActivity(intent)
    }

    binding.btnViewPager.setOnClickListener {
      val intent = Intent(this, ViewPagerActivity::class.java)
      startActivity(intent)
    }

    binding.btnDrawer.setOnClickListener {
      val intent = Intent(this, DrawerActivity::class.java)
      startActivity(intent)
    }
  }
}









