package btic.fullstack502.app_20250801

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.RemoteInput

class RemoteInputReceiver : BroadcastReceiver() {

  override fun onReceive(context: Context, intent: Intent) {
//    안드로이드 상단 알림 서비스 가져오기
    val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

//    RemoteInput 정보를 매개변수로 전달받은 Intent 를 통해서 가져옴
    val remoteInput = RemoteInput.getResultsFromIntent(intent)
//    가져온 RemoteInput 정보에서 지정한 키 이름으로 저장된 데이터를 가져옴
    val receiverText = remoteInput?.getCharSequence("fullstack502-receiver").toString()

    Toast.makeText(context, "전달받은 메시지 : $receiverText", Toast.LENGTH_SHORT).show()
    Log.d("**fullstack502**", "전달받은 메시지 : $receiverText")

    manager.cancel(13)
  }
}