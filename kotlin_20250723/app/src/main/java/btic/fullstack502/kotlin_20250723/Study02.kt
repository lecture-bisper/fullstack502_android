package btic.fullstack502.kotlin_20250723

import android.util.Log.i
import java.time.DayOfWeek
import java.time.LocalDate

fun main() {

  println("\n ----- 코틀린의 반복문 ----- \n")
  println("\n ----- for 문 -----")
  
//  코틀린의 반복문 중 for 문은 자바의 향상된 for 문과 같음
//  in : 지정한 배열 혹은 리스트에서 데이터를 하나씩 출력하면서 반복
//  .. : 지정한 시작 숫자부터 종료 숫자까지 1씩 증가하면서 반복
//  until : 지정한 시작 숫자부터 종료 숫자까지 1씩 증가하면서 반복, 종료 숫자는 포함하지 않음
//  step : 반복 시 증가시킬 숫자의 크기를 지정
//  downTo : 반복 시 숫자가 감소함, 감소할 크기를 설정

  val list1 = listOf(10, 20, 30, 40, 50)

  println("----- 리스트 list1 의 값 출력 -----")

  for (i in list1) {
    print("$i, ")
  }

  println("\n----- in .. 사용 -----")

  var sum1 = 0

  for (i in 1..10) {
    sum1 += i
    println("i : $i, sum1 : $sum1")
  }

  println("\n----- until 사용 -----")
  sum1 = 0

  for (i in 1 until 10) {
    sum1 += i
    println("i : $i, sum1 : $sum1")
  }

  println("\n----- step 사용 -----")
  for (i in 0..10 step 2) {
    println("i : $i")
  }

  println("\n----- downTo 사용 -----")
  for (i in 10 downTo 0) {
    println("i : $i")
  }

  println("\n----- downTo step 사용 -----")
  for (i in 10 downTo 0 step 2) {
    println("i : $i")
  }


  println("\n ----- indices / withIndex() 사용 ----- \n")
  
//  indices : 배열과 같은 컬렉션이 가지고 있는 index를 출력
//  withIndex() : 배열과 같은 컬렉션이 가지고 있는 index와 value 를 동시에 출력

  val arrInt = arrayOf(10, 20, 30, 40, 50)

//  지정한 배열에서 데이터를 하나씩 꺼내어 반복 실행
  for (i in arrInt) {
    println("arrInt 가 가지고 있는 값 : $i")
  }

  println()

//  indices 를 사용 시 for 문 안에 있는 변수 i 에 저장되는 데이터가 지정한 배열의 index 번호임
  for (i in arrInt.indices) {
    println("현재 i : $i, arrInt[$i] 의 값 : ${arrInt[i]}")
  }
  
  println()

  for ((index, value) in arrInt.withIndex()) {
    println("index : $index, value : $value")
  }
  
  println("\n ----- while 사용하기 ----- \n")

//  코틀린의 while 문은 자바의 while 문과 동일함
  var count = 1
  var total = 0

  while (count < 11) {
    total += count
    println("count : $count, total : $total")
    count++
  }
  
//  문제 1) 코틀린의 for 문을 사용하여 구구단을 모두 출력하는 프로그램을 작성하세요
//  2단 ~ 9단까지
//  출력 형태 :
//  --- 2단 ---
//  2 * 1 = 2
//    ...
//  2 * 9 = 18
//  --- 3단 ---
//    ...
//  --- 9단 ---
//    ...
//  9 * 9 = 81

  println("\n ----- 문제 1 ----- \n")

  for (i in 2..9) {
    println("--- $i 단 ---")
    for (j in 1 until 10) {
      println("$i * $j = ${i * j}")
    }
  }

//  문제 2) 현재 날짜를 받아와서 현재 날짜의 요일을 출력하세요
//  현재 날짜는 LocalDateTime 혹은 LocalDate 를 이용
//  요일은 DayOfWeek 를 이용

  println("\n ----- 문제 2 ----- \n")

  val currentDay = LocalDate.now() // 현재 날짜받아오기
  val day = currentDay.dayOfWeek // 현재 날짜를 기준으로 한 주의 요일 받아오기

  println("날짜 : $currentDay")

//  when 을 통해서 요일에 맞는 내용 출력
  when (day) {
    DayOfWeek.MONDAY -> println("월요일 입니다.")
    DayOfWeek.TUESDAY -> println("화요일 입니다.")
    DayOfWeek.WEDNESDAY -> println("수요일 입니다.")
    DayOfWeek.THURSDAY -> println("목요일 입니다.")
    DayOfWeek.FRIDAY -> println("금요일 입니다.")
    DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> println("주말 입니다.")
  }

}









