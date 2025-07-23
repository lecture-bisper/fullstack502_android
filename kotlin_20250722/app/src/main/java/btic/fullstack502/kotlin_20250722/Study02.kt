package btic.fullstack502.kotlin_20250722

fun main() {

  println("\n ----- function ----- \n")

//  코틀린의 함수 선언하기
//  기본적으로 자바의 메소드 선언 방법과 동일함
//  'fun' 키워드를 사용하여 함수를 선언
//  함수의 반환 타입을 매개변수를 입력하기 위한 () 뒤에 ' : ' 함께 사용
//  함수명의 () 와 코드 블럭인 {} 사이에 ' : 반환 타입 ' 을 입력함
//  코틀린의 함수는 클래스에 포함되지 않고 사용할 수 있음

//  사용법
//  fun 함수명 (매개변수1: 데이터타입, 매개변수2: 데이터타입, ...) : 반환타입 {
//    함수 호출 시 실행할 소스코드 ...
//    return 반환값
//  }

//  함수 호출
  function1()
  function11()
  function2(10, 20)
  function22(10, 20)
  function3()
  function4(10, 20)

  println("\n ---------- \n")

  function5("아이유", 31, false)
  function5("아이유", 31)
  function6("ui", true, 32)
//  중간 매개변수에 기본값을 사용했을 경우 해당 매개변수의 값을 지정하지 않으면 기본값을 사용하는 것인지 해당 위치에 데이터를 입력하는 것인지 컴파일러가 판단할 수 없음
//  function6("ui", 32)
  
//  함수 호출 시 매개변수의 이름을 직접 지정하여 데이터를 전달 가능
//  호출할 매개변수의 선언부에 입력되어 있는 매개변수명을 입력하여 데이터 전달
//  매개변수의 이름을 지정하여 데이터를 전달하기 때문에 매개변수의 순서가 상관없음
  function5(name = "아이유", age = 31, gender = false)
  
//  매개변수의 순서를 변경하여 호출
  function5(age = 31, gender = false, name = "아이유")
  
//  매개변수의 순서를 변경하고 기본값을 사용하여 호출
//  기본값이 설정된 매개변수가 중간에 있어도 매개변수의 이름을 지정하여 데이터를 전달하면 문제가 없음
  function6(age = 31, name = "아이유")

}

//  반환값과 매개변수가 모두 없는 함수
fun function1(): Unit {
  println("반환값과 매개변수가 모두 없는 함수")
}

//  반환값과 매개변수가 모두 없는 함수, Unit 생략
fun function11() {
  println("반환값과 매개변수가 모두 없는 함수")
}

//  매개변수 사용 시 var, val 키워드 미입력, 기본적으로 val 로 적용
//  반환값은 없고, 매개변수는 존재하는 함수
fun function2(num1: Int, num2: Int): Unit {
  println("반환값은 없고, 매개변수는 존재하는 함수")
  println("num1: $num1, num2: $num2")
//  매개변수는 val 로 선언된 변수이기 때문에 수정 불가
//  num1 = 100
}

fun function22(num1: Int, num2: Int) {
  println("반환값은 없고, 매개변수는 존재하는 함수")
  println("num1: $num1, num2: $num2")
}

//  반환값은 존재하고, 매개변수는 없는 함수
fun function3(): Int {
  println("반환값은 존재하고, 매개변수는 없는 함수")
  val num1 = 10
  val num2 = 20
  val result = num1 + num2
  return result
}

//  반환값과 매개변수가 모두 존재하는 함수
fun function4(num1: Int, num2: Int): Int {
  println("반환값과 매개변수가 모두 존재하는 함수")
  val result = num1 + num2
  return result
}

//  함수의 매개변수에 기본값 설정
fun function5(name: String, age: Int, gender: Boolean = true) {
  if (gender) {
    println("이름 : $name\n나이 : $age\n성별 : 남성")
  }
  else {
    println("이름 : $name\n나이 : $age\n성별 : 여성")
  }
}

//  함수의 매개변수에 기본값 사용 시 기본값이 설정된 매개변수는 가장 마지막에 입력
//  기본값이 있는 매개변수를 매개변수의 순서 중간에 설정 시 해당 함수를 호출할 경우 기본값을 가지고 있는 매개변수를 생략하면 매개변수에 전달하는 값의 위치를 알 수 없기 때문에 오류 발생
fun function6(name: String, gender: Boolean = true, age: Int) {
  if (gender) {
    println("name : $name\nage : $age\ngender : 남성")
  }
  else {
    println("name : $name\nage : $age\ngender : 여성")
  }
}












