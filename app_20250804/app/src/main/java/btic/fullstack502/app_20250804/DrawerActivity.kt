package btic.fullstack502.app_20250804

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250804.databinding.ActivityDrawerBinding

class DrawerActivity : AppCompatActivity() {

  lateinit var toggle: ActionBarDrawerToggle

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityDrawerBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.drawer) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    액션바 혹은 툴바를 적용 시 메뉴가 출력되는 토글 버튼을 설정하여 사용
    toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    toggle.syncState()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
//    이벤트가 토글 버튼에서 발생하면 동작
    if (toggle.onOptionsItemSelected(item)) {
      return true
    }

    return super.onOptionsItemSelected(item)
  }
}







