package btic.fullstack502.app_20250805

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

  override fun onCreate() {
    super.onCreate()

    Log.d("**fullstack502", "### bindService() 실행 - onCreate() 호출 ###")
  }

  override fun onBind(intent: Intent): IBinder {
    Log.d("**fullstack502**", "### bindService() 실행 - onBind() 호출 ###")

//    사용자가 만든 Binder 클래스 타입의 객체를 반환
//    ServiceConnection 타입 객체의 onServiceConnected() 의 매개변수로 전달 됨
//    onServiceConnected() 매개변수를 이용하면 서비스에서 연산된 결과를 엑티비티에서 사용할 수 있음
    return MyBinder("MyService에서 생성한 MyBinder 객체")
  }

  override fun onUnbind(intent: Intent?): Boolean {
    Log.d("**fullstack502**", "### unbindService() 실행 - onUnbind() 호출 ###")

    return super.onUnbind(intent)
  }

  override fun onDestroy() {
    super.onDestroy()

    Log.d("**fullstack502**", "### unbindService() 실행 - onDestroy() 호출 ###")
  }
}