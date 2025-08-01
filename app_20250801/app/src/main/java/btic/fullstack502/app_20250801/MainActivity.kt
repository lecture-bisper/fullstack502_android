package btic.fullstack502.app_20250801

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.core.graphics.drawable.IconCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250801.databinding.ActivityMainBinding
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

  private val binding by lazy {
    ActivityMainBinding.inflate(layoutInflater)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    setupEvent()
  }

  private fun setupEvent() {

    binding.btnNotification.setOnClickListener {
      val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
      val builder: NotificationCompat.Builder

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val channelId = "notify-channel"
        val channelName = "알림 채널 1번"
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)

        channel.description = "상단 알림 터치 이벤트"
        channel.setShowBadge(true)
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val audioAttr = AudioAttributes.Builder()
          .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
          .setUsage(AudioAttributes.USAGE_ALARM)
          .build()
        channel.setSound(uri, audioAttr)
        channel.enableLights(true)
        channel.lightColor = Color.BLUE
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

        manager.createNotificationChannel(channel)
        builder = NotificationCompat.Builder(this, channelId)
      }
      else {
        builder = NotificationCompat.Builder(this)
      }

      builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
      builder.setWhen(System.currentTimeMillis())
      builder.setContentTitle("app_20250801 상단 알림창 사용하기")
      builder.setContentText("클릭 시 app_20250801 앱의 화면으로 이동")
      builder.setAutoCancel(true)

//      상단 알림의 터치 이벤트 : 상단 알림 자체가 현재 앱에서 동작하는 것이 아니라 안드로이드 시스템에 대신 실행해 달라고 요청하는 것이기 때문에 상단 알림의 터치 이벤트도 직접 실행할 수 없음
//      각종 이벤트를 모두 안드로이드 시스템에 동작 요청을 해야 함
//      이러한 요청을 Intent 객체를 이용하여 안드로이드 시스템에 요청해야 함

//      화면 전환을 위한 Intent 객체 생성
//      첫번째 매개변수 : 전환할 앱을 선택 (현재 앱을 의미)
//      두번째 매개변수 : 화면 전환을 원하는 엑티비티를 선택
      val intent = Intent(this, NotificationActivity::class.java)
//      안드로이드에 시스템에 상단 알림의 터치 이벤트 발생 시 지정한 엑티비티로 이동 설정
//      네번째 매개변수 : 기존의 PendingIntent 가 존재할 경우(동일한 FLAG 값으로 똑같은 알림이 발생했을 경우) 어떻게 처리할지 를 설정, API 31 이상에서는 FLAG_MUTABLE, FLAG_IMMUTABLE 중 하나를 사용해야 함
//        FLAG_UPDATE_CURRENT : PendingIntent 가 이미 존재할 경우 데이터만 업데이트 후 재사용
//        FLAG_CANCEL_CURRENT : PendingIntent 가 이미 존재할 경우 기존 것을 취소하고 새로 생성
//        FLAG_ONE_SHOT : PendingIntent 가 한번만 사용 가능, 사용 후 자동 소멸
//        FLAG_NO_CREATE : 기존에 존재하는 PendingIntent 만 반환, 없으면 null
//        FLAG_IMMUTABLE : 변경 불가능한 PendingIntent, 보안을 위해서 사용, API 31 이상에서 필수
//        FLAG_MUTABLE : 외부 앱이 Intent 의 내용을 변경할 수 있음, RemoteInput 시 사용
      val pendingIntent = PendingIntent.getActivity(this, 12, intent, PendingIntent.FLAG_IMMUTABLE)

//      상단 알림창에 PendingIntent 정보 등록
      builder.setContentIntent(pendingIntent)

      manager.notify(11, builder.build())
    }


