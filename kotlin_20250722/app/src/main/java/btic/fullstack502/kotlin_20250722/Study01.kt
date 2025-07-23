package btic.fullstack502.kotlin_20250722

fun main() {

  println("\n ----- Any ----- \n")
//  Any : 코틀린의 모든 데이터 타입을 저장할 수 있는 데이터 타입, Java의 Object 타입과 같은 역할을 함

//  Any 타입으로 지정한 변수에 정수 데이터 저장
  val data1: Any = 10
  println("data1 : $data1")
//  Any 타입으로 지정한 변수에 문자열 데이터 저장
  val data2: Any = "hello"
  println("data2 : $data2")
//  Any 타입으로 지정한 변수에 클래스의 객체를 저장
  var data3: Any = Test01()
  println("data3 : $data3")
//  var 키워드를 사용하여 생성된 Any 타입의 변수에는 여러가지 타입의 데이터를 저장할 수 있음
  data3 = 10
  println("data3 : $data3")
//  자바에서 사용 시
//  Object data3 = 100;
//  data3 = "문자열";
// 자바에서는 Object 타입의 변수에 저장된 데이터를 출력 시 강제 타입 변환
//  System.out.println("data3 : " + (String) data3);

  println("\n ----- Unit ----- \n")

//  Unit : 코틀린에서 해당 함수의 반환값이 없다는 의미의 키워드, 자바의 void 키워드와 동일함
//  Unit 키워드를 생략하면 컴파일러가 자동으로 Unit 키워드를 붙임
//  함수 호출
  some1()
  some2()

  println("\n ----- Nothing ----- \n")

//  Nothing : null 만 저장할 수 있는 타입
//  함수나 메소드의 반환 타입으로 Nothing 을 사용할 경우 null 과 예외만 반환
  
//  변수의 데이터 타입을 Nothing 으로 지정하여 null 만 저장
  var data4: Nothing? = null
//  데이터 타입을 Nothing 타입으로 지정한 변수에 null 이 아닌 다른 데이터를 저장하여 오류 발생
//  data4 = 100
  
//  null 허용 / null 불허용 : 코틀린의 변수는 모든 변수가 객체이기 때문에 null 을 저장할 수 있는 가능성이 존재함
//  코틀린에서는 null 을 안전하게 사용하기 위해서 변수의 데이터 타입에 ' ? ' 를 사용하여 null 저장을 허용하거나 불허용함
//  데이터 타입에 ' ? ' 가 있으면 null 저장 가능, 없으면 저장 불가

  var data5: Int = 10
  var data6: Int? = 10

  println("data5 : $data5")
  println("data6 : $data6")

//  변수 data5 의 데이터 타입은 Int 이므로 null 저장 불가
//  data5 = null
//  변수 data6 의 데이터 타입은 Int? 이므로 null 저장 가능
  data6 = null
}

class Test01 {
}

//  반환값이 없음을 나타내기 위해서 Unit 키워드를 사용
fun some1(): Unit {
  println(10 + 20)
}

//  반환값이 없음을 나타내는 Unit 키워드를 생략, Unit 키워드 생략 가능, 컴파일러가 자동으로 붙임
fun some2() {
  println(10 + 20)
}

//  반환 타입을 Nothing? 타입을 설정하여 null을 반환
fun some3(): Nothing? {
  return null
}

//  반환 타입을 Nothing 타입으로 설정하여 예외만 반환
fun some4(): Nothing {
  throw Exception()
}









