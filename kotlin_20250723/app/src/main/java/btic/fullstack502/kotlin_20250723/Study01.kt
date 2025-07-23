package btic.fullstack502.kotlin_20250723

fun main() {

  println("\n ----- 조건문 사용하기 -----\n")
//  조건문 : 코틀린에서 사용하는 조건문은 if 문, when 문을 사용
//  if 문은 자바와 동일함
//  when 은 자바의 switch ~ case 문과 동일함

  var data1 = 10

  if (data1 > 0) {
    println("data1 은 0 보다 크다")
  }
  else {
    println("data1 은 0 보다 작거나 같다")
  }

//  elseif 문도 자바와 동일
  if (data1 > 10) {
    println("data1 은 10 보다 크다")
  }
  else if (data1 > 0 && data1 <= 10) {
    println("data1 은 0 보다 크고 10 보다 작거나 같다")
  }
  else {
    println("data1 은 0 보다 작다")
  }

  println("\n ----- if 문을 변수에 대입하기 -----\n")

//  코틀린에서는 if 문을 변수에 대입할 수 있음
//  if문의 코드블럭 가장 마지막 데이터를 변수에 저장함

  var result = if (data1 > 0) {
    println("data1 은 0 보다 크다")
    true // 변수에 저장될 데이터
  }
  else {
    println("data1 은 0 보다 작거나 같다")
    false // 변수에 저장될 데이터
  }
  println("result : $result")

//  자바에서 사용 시
//  boolean result = false;
//  if (data1 > 0) {
//    println("data1 은 0 보다 크다");
//    result = true;
//  }
//  else {
//    println("data1 은 0 보다 작거나 같다");
//    result = false;
//  }


  println("\n ----- when 사용하기 -----\n")

//  when 은 코틀린에서 사용하는 조건문 중 하나
//  switch ~ case 문과 동일한 역할을 함 (기능이 추가 됨)
//  switch ~ case 문에서 case 부분을 제거하고 바로 값을 입력하는 형태
//  ' : ' 대신 ' -> ' 를 사용하며, break 문도 제거 됨
//  실행할 소스 코드가 1 라인일 경우 {} 도 생략 가능
//  switch ~ case 문의 default 대신 else 를 사용
//  코틀린의 if 문과 같이 변수에 when 문을 대입할 수 있음

//  사용법
//  when (변수) {
//    값1 -> { 실행할 소스 코드 }
//    값2 -> { 실행할 소스 코드 }
//    ...
//    else -> { 모든 조건이 맞지 않을 경우 실행할 소스 코드 }

  var data2 = 20

  when (data2) {
    10 -> {
      println("data2의 값은 10")
    }
    20 -> println("data2의 값은 20")
    else -> { println("data2의 값은 10 도 20 도 아님") }
  }

//  자바에서 사용 시
//  int data2 = 20
//  switch (data2) {
//    case 10:
//    println("data2의 값은 10")
//    break;
//
//    case 20:
//    println("data2의 값은 20")
//    break;
//
//    default:
//    println("data2의 값은 10 도 20 도 아님")
//    break;
//  }

//  when 을 통해서 문자열 비교도 가능
  var data3 = "hello"
  when (data3) {
    "hello" -> println("data3의 값은 hello")
    "world" -> println("data3의 값은 world")
    else -> println("data3의 값은 hello 도 world 도 아님")
  }

//  is : 코틀린의 데이터 타입 확인 연산자, 지정한 데이터 타입이 맞을 경우 true 아니면 false 를 출력
//  값1, 값2 : when 문에서 지정한 값을 1개만 사용하는 것이 아니라 여러개를 지정할 때 사용함, 자바의 swtich ~ case 문에서 case 를 여러개 설정한 후 break 문을 입력하지 않고 사용하는 형태와 동일한 방식
//  in 데이터묶음 : 데이터 묶음에서 데이터를 하나씩 꺼내어 사용하는 연산자
//  값1..값2 : 값1 ~ 값2 까지의 연속된 데이터를 의미하는 연산자
//      1..10 = 1 ~ 10 의 숫자를 의미

  var data4: Any = 10
  when (data4) {
    is String -> println("data4 는 문자열 타입")
    20, 30 -> println("data4 의 값은 20 혹은 30")
    in 1..10 -> println("data4 의 값은 1 ~ 10 까지의 숫자")
    else -> println("data4 는 데이터가 없음")
  }
  
  println("\n ----- 변수에 when 대입하기 -----\n")
  
//  when 문 내부에서 간단한 연산이 가능
//  when 문의 결과를 변수에 저장하는 것이 가능함 (변수에 if 문 대입과 같은 방식)
  
  val data5 = 10
  var whenResult: String = when {
    data5 <= 0 -> "data5 는 0 보다 작거나 같다"
    data5 > 100 -> "data5 는 100 보다 크다"
    else -> {
      "data5 에 해당하는 값이 없음"
    }
  }

  println("whenResult : $whenResult\n----------")

  val data6: Any = 10
  whenResult = when(data6) {
    is String -> {
      println("data6 은 문자열 타입")
      "data6 은 문자열 타입"
    }
    20, 30 -> {
      println("data6 은 20 혹은 30")
      "data6 은 20 혹은 30"
    }
    in 1..10 -> {
      println("data6 은 1 부터 10 까지의 숫자 중 하나")
      "data6 은 1 부터 10 까지의 숫자 중 하나"
    }
    else -> {
      println("data6 에 해당하는 값이 없음")
      "data6 에 해당하는 값이 없음"
    }
  }

  println("whenResult : $whenResult")

}









