package btic.fullstack502.kotlin_20250724

//  람다 함수 : 코틀린에서 제공하는 익명함수 선언 기법
//  람다식을 사용하여 기존 함수의 선언 방법보다 간소화하여 함수를 선언할 수 있음
//  선언한 람다 함수를 변수에 저장하여 변수명으로 해당 함수를 호출하여 사용

//  사용법
//  val 변수명 = {매개변수 -> 실행소스} // 선언
//  변수명(매개변수) // 호출

//  일반적인 함수 선언
fun sum1(no1: Int, no2: Int) : Int {
  return no1 + no2
}

//  람다 함수로 선언
//  호출 시 일반함수와 동일함
val sum2 = {no1: Int, no2: Int -> no1 + no2}


//  매개변수가 있는 람다 함수
val ptr1 = {str1: String, str2: String -> println("출력할 문자 : $str1, $str2")}

//  매개변수가 없는 람다 함수
val ptr2 = { -> println("출력할 문자 : 매개변수가 없어요")}

//  매개변수가 없는 람다 함수 다른 방식, 매개변수가 없으면 '->' 도 생략 가능
val ptr3 = { println("출력할 문자 : 매개변수가 없어요 2") }


//  매개변수가 1개인 람다 함수
val ptr4 = { num: Int -> println("매개변수가 1개인 람다 함수 : $num") }

//  매개변수가 1개인 람다 함수 다른 방식, 데이터 타입이 무엇인지 확인할 수 있을 경우 사용
//  매개변수 선언 시 '변수명: 데이터 타입' 의 데이터 타입 부분에 '(데이터 타입) -> Unit' 형태로 사용하여 해당 변수의 타입이 함수 타입임을 알려줌
//  it : 매개변수가 1개인 람다 함수 사용 시 매개변수의 데이터를 가져오는 키워드
val ptr5: (Int) -> Unit = { println("다른 방식의 매개변수가 1개인 람다 함수 $it") }

//  람다 함수 선언 시 여러 줄의 소스코드를 입력해도 상관없음
//  람다 함수에서 마지막 라인의 연산 결과가 데이터일 경우 해당 데이터를 return 함
val ptr6 = { num1: Int, num2: Int -> num1 + num2 }
val ptr7 = {
  num1: Int,  num2: Int ->
  println("람다 함수 안에서 동작")
  println("아래의 내용은 반환됨")
  num1 + num2
}


//  함수 타입 선언 : 람다 함수를 사용 시 람다 함수(람다함수는 익명함수)를 호출하기 위해서 변수에 람다 함수를 저장하여 사용함
//  람다 함수를 저장할 변수에 저장하고자 하는 함수의 형태를 미리 선언하는 것을 의미

//  코틀린에서 일반적인 함수 선언 방식
//  Int 타입의 매개변수 2개를 입력받고, Int 타입의 연산 결과를 반환
fun function1(num1: Int, num2: Int) : Int {
  var result: Int = 0
  result = num1 + num2
  return result
}

//  위의 일반 함수를 람다 함수로 변환
//  변수 function2 는 데이터 타입 자동 추론 방식을 사용하여 저장한 데이터 타입을 확정
//  Int 타입의 매개변수 2개를 입력받고, Int 타입의 연산 결과를 반환
val function2 = { num1: Int, num2: Int -> // 매개변수로 받아오는 데이터
  var result: Int = 0
  result = num1 + num2
  result // 반환할 데이터
}
// val function2 = {num1: Int, num2: Int -> num1 + num2}

//  변수를 먼저 선언하고, 나중에 람다 함수를 저장할 예정 시 사용
//  저장할 람다 함수의 형태를 지정할 수 있음
//  저장할 형태는 Int 타입의 매개변수 2개를 입력받고, Int 타입의 연산 결과를 반환
val function21: (Int, Int) -> Int = { num1: Int, num2: Int ->
  var result: Int = 0
  result = num1 + num2
  result
}

//  저장할 형태를 미리 지정한 변수
lateinit var function3: (Int, Int) -> Int

//  타입 별칭 : 함수를 저장할 변수에 적용할 데이터 타입을 미리 지정해 놓고 사용하는 방식
//  typealias 키워드를 사용

//  사용법
//  typealias 별칭명 = 람다 함수의 타입
//  val 변수명 : 별칭명 = 람다 함수

//  변수에 일반 함수 선언 방식의 익명함수를 저장, 변수명으로 함수를 호출 가능
val function4 = fun (num1: Int, num2: Int) : Int {
  var result = 0
  result = num1 + num2
  return result
}

