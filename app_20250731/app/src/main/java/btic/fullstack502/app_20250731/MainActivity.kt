package btic.fullstack502.app_20250731

import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250731.databinding.ActivityMainBinding
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

//  음원을 재생하기 위한 변수 선언, lateinit 을 사용하여 실제 사용 시 초기화
//  안드로이드 시스템 음원용
  private lateinit var notification: Uri
  private lateinit var ringtone: Ringtone
//  사용자 음원용
  private lateinit var mediaPlayer: MediaPlayer

  @RequiresApi(Build.VERSION_CODES.R)
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

//    토스트 메시지 출력 버튼
    binding.btnToast.setOnClickListener {
      Toast.makeText(this, "토스트 메시지 사용하기", Toast.LENGTH_SHORT).show()
    }

//    토스트 메시지 콜백 기능 사용 버튼
    binding.btnToastCallback.setOnClickListener {
//      미리 생성해 놓은 메소드를 해당 버튼 이벤트 시 사용
      toastCallback()
    }

//    데이트 픽커 사용 버튼
    binding.btnDatePicker.setOnClickListener {
//      DatePickerDialog() : 데이트 픽커 다이얼로그 호출 함수
//      첫번째 매개변수 : 출력할 화면 선택 (현재 엑티비티를 선택, this)
//      두번째 매개변수 : 이벤트 리스너와 구현현된 이벤트 핸들러
//      세번째, 네번째, 다섯번째 : 각각 년도, 월, 일 입력
//        월은 0 ~ 11 까지의 숫자로 표현 (0 = 1월, 1 = 2월, 11 = 12월)
      DatePickerDialog(this, object: DatePickerDialog.OnDateSetListener {
//        onDateSet() : 구현된 이벤트 핸들러, 오버라이딩하여 사용
//        첫번재 매개변수 : 데이터 픽커 클래스, 일종의 디자인이라고 보면 됨
//        나머지 매개변수 : 각각 년도 월 일
//          월은 0 ~ 11 까지의 숫자로 표현 (0 = 1월, 1 = 2월, 11 = 12월)
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
          Log.d("**fullstack502**", "year : $year, month : $month, day : $dayOfMonth")
//          ${month + 1} 의미는 0 부터 출력이므로 사용자 화면에서 확인하기 쉽게 하기 위해서 1 을 더해줌
          Toast.makeText(this@MainActivity, "선택한 날짜 : $year-${month + 1}-$dayOfMonth", Toast.LENGTH_SHORT).show()
        }
//          7 - 1 은 월을 0부터 입력하므로 사용자가 알기 쉬운 값에서 1을 빼줌
      }, 2025, 7 - 1, 31).show()
    }

//    타임 픽커 사용 버튼
    binding.btnTimePicker.setOnClickListener {
//      TimePickerDialog() : 타임 픽커 다이얼로그 호출 함수
//      첫번째 매개변수 : 출력할 화면 선택 (현재 엑티비티를 선택, this)
//      두번째 매개변수 : 이벤트 리스너와 구현된 이벤트 핸들러
//      세번째, 네번째 : 각각 시, 분 을 입력
//      다섯번째 매개변수 : 24시간 시간 표현 여부 입력, true = 24시간, false = 오전, 오후 12시간
      TimePickerDialog(this, { view, hourOfDay, minute ->
        Log.d("**fullstack502**", "time - $hourOfDay, minute : $minute")
        Toast.makeText(this@MainActivity, "선택한 시간 - $hourOfDay : $minute", Toast.LENGTH_LONG).show()
      }, 11, 44, false).show()
    }

