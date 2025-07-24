package btic.fullstack502.kotlin_20250724

fun main() {
  println("\n ----- null 안전성 연산자 사용하기 -----\n")

//  변수 선언과 동시에 데이터 저장
  var result: String = "안녕하세요"
//  변수에 저장된 데이터 출력
  println(result)
  println(result.length)

//  변수에 빈 문자열 저장
  result = ""
//  변수에 저장된 데이터 출력
  println(result.length)

//  변수 result 에 null 을 저장할 경우 오류 발생, 코틀린은 기본적으로 null 불허용
//  result = null

  println() 
//  변수 선언과 동시에 데이터 저장, 데이터 타입에 ' ? ' 연산자를 사용하여 null 허용
  var result2: String? = "안녕하세요"
  println(result2)
//  ' ? ' 연산자가 사용된 객체의 멤버를 사용 시 ' ?. ' 연산자를 사용해야 함, 객체를 안전하게 실행하기 위한 것임
//  println(result2.length)
  println(result2?.length)

//  null 허용 변수 result2 에 빈 문자열 저장 후 저장된 내용 출력
  result2 = ""
  println(result2)
  println(result2?.length)

//  null 허용 변수 result2 에 null 을 저장 (정상적으로 저장 완료)
  result2 = null
//  null 이 저장된 변수를 출력
  println(result2)
//  null 이 저장된 변수의 멤버를 ?. 연산자를 사용하여 강제 호출
  println(result2?.length)

  println("\n ----- ?: 사용 -----\n")

//  null 허용 변수 data 선언과 동시에 데이터 저장
  var data: String? = "안녕하세요"
//  변수의 데이터 출력
  println("변수 data : $data")

  println()

//  null 허용 변수 data 에 새로운 데이터 저장
  data = "hello world!!"
//  변수의 내용 출력 및 멤버 사용, 엘비스(?:) 연산자를 사용하여 기본값 설정
//  해당 변수는 null 이 아니므로 변수의 멤버를 그대로 사용
  println("변수 data : $data, 길이 : ${data?.length ?: 0}")

  println()
  
//  null 허용 변수 data 에 null 을 저장
  data = null
//  변수의 내용 출력 및 멤버 사용, 엘비스(?:) 연산자를 사용하여 기본값 설정
//  해당 변수는 null 이므로 null 을 출력, 값이 null 인 객체의 멤버를 사용할 수 없으므로 기본값으로 설정된 0 을 대신 출력
  println("변수 data : $data, 길이 : ${data?.length ?: 0}")

  println("\n----- !! 사용하기 -----\n")

//  매개변수로 문자열을 전달
  println(some("hello world!!"))
//  매개변수로 null 을 전달
  println(some(null))
}

//  매개변수로 null 허용 변수를 받는 함수 some() 선언, 반환값으로 Int 타입 출력
fun some(data: String?) : Int {
//  반환값은 매개변수로 받아온 null 허용 변수의 길이를 출력
//  ' !! ' 연산자를 사용하여 매개변수가 null 일 경우 예외 강제 발생
  return data!!.length
}








