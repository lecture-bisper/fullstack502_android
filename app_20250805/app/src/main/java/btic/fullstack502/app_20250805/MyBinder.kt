package btic.fullstack502.app_20250805

import android.os.Binder

class MyBinder(var msg: String) : Binder() {

  fun sendData(msg: String) {
    this.msg = msg
  }
}









