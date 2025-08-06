package btic.fullstack502.app_20250805

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.widget.Toast

//  MP3 파일을 재생하기 위한 사용자 서비스 클래스, Service 클래스를 상속받음
class MP3Service : Service() {

//  음원 파일을 재생하기 위한 안드로이드 기본 제공 클래스
  var mediaPlayer: MediaPlayer? = null
  
//  IBinder 를 상속받아 구현한 MP3Binder 클래스 타입의 객체 생성
  var mp3Binder: MP3Binder = MP3Binder(this)


//  var mp3Binder: MP3Binder = MP3Binder()

//  내장 클래스로 선언한 MP3Binder 클래스
//  현재 클래스 내부에서만 사용함, 현재 클래스의 내장 클래스이므로 현재 클래스의 멤버이기 때문에 현재 클래스의 객체를 사용할 수 있음
//  inner class MP3Binder: Binder() {
//    fun getService(): MP3Service {
//      return this@MP3Service
//    }
//  }

//  Service 클래스를 상속받은 클래스가 반드시 구현해야 하는 메소드
//  bindService() 로 서비스 실행 시 실행되는 메소드
  override fun onBind(intent: Intent): IBinder {
    return mp3Binder
  }

//  MP3 파일 재생을 위한 메소드
  fun play() {
    if (mediaPlayer == null) {
//      MediaPlayer 타입의 객체 생성, 매개변수로 재생할 음원 파일의 경로를 받음
      mediaPlayer = MediaPlayer.create(this, R.raw.chocolate)
//      볼륨 조절, 0.0 ~ 1.0 까지 사용
      mediaPlayer?.setVolume(1.0F, 1.0F)
//      루프 설정
      mediaPlayer?.isLooping = true
//      MP3 파일 재생
      mediaPlayer?.start()
    }
    else {
//      현재 음원을 재생 중인지 확인
      if (mediaPlayer!!.isPlaying) {
        Toast.makeText(this, "이미 음악이 재생중입니다.", Toast.LENGTH_SHORT).show()
      }
      else {
        mediaPlayer?.start()
      }
    }
  }

//  음원 재생 일시 정지
  fun pause() {
    mediaPlayer?.let {
      if (it.isPlaying) {
        it.pause()
      }
      else {
        it.start()
      }
    }
  }

//  음원 재생 정지
  fun stop() {
    mediaPlayer?.let {
      if (it.isPlaying) {
        it.stop()
//        재생할 음원의 재생 위치를 처음으로 되돌림
        it.release()
        mediaPlayer = null
      }
    }
  }
}










