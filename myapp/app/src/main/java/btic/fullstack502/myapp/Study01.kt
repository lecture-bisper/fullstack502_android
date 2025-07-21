package btic.fullstack502.myapp

var data6 = 60
val data7 = 70

//var data8
//var data9: Int
//  전역 변수로 선언 시에는 선언과 동시에 데이터를 저장해야 함
var data8 = 80
val data9: Int = 90

//  lateinit을 사용하면 전역 변수로 선언 시 선언과 동시에 데이터를 저장하지 않아도 됨
//  기본 타입은 lateinit 을 사용할 수 없음, 레퍼런스 타입만 가능
//  var data12: Int
lateinit var data12: String   // 실제로 해당 변수를 사용 시 데이터 저장

//  by lazy{} 를 사용하여 변수의 초기화 미루기
//  해당 변수가 실제로 사용 시 변수를 초기화
val data13: Int by lazy {
  130
}

//  by lazy{} 를 사용하여 변수의 초기화 미루기
//  {} 안에서 여러가지 연산이 가능함
//  {} 의 마지막 라인에 입력된 데이터가 최종적으로 변수에 저장됨
val data14: Int by lazy {
//  by lazy {} 안의 내용
  println("in lazy....")
//  최종적으로 변수 data14에 저장될 데이터
  140
}

fun main() {
//  코틀린은 소스코드의 마지막에 사용하는 ' ; ' 를 생략함
  println("----- use kotlin -----")

//  var 은 변수의 값을 계속 수정할 수 있는 변수
  var data1 = 10
//  val 로 선언한 변수는 한번 저장 후 수정이 불가능한 변수
  val data2 = 20

  println("data1 : " + data1)
  println("data2 : " + data2)

  data1 = 100
//  val 로 선언하여 데이터 수정이 불가능함
//  data2 = 200

  println("data1 : " + data1)
//  println("data1 : " + data2)

//  변수 선언 시 사용할 데이터 타입을 지정
//  변수만 먼저 선언
  var data3: Int
  val data4: Int

//  변수에 지정한 데이터 타입이 아닌 다른 데이터 타입 저장 시 오류 발생
//  data3 = "숫자 30"
//  data4 = true

//  먼저 선언된 변수에 초기값 설정
  data3 = 30
  data4 = 40

  println("data3 : " + data3)
  println("data4 : " + data4)

  data3 = 300
  println("data3 : " + data3)
//  data4 = 400
//  println("data4 : " + data4)

//  변수 data5 선언, 초기값을 저장된 데이터의 데이터 타입을 자동 추론
//  String 타입의 변수로 지정됨
  var data5 = "문자열"
  println("data5 : $data5")

//  String 타입의 변수에 다른 문자열 저장
  data5 = "다른 문자열"
  println("data5 : $data5")

//  String 타입으로 지정된 변수에 다른 데이터 타입의 값을 저장할 수 없음
//  data5 = 500

//  파일의 최상단에 선언하여 해당 파일 내에서 모두 함께 사용할 수 있는 변수(전역변수)
  println("data6 : $data6")
//  코틀린은 문자열 템플릿을 제공하여 문자열 안에서 $변수명 사용 시 문자열 연결 기호 없이 변수의 내용을 출력할 수 있음
  println("data7 : $data7")
  data6 = 600
  println("data6 : ${data6}")
//  data7 = 700

//  문자열 템플릿을 사용 시 간단한 연산도 가능함, ${변수 및 연산식} 사용
  println("data6 + 100 = ${data6 + 100}")

  println("\n ---------- \n")

//  지역 변수는 변수만 먼저 선언 가능
//  지역 변수로 변수만 먼저 선언 시 데이터 타입을 지정해야 함
//  var data10    // 데이터 타입을 지정하지 않아서 오류 발생
  var data10: Int // 데이터 타입을 지정하여 정상 실행
  val data11: Int //  val 타입도 변수만 먼저 선언 가능

  data10 = 100  // var 로 변수만 먼저 선언 후 나중에 데이터 저장
  data11 = 110  // val 로 변수만 먼저 선언 후 나중에 데이터 저장
  println("data10 : $data10")
  println("data11 : $data11")

  data12 = "lateinit 으로 나중에 초기화한 변수"
  println(data12)


  println("----- by lazy -----")

//  런타임에서 실제로 초기화 미루기를 사용한 변수를 사용 시 초기화
  println("data13 : $data13")

  println("in main...")
//  by lazy {} 를 사용한 변수를 실제로 초기화 함
//  by lazy {} 를 사용한 변수를 최초로 사용하는 부분
  println(data14 + 10)
//  위에서 초기화된 변수를 그대로 사용
  println(data14 + 100)

  println("\n ----- ----- \n")

  val a1: Byte = 0b00001011
//  정수 기본 타입
  val a2: Int = 123
  val a3: Short = 123
//  Long 타입은 접미사로 L 사용
  val a4: Long = 10L
//  Float 타입은 접미사로 F 사용
  val a5: Float = 10.0F
//  실수 기본 타입
  val a6: Double = 10.0
  val a7: Boolean = true
  val a8: Char = 'a'
  val a9: String = "Hello \n World!!"
  val a10: String = """
        Hello
        World!!
  """

  println("Byte : $a1")
  println("Short : $a3")
  println("Int : $a2")
  println("Long : $a4")
  println("Float : $a5")
  println("Double : $a6")
  println("Boolean : $a7")
  println("Char : $a8")
  println("String : $a9")
  println("String : $a10")
}











