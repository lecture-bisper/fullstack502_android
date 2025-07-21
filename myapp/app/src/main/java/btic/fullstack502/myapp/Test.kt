package btic.fullstack502.myapp

import java.util.Date

var data = 10

//  코틀린은 파일명과 클래스명이 일치하지 않아도 상관없음
//  코틀린은 객체지향만을 위한 언어가 아니기 때문에 함수 및 변수를 파일의 최상단에 입력할 수 있음

//  코틀린의 시작 함수, main()
fun main() {
  data = 20
  println("변수 data의 값 : " + data)
  formatDate(Date())
  User().sayHello()
}

fun formatDate(date: Date) {
  println("함수 formatDate() 호출")
}

class User {
  fun sayHello() {
    println("User 클래스의 메소드 sayHello() 호출")
  }
}
