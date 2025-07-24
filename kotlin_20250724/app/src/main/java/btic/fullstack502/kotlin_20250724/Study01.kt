package btic.fullstack502.kotlin_20250724

//  오버라이딩 : 부모 클래스의 멤버를 자식 클래스가 상속받아 그 내용을 수정하여 사용하는 것
//  코틀린에서는 오버라이딩 가능한 부모의 멤버에 open 키워드를 추가함
//  자식 클래스에서는 오버라이딩할 멤버에 override 키워드를 추가함

// 부모 클래스로 사용되는 클래스 Parent, open 키워드 추가
//  코틀린에서는 기본적으로 상속이 금지되어 있음
open class Parent {
  var someData1 = 100
  open var someData2 = 200

//  상속받은 클래스에서 오버라이딩을 허용하기 위해서 open 키워드 추가
  open fun someMethod() {
    println("부모 클래스의 메소드 호출 : $someData1")
  }
}

//  Parent 클래스를 상속받아 구현한 자식 클래스 Child
class Child : Parent() {
  override var someData2 = 2000
  var someData3 = 300

//  상속받은 메소드 및 변수를 오버라이딩 시 override 키워드가 필수
  override fun someMethod() {
    println("자식 클래스의 메소드 호출 : ${someData1}")
  }
}

//  접근제한자 : 코틀린의 접근제한자는 private, internal, protected, public 4가지를 제공함
//  기본적으로 자바와 동일함, 코틀린의 internal 과 자바의 default 는 동일함
//  protected 는 클래스에서 사용 시 자바와 동일함, 코틀린은 파일의 최상위 멤버로 변수와 함수를 사용할 수 있기 때문에 변수와 함수에는 protected 를 사용할 수 없음
//  코틀린의 기본 접근제한자는 public, 접근제한자 생략 시 public 으로 인식

open class Parent2 {
//  접근제한자 생략, 기본 접근제한자인 public 이 적용
  var publicData = 10
//  protected 접근제한자를 사용하여 자식 클래스에서는 사용 가능
  protected var protectedData = 20
//  private 접근제한자를 사용하여 현재 클래스 내부에서만 사용 가능
  private var privateData = 30
}

class Child2 : Parent2() {
  fun childMethod() {
//    상속받은 필드, public 접근제한자를 사용하므로 어디서나 사용 가능
    publicData++
//    상속받은 필드, protected 접근제한자를 사용하므로 상속받은 자식 클래스 내부에서는 사용 가능
    protectedData++
//    접근제한자가 private 이므로 상속받지 못함, 사용 불가
//    privateData++
  }
}

fun main() {
  println("\n ----- 오버라이딩 ----- \n")

  val child = Child()
  println("child 의 someData1 : ${child.someData1}")
  println("child 의 someData2 : ${child.someData2}")
  println("child 의 someData3 : ${child.someData3}")
  child.someMethod()

  println("\n ----- 접근제한자 -----\n")

//  자식 클래스인 Child2 클래스의 객체 생성
  val child2 = Child2()
//  public 접근제한자를 사용한 멤버이므로 객체 외부에서 사용 가능
  child2.publicData++
//  protected 접근제한자를 사용한 멤버이므로 Parent2 클래스를 상속받은 Child2클래스 내부에서는 사용 가능, Child2 클래스의 객체 외부에서는 사용 불가
//  child2.protectedData++
//  private 접근제한자를 사용한 멤버이므로 Parent2 클래스 내부에서만 사용 가능, Child2 는 상속받지도 못함, 존재하지 않음
//  child2.privateData++
}