//    알림 다이얼로그 사용하기
    binding.btnAlertDialog.setOnClickListener {
//      AlertDialog() : 알림 다이얼로그 호출 함수
//        빌더 패턴을 사용하여 알림 다이얼로그 내용을 설정
      AlertDialog.Builder(this).run {

//        알림 다이얼로그의 버튼 클릭 시 동작할 내용을 설정
        val eventHandler = object : DialogInterface.OnClickListener {
//          onClick() : 버튼 클릭 시 실행될 함수, 오버라이딩하여 사용
//          두번째 매개변수 : 사용자가 실제로 클릭한 버튼의 정보
          override fun onClick(dialog: DialogInterface?, which: Int) {
//            확인 버튼 클릭 시
            if (which == DialogInterface.BUTTON_POSITIVE) {
//              Toast 의 첫번째 매개변수에 this@MainActivity 인 이유
//              Toast 메시지가 출력되는 부분이 현재 엑티비티이기 때문에 this 를 사용
//              현재는 다이얼로그 가 출력되고 있기 때문에 this 만 입력 시 현재 엑티비티 만을 의미하는 것이 아니게 됨
//              Toast 메시지가 출력되는 위치를 명확하게 하기 위해서 this@MainActivity 를 사용
              Toast.makeText(this@MainActivity, "확인 버튼 클릭", Toast.LENGTH_SHORT).show()
            }
//            취소 버튼 클릭 시
            else if (which == DialogInterface.BUTTON_NEGATIVE) {
              Toast.makeText(this@MainActivity, "취소 버튼 클릭", Toast.LENGTH_SHORT).show()
            }
//            추가 버튼 클릭 시
            else {
              Toast.makeText(this@MainActivity, "추가 버튼 클릭", Toast.LENGTH_SHORT).show()
            }
          }
        }

//        제목 설정
        setTitle("알림 다이얼로그 사용하기")
//        제목 앞의 아이콘 설정
        setIcon(android.R.drawable.ic_dialog_info)
//        알림 다이얼로그의 body 부분의 메시지 설정
        setMessage("알림창을 닫으시겠습니까?")
//        setPositiveButton(), setNegativeButton(), setNeutralButton() 를 설정해야 다이얼로그 화면에 버튼이 출력
//        setPositiveButton(), setNegativeButton(), setNeutralButton() 를 여러번 입력 시 가장 마지막에 있는 설정으로 동작
//        확인 버튼 설정, 첫번째 매개변수로 표시할 텍스트 설정, 두번째 매개변수로 클릭 시 동작할 이벤트 핸들러를 설정
//        버튼 클릭 시 추가 동작이 필요없을 경우 이벤트 핸들러 대신 null 사용
        setPositiveButton("OK", eventHandler)
        setNegativeButton("Cancel", eventHandler)
        setNeutralButton("More", eventHandler)

//        setPositiveButton("확인", eventHandler)
//        setNegativeButton("취소", eventHandler)

//        다이얼로그 화면 출력
        show()
      }
    }

//    알림 다이얼로그 목록 사용하기
    binding.btnAlertDialogList.setOnClickListener {
//      알림 다이얼로그 화면에 출력할 데이터 배열 생성
      val items = arrayOf("사과", "배", "복숭아", "수박", "참외", "포도")

//      기존의 알림 다이얼로그와 기본적으로 같음, setItems() 가 추가되어 있음
      AlertDialog.Builder(this).run {
        setIcon(android.R.drawable.ic_dialog_info)
//        미리 설정해 놓은 strings.xml 의 내용을 가져와서 사용
        setTitle(resources.getString(R.string.alert_dialog_list))
//        setItems() : 알림 다이얼로그의 body 부분에 일반 텍스트가 아닌 목록을 출력하는 메소드
//        첫번째 매개변수 : 화면에 출력할 배열을 입력
//        두번째 매개변수 : 출력된 목록을 선택 시 동작할 이벤트, 이벤트 리스너, 이벤트 핸들러
        setItems(items, object : DialogInterface.OnClickListener {
//          onClick() : 출력된 목록 중 요소를 하나 클릭 시 동작하는 메소드
//          두번째 매개변수 : 사용자가 클릭한 목록의 index 번호를 전달받음, 0 부터 시작
          override fun onClick(dialog: DialogInterface?, which: Int) {
            Log.d("**fullstack502**", "선택한 과일 : ${items[which]}")
            Toast.makeText(this@MainActivity, "선택한 과일 : ${items[which]}", Toast.LENGTH_SHORT).show()
          }
        })
        setPositiveButton("닫기", null) // 버튼의 이벤트가 필요없을 경우 null 사용
        show()
      }
    }

