package btic.fullstack502.kotlin_20250723

import android.R.attr.name

//  코틀린의 클래스 선언
//  기본적으로 자바의 클래스와 선언 방법이 동일함

//  빈 클래스 선언
class User1 {
}

//  빈 클래스의 경우 코드 블록인 {} 생략 가능
class User2

//  클래스의 멤버는 자바와 같이 필드, 메소드, 생성자 임
class User {
//  클래스의 필드
  var name = "홍길동"

//  클래스의 생성자
//  코틀린 클래스의 생성자 이름은 constructor 로 지정되어 있음
  constructor(name: String) {
    this.name = name
  }

//  클래스의 메소드
  fun someFun() {
    println("이름 : $name")
  }

//  내장 클래스
  class ClassInClass {}

}

//  생성자
//  코틀린의 생성자는 주 생성자, 보조 생성자로 2가지가 존재함
//  코틀린의 생성자는 constructor 이라는 키워드로 이름이 지정되어 있음

//  주 생성자 : 생성자의 이름인 constructor 을 생략할 수 있음(주로 생략하여 사용), 키워드 생략 시 () 만 사용
//  클래스 명 뒤에 constructor() 혹은 () 만 붙여서 사용
//  주 생성자에 매개변수도 없을 경우 () 도 생략 가능
//  주 생성자는 1개만 존재할 수 있음
//  주 생성자에 매개변수를 사용할 수 있음 (기본적으로 var, val 생략)
//  주 생성자의 매개변수에 var, val 키워드를 사용 시 해당 매개변수를 해당 클래스의 필드로 사용
//  주 생성자에서 필드의 초기화나 메소드 호출을 위한 init{} 코드 블록을 사용할 수 있음

//  보조 생성자 : 생성자의 이름이 constructor 이며, 이름을 생략할 수 없음
//  보조 생성자는 생성자 오버로딩을 통하여 여러개 사용할 수 있음
//  자바의 생성자 처럼 클래스 내부에 선언하여 사용
//  보조 생성자의 매개변수는 var, val 키워드를 사용할 수 없음


//  주 생성자를 사용한 클래스 선언, 정식 클래스 선언 방법
class User3 constructor() {
}

//  주 생성자를 사용한 클래스, constructor 키워드 생략
class User4() {
}

//  주 생성자를 사용한 클래스, constructor 키워드와 매개변수를 입력하는 () 도 생략
class User5 {
}

//  주 생성자에 매개변수를 추가한 클래스
class User6 constructor(name: String, age: Int) {

//  클래스의 필드 선언
  var name: String
  var age: Int

//  주 생성자 초기화 블록
//  코틀린의 주 생성자는 매개변수를 입력하는 () 뒤에 생성자의 내용을 입력하는 코드블록 {} 을 사용할 수 없음
//  클래스 내부에 init{} 라는 코드블록을 사용하여 주 생성자의 내용을 입력함
  init {
    println("init{} 는 주 생성자가 실행하는 초기화 코드를 입력")
    println("주 생성자 매개변수 name : $name, age : $age")
//  this 는 자바의 this 와 동일한 의미
//  주 생성자의 매개변수로 전달받은 데이터를 사용하여 클래스의 필드를 초기화
    this.name = name
    this.age = age
//    name = "임꺽정" // 주 생성자의 매개변수는 읽기 전용, 수정 불가
  }

//  클래스의 메소드
  fun someMethod() {
    println("name : $name, age : $age")
  }
}

//  constructor 키워드를 생략, 주 생성자의 매개변수에 var, val 키워드 사용
//  주 생성자의 매개변수를 클래스의 필드로 사용
//  주 생성자의 매개변수에 var, val 키워드를 사용하지 않으면 init{} 초기화 블록 외부에서 매개변수로 받아온 변수를 사용할 수 없음
class User7(val name: String, var age: Int) {
//  var name: String
//  var age: Int

//  init {
//    this.name = name
//    this.age = age
//  }

  fun someMethod() {
    println("클래스의 멤버 메소드")
    println("name : $name, age : $age")
  }
}


//  보조 생성자를 사용하는 클래스
//  자바의 생성자가 코틀린의 보조 생성자와 같음
class User8 {

//  클래스의 필드 선언, 빈 문자열과 숫자 0 으로 초기화
  var name: String = ""
  var age: Int = 0

//  보조 생성자 선언, 생성자 오버로딩 사용
  constructor() {
    println("매개변수가 없는 보조 생성자")
  }

