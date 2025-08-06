package btic.fullstack502.app_20250805

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250805.databinding.ActivityMp3PlayerBinding

class MP3PlayerActivity : AppCompatActivity() {

//  ViewBinding 방식으로 ui xml 파일의 태그를 모두 가져오기
//  지연 초기화 방식 사용
  private val binding by lazy { ActivityMp3PlayerBinding.inflate(layoutInflater) }

//  지연 초기화 방식 사용, MP3 파일을 재생하기 위한 MP3Service 타입의 객체 생성
  private lateinit var mp3PlayerService: MP3Service
//  bindService() 를 통해서 서비스를 제어하기 위한 ServiceConnection 객체 생성, 지연초기화 방식 사용
  private val mp3PlayerServiceConnection by lazy {
    val connection = object: ServiceConnection {
      override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        mp3PlayerService = (service as MP3Binder).getService()
//        mp3PlayerService = (service as MP3Service.MP3Binder).getService()
      }

      override fun onServiceDisconnected(name: ComponentName?) {
        TODO("Not yet implemented")
      }
    }

//  by lazy 지연 초기화 방식 사용 시 by lazy의 가장 끝에 있는 변수는 반환됨
    connection
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

//    MP3Service 를 안드로이드 시스템에 등록하기 위한 Intent 객체 생성
    val mp3Intent = Intent(this, MP3Service::class.java)
//    bindService() 를 사용하여 MP3Service 를 동작
    bindService(mp3Intent, mp3PlayerServiceConnection, Context.BIND_AUTO_CREATE)

//    버튼 이벤트를 한번에 등록하는 함수
    initEventListener()
  }

  private fun initEventListener() {
    binding.btnPlay.setOnClickListener {
      mp3PlayerService.play()
    }

    binding.btnPause.setOnClickListener {
      mp3PlayerService.pause()
    }

    binding.btnStop.setOnClickListener {
      mp3PlayerService.stop()
    }
  }
}