//    알림 다이얼로그 체크박스 사용하기
    binding.btnAlertDialogCheckbox.setOnClickListener {
//      화면에 출력할 체크박스의 목록 생성하기
      val items = arrayOf("사과", "배", "복숭아", "수박", "참외", "포도")

//      알림 다이얼로그 설정
//      기존 알림 다이얼로그 목록 사용하기와 기본적으로 동일함, setItmes() 대신 setMultiChoiceItems() 를 사용
      AlertDialog.Builder(this).run {
        setIcon(android.R.drawable.ic_dialog_info)
        setTitle(resources.getString(R.string.alert_dialog_checkbox))
//        setMultiChoiceItems() : 알림 다이얼로그에 체크 박스 목록을 추가하는 메소드
//        첫번째 매개변수 : 화면에 출력할 체크박스 목록 데이터
//        두번째 매개변수 : 지정한 체크박스 목록의 기본 체크 여부 설정
//        세번째 매개변수 : 이벤트 리스너와 이벤트 핸들러 설정 및 구현
        setMultiChoiceItems(items, booleanArrayOf(false, true, false, true, true, false), object : DialogInterface.OnMultiChoiceClickListener {
//          onClick() : 사용자가 화면에 출력된 체크 박스의 요소를 클릭했을 경우 동작하는 메소드
//          두번째 매개변수 : 사용자가 클릭한 체크 박스 목록의 index 번호
//          세번째 매개변수 : 해당 index의 체크 박스 체크 여부
          override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
            Log.d("**fullstack502**", "${items[which]} 이 ${if(isChecked) "선택되었습니다." else "선택 해제 되었습니다."}")
            Toast.makeText(this@MainActivity, "${items[which]} 이 ${if(isChecked) "선택되었습니다." else "선택 해제 되었습니다."}", Toast.LENGTH_SHORT).show()
          }
        })
        setPositiveButton("닫기", null)
        show()
      }
    }

//    알림 다이얼로그 라디오버튼 사용하기
    binding.btnAlertDialogRadio.setOnClickListener {
//      화면에 출력할 라디오버튼의 목록 데이터 생성
      val items = arrayOf("사과", "배", "복숭아", "수박", "참외", "포도")

//      알림 다이얼로그 설정
//      기본적으로 알림 다이얼로그 체크박스와 같음
      AlertDialog.Builder(this).run {
        setIcon(android.R.drawable.ic_dialog_info)
        setTitle("알림 다이얼로그에 라디오 버튼 사용")
//        setSingleChoiceItems() : 알림 다이얼로그에 라디오버튼 목록을 추가하는 메소드
//        첫번째 매개변수 : 알림 다이얼로그에 출력할 라디오버튼 목록 데이터
//        두번째 매개변수 : 알림 다이얼로그 라디오버튼 출력 시 기본적으로 선택할 index 번호
//        세번째 매개변수 : 이벤트 리스너와 이벤트 핸들러 설정 및 구현
        setSingleChoiceItems(items, 0, object : DialogInterface.OnClickListener {
//          onClick() : 사용자가 화면에 출력된 라디오버튼의 요소를 클릭했을 경우 동작하는 메소드
//          두번째 매개변수 : 사용자가 클릭한 라디오버튼 목록의 index 번호
          override fun onClick(dialog: DialogInterface?, which: Int) {
            Log.d("**fullstack502**", "${items[which]} 이 선택되었습니다.")
            Toast.makeText(this@MainActivity, "${items[which]} 이 선택되었습니다.", Toast.LENGTH_SHORT).show()
          }
        })
        setPositiveButton("닫기", null)
//        setCancelable() : 알림 다이얼로그의 확인/취소 버튼을 눌러야지만 알림 다이얼로그를 닫게 하는 설정, 기본값 true
        setCancelable(false)
        show()
//        setCanceledOnTouchOutside() : 알림 다이얼로그의 버튼을 제외한 다른 화면을 클릭 시 자동 취소를 하지않기 위한 설정, 기본값 true
      }.setCanceledOnTouchOutside(false)
    }