  constructor(name: String) {
    println("두번째 보조 생성자, 매개변수가 1개인 보조 생성자")
    println("매개변수 name : $name, 필드 name : ${this.name}")
    this.name = name
  }

  constructor(name: String, age: Int) {
    println("세번째 보조 생성자, 매개변수가 2개인 보조 생성자")
    println("매개변수 name : $name, age : $age")
    println("필드 name : ${this.name}, age : ${this.age}")
    this.name = name
    this.age = age
  }

  fun someMethod() {
    println("보조 생성자를 사용한 클래스 User8의 메소드")
    println("name : $name, age : $age")
  }
}

//  주 생성자나 보조 생성자를 반드시 사용할 필요는 없음
//  주 생성자와 보조 생성자 중 원하는 것을 사용하면 됨 (개발자 취향)
//  주 생성자와 보조 생성자를 함께 사용 시 반드시 보조 생성자에서 주 생성자를 호출

//  주 생성자와 보조 생성자를 함께 사용하는 클래스
//  주 생성자의 매개변수에 var 키워드를 사용하여 매개변수를 클래스의 필드로 사용
class User9(var name: String) {

//  클래스의 필드 선언
  var age: Int = 0
  var email: String = ""

//  주 생성자의 초기화 블록
  init {
    println("매개변수가 1개인 주 생성자 사용")
  }

//  보조 생성자
//  자바의 this() 와 같이 코틀린에서도 this() 를 사용하여 다른 생성자를 호출할 수 있음
//  보조 생성자 선언부 뒤에 ' : this()' 을 추가하여 다른 생성자를 호출
//  매개변수가 2개인 보조 생성자에서 매개변수가 1개인 주 생성자를 this(매개변수) 를 통해서 호출함
  constructor(name: String, age: Int) : this(name) {
    println("매개변수가 2개인 보조 생성자 사용")
    this.age = age
  }

//  오버로딩된 보조 생성자
//  매개변수가 3개인 보조 생성자에서 주 생성자를 직접 호출하지 않고, 매개변수가 2개인 보조 생성자를 호출하고, 호출된 보조 생성자가 주 생성자를 호출함
  constructor(name: String, age: Int, email: String) : this(name, age) {
    println("매개변수가 3개인 보조 생성자 사용")
    this.email = email
  }

  fun someMethod() {
    println("클래스의 필드 출력 - name: $name, age: $age, email: $email")
  }
}


fun main() {

  println("\n ----- 클래스 사용하기 ----- \n")

//  코틀린의 클래스로 객체 생성
//  코틀린은 객체 생성 시 new 키워드를 사용하지 않음
  val user = User("아이유")
//  객체의 멤버에 접근하는 방식은 자바와 동일함
  user.someFun()

  val user1 = User1() // 일반적인 빈 클래스로 객체 생성
  val user2 = User2() // {} 도 생략한 빈 클래스로 객체 생성
  val user3 = User3() // 주 생성자 이름인 constructor() 을 사용한 클래스로 객체 생성
  val user4 = User4() // 주 생성자 이름인 constructor 을 생략한 클래스로 객체 생성
  val user5 = User5() // 주 생성자 이름과 () 도 모두 생략한 클래스 객체 생성

// 주 생성자에 매개변수를 전달하여 객체 생성
  val user6 = User6("아이유", 32)
  println("user6.name : ${user6.name}")
  println("user6.age : ${user6.age}")
  user6.someMethod()

  println()

  val user7 = User7("아이유", 32)
  println("user7.name : ${user7.name}")
  println("user7.age : ${user7.age}")
  user7.someMethod()

  println("\n ----- 보조 생성자 사용 ----- \n")

  val user81 = User8()
  user81.someMethod()

  println()

  val user82 = User8("아이유")
  user82.someMethod()

  println()

  val user83 = User8("아이유", 32)
  user83.someMethod()

  println("\n\n")

  val user91 = User9("아이유")
  user91.someMethod()

  println()

  val user92 = User9("아이유", 32)
  user92.someMethod()

  println()

  val user93 = User9("아이유", 32, "iu@bitc.ac.kr")
  user93.someMethod()
}