//    상단 알림창의 액션버튼 사용하기
    binding.btnNotificationAction.setOnClickListener {
//      안드로이드 시스템의 상단 알림 서비스 가져오기
      val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//      현재 앱의 상단 알림 서비스에서 사용할 고유 ID 설정, 사용자가 직접 설정
      val notificationId = 12
      val channelId = "notify-channel"
      val channelName = "알림 채널 1번"

//      상단 알림 시 사용할 음원 설정
      val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
      val audioAttr = AudioAttributes.Builder()
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
        .build()

//      API 33 이상일 경우 채널 설정
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//        알림 채널 값 설정
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH).apply {
          description = "상단 알림 액션 버튼 사용하기"
          setShowBadge(true)
          setSound(soundUri, audioAttr)
          enableLights(true)
          lightColor = Color.WHITE
          enableVibration(true)
          vibrationPattern = longArrayOf(100, 200, 100, 200)
        }

//        안드로이드 상단 알림에 채널 정보 등록
        manager.createNotificationChannel(channel)
      }

      val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        NotificationCompat.Builder(this, channelId)
      }
      else {
        NotificationCompat.Builder(this)
      }

//      안드로이드 상단 알림 화면 설정
      builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        .setContentTitle("상단 알림 app_20250801")
        .setContentText("상단 알림에서 액션 버튼 사용")
        .setWhen(System.currentTimeMillis())
        .setAutoCancel(true)

//      안드로이드 상단 알림의 액션 버튼 설정
//      상단 알림의 터치 이벤트 말고 액션 버튼을 이용한 이벤트를 설정할 수 있음, 3개 까지 가능
//      상단 알림의 터치 이벤트와 동일하게 안드로이드 시스템에서 대신 처리해야 함
      
//      액션 버튼의 '확인' 을 클릭 시 동작할 Intent 정보 설정
//      Intent 에 Broadcast 를 상속받은 ActionReceiver 클래스를 사용하도록 설정, action 이라는 이름의 파라미터에 'OK' 값을 전달
      val okIntent = Intent(this, ActionReceiver::class.java).apply { action = "OK" }
//      PendingIntent 를 사용하여 안드로이드 시스템에서 대신 실행 설정
      val okPendingIntent = PendingIntent.getBroadcast(this, 0, okIntent, PendingIntent.FLAG_IMMUTABLE)
//      안드로이드 상단 알림 화면에 액션 버튼으로 등록
      builder.addAction(android.R.drawable.ic_menu_call, "확인", okPendingIntent)

//      액션 버튼의 '취소' 를 클릭 시 동작할 Intent 정보 설정
      val cancelIntent = Intent(this, ActionReceiver::class.java).apply { action = "CANCEL" }
      val cancelPendingIntent = PendingIntent.getBroadcast(this, 1, cancelIntent, PendingIntent.FLAG_IMMUTABLE)
      builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "취소", cancelPendingIntent)

//      액션 버튼의 '자세히보기' 를 클릭 시 동작할 Intent 정보 설정
      val moreIntent = Intent(this, ActionReceiver::class.java).apply { action = "MORE" }
      val morePendingIntent = PendingIntent.getBroadcast(this, 2, moreIntent, PendingIntent.FLAG_IMMUTABLE)
      builder.addAction(android.R.drawable.ic_menu_info_details, "자세히 보기", morePendingIntent)

//      안드로이드 시스템 상단 알림 실행
      manager.notify(notificationId, builder.build())
    }
    
    
//    상단 알림에서 원격 메시지 전달
    binding.btnNotificationRemote.setOnClickListener {
      val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
      val notificationId = 13
      val builder: NotificationCompat.Builder
      val channelId = "원격 입력 채널 ID"
      val channelName = "원격 메시지 입력"
      val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
      val audioAttr = AudioAttributes.Builder()
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
        .build()

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH).apply {
          description = "상단 알림 원격 메시지 입력"
          setShowBadge(true)
          setSound(soundUri, audioAttr)
          enableLights(true)
          lightColor = Color.WHITE
          enableVibration(true)
          vibrationPattern = longArrayOf(100, 200, 100, 200)
        }

        manager.createNotificationChannel(channel)
        builder = NotificationCompat.Builder(this, channelId)
      }
      else {
        builder = NotificationCompat.Builder(this)
      }

      builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        .setContentTitle("상단 알림 app_20250801")
        .setContentText("상단 알림에서 메시지를 원격으로 전달할 수 있음")
        .setWhen(System.currentTimeMillis())
        .setAutoCancel(true)