//    안드로이드 시스템의 음원을 사용한 사운드 알림 사용하기
//    일반 변수로 선언하여 출력 중인 사운드 객체를 1개로 제한
//    안드로이드 시스템의 RingtoneManager 를 사용하여 안드로이드 기본 설정 음원을 출력하도록 설정
//    var notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
//    지정한 음원을 출력할 안드로이드 시스템에 전달, 현재 실행되고 있는 앱을 의미
//    var ringtone = RingtoneManager.getRingtone(applicationContext, notification)

    binding.btnAlertSound.setOnClickListener {
      Toast.makeText(this, "링톤 매니저를 이용하여 시스템 사운드 출력", Toast.LENGTH_SHORT).show()

//      현재 RingtoneManager 를 사용한 음원이 재생되고 있는지 확인
      if (ringtone.isPlaying) {
        ringtone.stop()
      }
      else {
        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(applicationContext, notification)
        ringtone.play()
      }
    }

//    MediaPlayer 를 사용하여 사용자 알림 음원 출력하기
    binding.btnMediaPlayer.setOnClickListener {
      Toast.makeText(this, "Media Player 를 이용하여 사용자 음원 재생", Toast.LENGTH_SHORT).show()

      mediaPlayer = MediaPlayer.create(this, R.raw.tracehone)
      mediaPlayer.start()
    }

//    MediaPlayer 를 사용하여 출력 중인 사용자 알림 음원 정지하기
    binding.btnMediaPlayerStop.setOnClickListener {
      Toast.makeText(this, "Media Player 를 이용하여 재생 중인 사용자 음원 정지", Toast.LENGTH_SHORT).show()

      mediaPlayer.stop()
    }

//    진동 알림 사용하기
    binding.btnVibrator.setOnClickListener {
//      현재 스마트폰의 안드로이드 OS 버전이 지정한 API 버전과 같거나 높은지 확인, VibratorManager 는 API 31 부터 지원
//      사용중인 안드로이드 OS 버전에 따라 진동 서비스 객체를 가져옴
      val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//        최신 버전 진동 서비스
        val vibratorManager = this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
      }
      else {
//        예전 버전 진동 서비스
        getSystemService(VIBRATOR_SERVICE) as Vibrator
      }

//      현재 스마트폰의 안드로이드 OS 버전이 지정한 API 버전과 같거나 높은지 확인, API 26 버전부터 사용 방식이 변경됨
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        vibrate() : API 26 버전 이상일 경우 사용하는 함수, VIBRATOR_MANAGER_SERVICE 가 제공
//        VibrationEffect : 진동의 세기 및 패턴을 제어할 수 있는 클래스
//        createOneShot() : 동일한 형태의 진동 효과를 사용하는 함수
//        vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
        
//        createWaveform() : 지정한 배열에 따라 진동 패턴 및 진동의 세기를 조절하여 진동 알림을 사용할 수 있는 함수
        vibrator.vibrate(VibrationEffect.createWaveform(longArrayOf(500, 1000, 500, 2000), intArrayOf(0, 50, 0, 200), -1))
      }
      else {
//        vibrate() : 예전 버전에서 사용하는 함수, VIBRATOR_SERVICE 가 제공
//        vibrator.vibrate(1000)
//        vibrate() 에 long 타입 배열 사용 시 사용자 진동 패턴을 사용할 수 있음
        vibrator.vibrate(longArrayOf(500, 1000, 500, 2000), -1)
      }
    }

//    안드로이드 상단 알림창 사용하기
    binding.btnNotification.setOnClickListener {
//      안드로이드 시스템에서 NotificationManager 타입의 객체를 가져옴, NOTIFICATION_SERVICE 가 제공
      val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//      NotificationCompat 타입 변수 선언, 현재는 빈 변수
      val builder: NotificationCompat.Builder

//      현재 사용중인 스마트폰의 안드로이드 버전 확인, API 33 이상일 경우 채널 사용
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val channelId = "one-channel" // 사용자가 정하는 채널 id
        val channelName = "My One Channel" //  사용자가 정하는 채널 이름
//        NotificationChannel 생성
//        IMPORTANCE_HIGH : 긴급 알림, 알림음 출력, 화면 상단에 표시
//        IMPORTANCE_DEFAULT : 높은 중요도의 알림, 알림음 출력
//        IMPORTANCE_LOW : 중간 중요도의 알림, 알림음 없음
//        IMPORTANCE_MIN : 낮은 중요도의 알림, 알림음 없음, 상태바에 표시 안됨
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)

        channel.description = "알림 채널 설명"