//  변수에 저장할 수 있는 타입을 먼저 지정, 지정된 타입에 맞는 함수를 저장
val function41 : (Int, Int) -> Int = fun (num1: Int, num2: Int) : Int {
  var result = 0
  result = num1 + num2
  return result
}

//  사용자 정의 타입 설정, 타입 별칭 사용
typealias MyFuncType = (Int, Int) -> Int

//  타입 별칭을 데이터 타입을 지정한 변수에 지정된 타입에 맞는 함수를 저장
val function42 : MyFuncType = fun (num1: Int, num2: Int): Int {
  var result = 0
  result = num1 + num2
  return result
}

val function43 : MyFuncType = {num1: Int, num2: Int -> num1 + num2}


//  고차 함수 : 코틀린에서 함수의 매개변수로 함수를 전달하는 것
//  자바에서 클래스 다형성 혹은 인터페이스 다형성과 같은 역할을 함
//  코틀린에서는 변수에 저장할 함수 타입 선언을 미리 지정하고, 지정된 타입 형식의 함수를 저장해서 사용하는 것

// 함수 hofFun 의 매개변수는 (Int) -> Boolean): () -> String 형태임
// 매개변수 arg 는 매개변수는 (Int) -> Boolean) 타입이고, 반환값은 () -> String 타입인 함수
// 매개변수 arg 는 매개변수도 함수, 반환값도 함수인 함수
//  변수 arg 에 저장된 것이 함수, arg() 로 호출
fun hofFun(arg: (Int) -> Boolean): () -> String {
//  arg(10) 은 매개변수로 Int 타입의 숫자 10 입력받고 연산 후 Boolean 타입의 결과를 반환
//  변수 result 에 arg(10)의 결과값이 저장
  val result = if (arg(10)) {
    "valid"
  }
  else {
    "invalid"
  }

//  hofFun 의 반환 타입은 () -> String 임
//   { "hofFun result : $result"} 를 반환함
//   { "hofFun result : $result"}  =>   { -> "hofFun result : $result"}
//  매개변수는 없고 반환값은 String 타입인 함수를 반환
  return { "hofFun result : $result" }
}


fun main() {

  println("\n----- 고차함수 사용하기 -----\n")

//  funResult 는 함수를 저장하는 변수, 함수 hofFun 을 변수 funResult에 저장
  val funResult = hofFun({no -> no > 0})
//  변수 funResult 에 함수가 저장되어 있으므로 funResult()
  println(funResult())

  println("\n ----- 람다함수 사용하기 -----\n")

//  일반 함수를 사용하여 연산
  var result = sum1(10, 20)
  println("(일반 함수) 두 수의 덧셈은 $result 입니다.")

//  람다 함수를 사용하여 연산
  result = sum2(10, 20)
  println("(람다 함수) 두 수의 덧셈은 $result 입니다.")
  
  println("\n ----- 람다 함수를 선언과 동시에 호출 -----\n")

//  선언한 람다 함수 뒤에 '(매개변수)' 를 사용하면 선언과 동시에 호출됨
  println({num1: Int, num2: Int -> num1 + num2}(10, 20))

  println("\n ----- 람다 함수 호출 -----\n")

  ptr1("헬로~~", "월드!!")

  println("\n----- 매개변수가 없는 람다 함수 -----\n")
  ptr2()
  ptr3()

  println("\n ----- 매개변수가 1개인 람다 함수 -----\n")
  ptr4(100)
  ptr5(100)

  println("\n----- 반환값을 사용하는 람다 함수 ----\n")
  result = ptr6(10, 20)
  println("한줄 실행형 람다 함수의 반환값 : $result")
  result = ptr7(10, 20)
  println("여러줄 실행형 람다 함수의 반환값 : $result")
  

  println("\n----- 함수 타입 선언 -----\n")

  result = function1(10, 20)
  println("일반 함수를 사용 : $result")

  result = function2(10, 20)
  println("함수 타입을 선언한 변수를 사용 : $result")

//  변수에 저장할 수 있는 함수 형태를 지정하면, 해당 변수에 저장하는 함수의 형태가 동일하면 여러가지 함수를 바꿔가면서 사용이 가능
  function3 = {num1: Int, num2: Int -> num1 + num2}
  println(function3(10, 20))
  function3 = {num1: Int, num2: Int -> num1 - num2}
  println(function3(100, 50))

  println("\n ----- typealias 사용 -----\n")

  result = function4(10, 20)
  println("결과 : $result")
  result = function41(10, 20)
  println("결과 : $result")
  result = function42(10, 20)
  println("결과 : $result")
  result = function43(10, 20)
  println("결과 : $result")
}







