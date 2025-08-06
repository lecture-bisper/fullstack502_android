package btic.fullstack502.app_20250805

import android.os.Binder

//  외부 클래스로 선언한 MP3Binder 클래스
class MP3Binder(private val service: MP3Service) : Binder() {

  fun getService(): MP3Service {
    return service
  }
}