//        앱 아이콘에 알림 숫자 설정
        channel.setShowBadge(true)

//        알림음 출력 설정
//        RingtoneManager 를 사용하여 알림음을 안드로이드 시스템 음원으로 설정
        val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//        setContentType() : 알림, 벨소리, 시스템 음원 등의 위한 오디오 유형 설정
//        CONTENT_TYPE_SONIFICATION : 알림 및 시스템 음원
//        CONTENT_TYPE_MUSIC : 음악 앱 및 스트리밍 서비스
//        CONTENT_TYPE_SPEECH : TTS 및 음성 메시지
//        CONTENT_TYPE_MOVIE : 동영상 플레이어
//        CONTENT_TYPE_UNKNOWN : 특정 카테고리가 없는 오디오
        val audioAttributes = AudioAttributes.Builder()
          .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
          .setUsage(AudioAttributes.USAGE_ALARM)
          .build()
        
//        알림음 사용 설정, 음원 리소스 위치와 음원 출력 설정값
        channel.setSound(uri, audioAttributes)
//        알림 시 플래시 사용 설정
        channel.enableLights(true)
        channel.lightColor = Color.RED
//        알림 시 진동 사용 설정
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

//        시스템 서비스를 사용하여 미리 설정해 놓은 NotificationChannel 정보를 등록
        manager.createNotificationChannel(channel)

//        NotificationCompat 객체를 Builder() 사용하여 생성
        builder = NotificationCompat.Builder(this, channelId)
      }
      else {
//        API 33 이전 버전일 경우 채널 정보없이 NotificationCompat 객체 생성
        builder = NotificationCompat.Builder(this)
      }

//      상단 알림창의 아이콘 설정
      builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
//      상단 알림창의 알림 발생 시간 설정
      builder.setWhen(System.currentTimeMillis())
//      상단 알림창의 제목
      builder.setContentTitle("상단 알림창 제목")
//      상단 알림창의 내용
      builder.setContentText("상단 알림창 내용")
//      상단 알림창 클릭 시 아이콘에 존재하는 알림 숫자를 제거
      builder.setAutoCancel(true)

//      안드로이드 시스템 서비스에 상단 알림창 출력 요청
//      첫번째 매개변수 : 안드로이드 시스템 서비스에 알림창 출력 요청 시 사용하는 ID, 사용자가 정할 수 있음
//      두번째 매개변수 : Notification 객체, NotificationCompat.Builder() 를 통해서 Notification 객체를 생성
      manager.notify(11, builder.build())
    }
  }

//  어노테이션을 사용하여 버전 호환성 처리
//  @RequiresApi(Build.VERSION_CODES.R)
  private fun toastCallback() {

//    사용할 토스트 메시지 설정
    val toast = Toast.makeText(this, "토스트 메시지 콜백 기능 사용하기", Toast.LENGTH_SHORT)
  
//    Toast의 콜백 기능은 API 30 부터 제공
//    현재 프로젝트의 최소 지원 API 를 29로 지정하여 호환성 문제가 발생할 수 있음
  
//    호환성 문제 해결 방법 2가지
//    1. 어노테이션을 사용하여 사용할 수 있는 API 버전을 설정
//    2. if 문을 사용하여 지정한 버전 이상일 경우에만 실행하도록 설정 (추천)

//    if 문을 사용하여 버전 호환성 처리
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      toast.addCallback(
        object : Toast.Callback() {
//          토스트 메시지가 출력되었다가 사라질 때 자동 동작
          override fun onToastHidden() {
            super.onToastHidden()
            Log.d("full stack 502", "토스트 메시지 숨김")
          }

//          토스트 메시지가 출력 전에 자동 동작
          override fun onToastShown() {
            super.onToastShown()
            Log.d("full stack 502", "토스트 메시지 출력")
          }
        }
      )
    }

    toast.show()
  }
}