//      원격 메시지 입력을 위한 부분
//      원격 입력 시 사용할 키 설정, 원격 입력 시 사용자의 브로드캐스트 리시버에서 해당 값으로 확인
      val receiveKeyName = "fullstack502-receiver"
//      RemoteInput 객체 생성 시 receiveKeyName 을 사용하여 생성
      val remoteInput: RemoteInput = RemoteInput.Builder(receiveKeyName).run {
        setLabel("응답")
        build()
      }

//      액션 버튼과 동일하게 Intent 생성, 백그라운드에서 동작할 사용자 브로드 캐스트 리시버를 등록
      val receiverIntent = Intent(this, RemoteInputReceiver::class.java)
//      액션 버튼과 동일하게 PendingIntent 를 getBroadcast() 를 사용하여 생성, flag 값을 FLAG_MUTABLE 로 설정
      val receiverPendingIntent = PendingIntent.getBroadcast(this, 100, receiverIntent,
        PendingIntent.FLAG_MUTABLE)
//      NotificationCompat 에 저장할 RemoteInput 내용을 설정
      val remoteAction = NotificationCompat.Action.Builder(android.R.drawable.ic_menu_send, "응답", receiverPendingIntent).addRemoteInput(remoteInput).build()

      builder.addAction(remoteAction)

      manager.notify(notificationId, builder.build())
    }

//    상단 알림에 프로그레스바 사용
    binding.btnNotificationProgress.setOnClickListener {
      val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
      val notificationId = 14
      val builder: NotificationCompat.Builder
      val channelId = "상단 알림 프로그레스바 채널 ID"
      val channelName = "상단 알림에서 프로그레스바 동작"
      val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
      val audioAttr = AudioAttributes.Builder()
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
        .build()

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH).apply {
          description = "상단 알림에서 프로그레스바 사용하기"
          setShowBadge(true)
          setSound(soundUri, audioAttr)
          enableLights(true)
          lightColor = Color.WHITE
          enableVibration(true)
          vibrationPattern = longArrayOf(100, 200, 100, 200)
        }

        manager.createNotificationChannel(channel)
        builder = NotificationCompat.Builder(this, channelId)
      }
      else {
        builder = NotificationCompat.Builder(this)
      }

      builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        .setContentTitle("상단 알림 app_20250801, 다운로드 중")
        .setWhen(System.currentTimeMillis())
//        자동 닫기를 막기 위해서 setAutoCancel(), setOngoing() 을 설정
        .setAutoCancel(false)
        .setOngoing(true)

//      상단 알림에 프로그레스바 출력
//      setProgress() : 상단 알림에 프로그레스바를 출력하기 위한 설정
//      첫번째 매개변수 : 프로그레스바의 최대값
//      두번재 매개변수 : 프로그레스바의 현재값
//      프로그레스바가 움직이려면 스레드를 사용하여 두번째 매개변수 값을 계속 변경 시켜야 함
//      builder.setProgress(100, 50, false)

//      for 문을 사용하여 프로그레스바의 값을 변경
      Thread {
        for (i in 1..100 step 10) {
//          현재 프로그레스바의 값을 수정
          builder.setProgress(100, i, false)
//          상단 알림을 요청
          manager.notify(notificationId, builder.build())
//          0.5초 동안 for문을 정지
          SystemClock.sleep(500)
        }

//        프로그레스바의 최대값에 도달하면 상단 알림의 내용을 수정
        builder.setContentText("다운로드 완료")
//        프로그레스바의 최대값과 현재값을 0 으로 수정하여 프로그레스바 삭제
        builder.setProgress(0, 0, false)
//        상단 알림 자동 닫기로 설정
        builder.setAutoCancel(true)
        builder.setOngoing(false)
//        수정된 상단 알림 화면으로 출력
        manager.notify(notificationId, builder.build())
      }.start()
    }

