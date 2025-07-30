package btic.fullstack502.app_20250730

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250730.databinding.ActivityLifeCycleBinding

class LifeCycleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityLifeCycleBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    Log.d("LifeCycle Method", "***** onCreate() 호출 *****")
  }

  override fun onStart() {
    super.onStart()

    Log.d("LifeCycle Method", "***** onStart() 호출 *****")
  }

  override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)

    Log.d("LifeCycle Method", "***** onRestoreInstanceState() 호출 *****")

//    상태 복구
    val data1 = savedInstanceState.getString("saveData1")
    val data2 = savedInstanceState.getInt("saveData2")

    Log.d("LifeCycle Method", "***** 복구한 데이터 data1: $data1, data2: $data2 *****")
  }

  override fun onResume() {
    super.onResume()

    Log.d("LifeCycle Method", "***** onResume() 호출 *****")
  }

  override fun onPause() {
    super.onPause()

    Log.d("LifeCycle Method", "***** onPause() 호출 *****")
  }

  override fun onStop() {
    super.onStop()

    Log.d("LifeCycle Method", "***** onStop() 호출 *****")
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)

    Log.d("LifeCycle Method", "***** onSaveInstanceState() 호출 *****")

//    상태 정보 저장
    outState.putString("saveData1", "onSave에서 저장한 데이터")
    outState.putInt("saveData2", 100)
  }

  override fun onDestroy() {
    super.onDestroy()

    Log.d("LifeCycle Method", "***** onDestroy() 호출 *****")
  }

  override fun onRestart() {
    super.onRestart()

    Log.d("LifeCycle Method", "***** onRestart() 호출 *****")
  }
}










