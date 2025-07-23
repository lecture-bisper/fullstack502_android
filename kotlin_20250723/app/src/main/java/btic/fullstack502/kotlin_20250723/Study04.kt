package btic.fullstack502.kotlin_20250723

//  클래스 상속
//  코틀린의 클래스도 자바와 동일하게 클래스 상속이 가능
//  부모 클래스의 open 키워드를 추가하여 사용
//  자식 클래스에 extends 대신 ' : ' 기호를 사용
//  자식 클래스는 상속 관계에 있는 부모 클래스의 생성자를 반드시 호출해야 함
//  super() : 자바의 super() 와 동일하게 부모의 생성자를 호출하는 명령

//  부모 클래스로 사용하는 클래스, open 키워드 사용
open class Super {
}

//  부모 클래스를 상속받아 사용하는 자식 클래스, 상속 시 : 사용
class Sub : Super {
//  자식 클래스의 생성자가 부모 클래스의 생성자를 호출, super() 를 이용
  constructor() : super() {
  }
}

//  부모 클래스 선언
open class Parent1 {
//  부모 클래스의 필드
  var parent1Field1 = 100
  
//  부모 클래스의 메소드
  fun parent1Method1() {
    println("부모 클래스의 멤버 메소드")
  }
}

//  부모 클래스인 Parent1 을 상속받은 자식 클래스 Child11
//  주 생성자 및 보조 생성자가 없는 자식 클래스
class Child11 : Parent1() {
//  자식 클래스의 필드
  var child11Field1 = 1100

//  자식 클래스의 생성자에서 부모 클래스의 생성자를 호출하는 super() 대신 클래스 선언부에서 부모 클래스 뒤에 주 생성자를 의미하는 () 를 추가하여 사용
//  constructor() : super() {
//  }
  
//  자식 클래스의 메소드
  fun child11Method1() {
    println("자식 클래스 Child11 의 멤버 메소드")
  }
}

//  부모 클래스인 Parent1 을 상속받은 자식 클래스 Child12
class Child12 : Parent1() {
  var child12Field1: Int = 1200

  fun child12Method1() {
    println("자식 클래스 Child12 의 멤버 메소드")
  }
}

//  매개변수가 있는 주 생성자를 사용하는 부모 클래스
//  주 생성자의 매개변수에 var 키워드를 사용하여 클래스의 필드로 사용
open class Parent2(var parent2Field2: String) {
  var parent2Field1 = 100

  fun parent2Method() {
    println("부모 클래스인 Parent2 의 메소드 호출")
  }
}

//  부모 클래스 Parent2 를 상속받아 사용하는 자식 클래스 Child21
//  자식 클래스에 생성자가 없음
//  상속받는 부모 클래스의 생성자를 직접 실행하여 호출
class Child21 : Parent2("아이유") {
  var child21Field1 = 1000
  
  fun child21Method() {
    println("자식 클래스 Child21의 메소드 호출")
  }
}

//  자식 클래스가 주 생성자를 사용함, 매개변수도 존재
//  부모 클래스의 생성자를 직접 실행하여 호출
//  자식 클래스의 생성자에 사용된 매개변수를 부모 클래스의 생성자 호출 시 사용
class Child22(name: String) : Parent2(name) {
  var child22Field1 = 1000
  
  fun child22Method() {
    println("자식 클래스 Child22의 메소드 호출")
  }
}

//  자식 클래스 Child22 와 동일한 형태이나 보조 생성자를 사용한 방식
//  자식 클래스의 보조 생성자에서 부모 클래스의 생성자를 super() 를 통해서 호출
class Child23 : Parent2 {
  var child23Field1 = 1000

  constructor(name: String) : super(name) {
  }
  
  fun child23Method() {
    println("자식 클래스 Child23 의 메소드 호출")
  }
}

fun main() {
  println("\n ----- 클래스 상속 ----- \n")

//  자식 클래스인 Child11 의 객체 생성
  val child11 = Child11()
  println(child11.parent1Field1)
  println(child11.child11Field1)
  child11.parent1Method1()
  child11.child11Method1()

  println()
  
  val child12 = Child12()
  println(child12.parent1Field1)
  println(child12.child12Field1)
  child12.parent1Method1()
  child12.child12Method1()

  println("\n ----- ----- \n")

  val child21 = Child21()
  println(child21.parent2Field1)
  println(child21.parent2Field2)
  println(child21.child21Field1)
  child21.parent2Method()
  child21.child21Method()

  println()

  val child22 = Child22("아이유")
  println(child22.parent2Field1)
  println(child22.parent2Field2)
  println(child22.child22Field1)
  child22.parent2Method()
  child22.child22Method()

  println()

  val child23 = Child23("아이유")
  println(child23.parent2Field1)
  println(child23.parent2Field2)
  println(child23.child23Field1)
  child23.parent2Method()
  child23.child23Method()
}