//    상단 알림 스타일 변경하기
    binding.btnNotificationStyle.setOnClickListener {
      val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
      val notificationId = 15
      val builder: NotificationCompat.Builder
      val channelId = "상단 알림 스타일변경 ID"
      val channelName = "상단 알림 스타일 변경하기"
      val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
      val audioAttr = AudioAttributes.Builder()
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
        .build()

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH).apply {
          description = "상단 알림 스타일 변경하기"
          setShowBadge(true)
          setSound(soundUri, audioAttr)
          enableLights(true)
          lightColor = Color.WHITE
          enableVibration(true)
          vibrationPattern = longArrayOf(100, 200, 100, 200)
        }

        manager.createNotificationChannel(channel)
        builder = NotificationCompat.Builder(this, channelId)
      }
      else {
        builder = NotificationCompat.Builder(this)
      }

      builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        .setContentTitle("상단 알림 스타일 변경하기")
        .setContentText("상단 알림에 여러가지 스타일 적용")
        .setWhen(System.currentTimeMillis())
        .setAutoCancel(true)

//      상단 알림에 스타일 적용 시 4가지 방식의 스타일을 적용할 수 있음
//      setStyle() 에 스타일 객체를 매개변수로 입력하여 스타일 적용
      
//      큰 이미지 스타일
//      BitmapFactory : 안드로이드에서 이미지를 사용하기 위한 클래스
//      Options() : 이미지를 로딩하는 방식 설정 (크기, 색상, 품질 등)
//      inSampleSize : 2의 제곱으로 입력, 사용할 이미지를 축소하여 메모리에 로딩, 2/4/8 등
//      val option = BitmapFactory.Options().apply {
//        inSampleSize = 2
//      }
//
////      리소스에 있는 이미지를 가져옴
//      val bigPicture = BitmapFactory.decodeResource(resources, R.drawable.mumin, option)
////      NotificationCompat의 BigPictureStyle() 타입 객체를 생성, 사용할 이미지를 적용
//      val bigPictureStyle = NotificationCompat.BigPictureStyle()
//        .bigPicture(bigPicture)
//
////      설정된 큰 이미지 스타일을 Notification 객체에 적용
//      builder.setStyle(bigPictureStyle)

//      긴 문자열 스타일
//      NotificationCompat의 BigTextStyle 타입 객체를 생성, 긴 문자열 데이터를 입력
//      val bigTextStyle = NotificationCompat.BigTextStyle()
//        .bigText(resources.getString(R.string.alert_notification_long_text))

//      설정된 긴 문자열 스타일을 Notification 객체에 적용
//      builder.setStyle(bigTextStyle)

//      박스 스타일
      val boxStyle = NotificationCompat.InboxStyle()
        .addLine("1과목 : html, css, javascript")
        .addLine("2과목 : java")
        .addLine("3과목 : database")
        .addLine("4과목 : JSP")
        .addLine("5과목 : Spring")
        .addLine("6과목 : Kotlin Android")

      builder.setStyle(boxStyle)

//      메시지 스타일
//      NotificationCompat의 MessagingStyle().Message() 메소드를 사용하여 출력할 메시지를 생성
//      MessagingStyle() 객체에 생성한 메시지를 등록
//      Notification 객체에 MessagingStyle 을 등록
      
//      채팅창 형태이므로 사용자 객체를 생성
      val sender1: Person = Person.Builder()
        .setName("사용자 1")
        .setIcon(IconCompat.createWithResource(this, R.mipmap.ic_launcher))
        .build()

      val sender2: Person = Person.Builder()
        .setName("사용자 2")
        .setIcon(IconCompat.createWithResource(this, R.mipmap.ic_launcher_round))
        .build()

//      출력할 메시지를 생성
      val msg1 = NotificationCompat.MessagingStyle.Message("안녕하세요", System.currentTimeMillis(), sender1)
      val msg2 = NotificationCompat.MessagingStyle.Message("반갑습니다.", System.currentTimeMillis(), sender2)

//      MessagingStyle() 객체 생성 및 출력할 메시지 등록
      val messageStyle = NotificationCompat.MessagingStyle(sender1)
        .addMessage(msg1)
        .addMessage(msg2)

//      Notification 객체에 생성된 스타일을 적용
      builder.setStyle(messageStyle)

      manager.notify(notificationId, builder.build())
    }

//    툴바를 사용한 엑티비티 화면
    binding.btnToolbar.setOnClickListener {
      val intent = Intent(this, ToolbarActivity::class.java)
      startActivity(intent)
    }
  }
}












