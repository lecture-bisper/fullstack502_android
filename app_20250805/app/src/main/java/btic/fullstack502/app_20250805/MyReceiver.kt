package btic.fullstack502.app_20250805

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MyReceiver : BroadcastReceiver() {

  override fun onReceive(context: Context, intent: Intent) {
    Log.d("**fullstack502**", "### MyReceiver ... ###")

    val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
    val builder: NotificationCompat.Builder

    val channelId = "채널ID"
    val channelName = "My Channel"

    val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    val audioAttr = AudioAttributes.Builder()
      .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
      .setUsage(AudioAttributes.USAGE_NOTIFICATION)
      .build()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
        description = "채널 설명"
        setShowBadge(true)
        setSound(soundUri, audioAttr)
        enableLights(true)
        lightColor = Color.WHITE
        enableVibration(true)
        vibrationPattern = longArrayOf(100, 200, 100, 200)
      }

      manager.createNotificationChannel(channel)
      builder = NotificationCompat.Builder(context, channelId)
    }
    else {
      builder = NotificationCompat.Builder(context)
    }

    builder.run {
      setSmallIcon(android.R.drawable.ic_notification_overlay)
      setWhen(System.currentTimeMillis())
      setContentTitle("알림")
      setContentText("안녕하세요")
    }

    manager.notify(11, builder.build())
  }
}










