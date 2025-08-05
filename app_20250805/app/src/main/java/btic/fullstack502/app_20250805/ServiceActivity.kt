package btic.fullstack502.app_20250805

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250805.databinding.ActivityServiceBinding

class ServiceActivity : AppCompatActivity() {

//  startService()로 동작하는 service의 intent
//  stopService()로 동작하고 있는 service를 정지하기 위해서 클래스의 필드로 선언
  private lateinit var startServiceIntent: Intent

  private lateinit var serviceBinder: MyBinder
//  bindService() 를 사용하여 동작하는 service 의 ServiceConnection 객체
//  unbindService() 를 사용하여 동작하고 있는 ServiceConnection 객체를 정지하기 위해서 클래스 필드로 선언
  private lateinit var connection: ServiceConnection

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityServiceBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    bindService()로 서비스 동작을 위해서 ServiceConnection 객체 생성
    connection = object: ServiceConnection {
//      사용자 서비스인 MyService 의 onBind() 메소드 반환값을 매개변수로 받아옴
//      전달받은 매개변수에는 MyService 에서 연산된 결과값이 들어있음
      override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.d("**fullstack502**", "### onServiceConnected() 실행됨 ###")

        serviceBinder = service as MyBinder
        Log.d("**fullstack502**", "### onServiceConnected() 에서 데이터 확인 : ${serviceBinder.msg}")
      }

      override fun onServiceDisconnected(name: ComponentName?) {
        Log.d("**fullstack502**", "### onServiceDisconnected() 실행됨 ###")
      }
    }

//    startService() 로 서비스 시작
    binding.btnStartService.setOnClickListener {
      Log.d("**fullstack502**", "## 서비스 시작 버튼 클릭!! ##")

      startServiceIntent = Intent(this, TimeService::class.java)
      startService(startServiceIntent)
    }

//    startService() 로 기존 서비스 종료
    binding.btnStopService.setOnClickListener {
      Log.d("**fullstack502**", "## 서비스 정지 버튼 클릭!! ##")

      stopService(startServiceIntent)
    }

//    bindService()로 서비스 시작
    binding.btnBindService.setOnClickListener {
      val intent = Intent(this, MyService::class.java)
      bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

//    unbindService()로 기존 서비스 정지
    binding.btnUnbindService.setOnClickListener {
      unbindService(connection)
    }
  }
}









