package btic.fullstack502.kotlin_20250724

class NonDataClass(val name: String, val email: String, val age: Int)

data class DataClass(val name: String, val email: String, val age: Int)

open class Super {
  open var data = 10

  open fun some() {
    println("부모 클래스의 some 메소드 호출 : $data")
  }
}

val obj = object: Super() {
  override var data = 20

  override fun some() {
    println("object 클래스의 some 메소드 호출 : $data")
  }
}

// 인터페이스 선언
interface InterA {
//  추상 메소드 선언
  fun interAMethod()
//  디폴트 메소드 선언
  fun interADefaultMethod() {
    println("인터페이스 A 의 디폴트 메소드 호출")
  }
}

interface InterB {
  fun interBMethod()
  fun interBDefaultMethod() {
    println("인터페이스 B 의 디폴트 메소드 호출")
  }
}

//  인터페이스를 상속받아 구현한 자식 클래스
class ImplA : InterA {
//  상속받은 추상 메소드를 오버라이딩하여 사용
  override fun interAMethod() {
    println("인터페이스 A의 추상메소드를 오버라이딩하여 호출")
  }
//  인터페이스의 디폴트 메소드는 그대로 사용
}

//  2개의 인터페이스 다중 상속받아 구현한 자식 클래스
class ImplB : InterA, InterB {
//  InterA 인터페이스의 추상 메소드를 오버라이딩
  override fun interAMethod() {
    println("인터페이스 A 의 추상메소드를 오버라이딩하여 호출")
  }

//  InterB 인터페이스의 추상 메소드를 오버라이딩
  override fun interBMethod() {
    println("인터페이스 B 의 추상메소드를 오버라이딩하여 호출")
  }

//  InterB 인터페이스의 디폴트 메소드를 오버라이딩
  override fun interBDefaultMethod() {
    super.interBDefaultMethod()
    println("인터페이스 B 의 디폴트 메소드를 오버라이딩하여 호출")
  }
}

fun main() {
  println("\n ----- 데이터 클래스 ----- \n")

  println("----- 일반 클래스를 사용하여 객체 생성 후 비교 -----")
//  일반 클래스를 사용하여 객체를 생성 후 각 객체에 동일한 데이터 저장
  val ndata1 = NonDataClass("아이유", "iu@bitc.ac.kr", 32)
  val ndata2 = NonDataClass("아이유", "iu@bitc.ac.kr", 32)

  println("ndata1 : ${ndata1.name}, ${ndata1.email}, ${ndata1.age}")
  println("ndata2 : ${ndata2.name}, ${ndata2.email}, ${ndata2.age}")

//  일반 클래스를 사용하여 생성한 2개의 객체를 서로 비교 (주소를 비교)
  var result: Boolean = ndata1 == ndata2
  println("ndata1 과 ndata2 의 비교 결과 : $result")

  println("\n----- Data Class 를 사용하여 객체 생성 후 비교 -----")
//  데이터 클래스를 사용하여 객체를 생성 후 각 객체에 동일한 데이터 저장
  val data1 = DataClass("아이유", "iu@bitc.ac.kr", 32)
  val data2 = DataClass("아이유", "iu@bitc.ac.kr", 32)

  println("data1 : ${data1.name}, ${data1.email}, ${data1.age}")
  println("data2 : ${data2.name}, ${data2.email}, ${data2.age}")

//  데이터 클래스를 사용하여 생성한 2개의 객체를 서로 비교 (저장된 데이터를 비교)
  result = data1 == data2
  println("data1 과 data2 의 비교 결과 : $result")

  println("\n ----- equals() 를 사용하여 객체 비교 -----\n")
//  equals() : 2개의 객체가 가지고 있는 데이터가 같은 확인하는 메소드
  result = ndata1.equals(ndata2)
  println("ndata1 과 ndata2 의 equals() 결과 : $result")

  result = data1.equals(data2)
  println("data1 과 data2 의 equals() 결과 : $result")

  println("\n ----- toString() ----\n")
  
//  toString() : 객체가 가지고 있는 데이터를 출력하는 메소드
//  일반 클래스의 toString() 은 해당 클래스의 패키지 및 메모리 주소를 출력
  println("일반 클래스의 객체 출력 : ${ndata1.toString()}")
//  Data 클래스의 toString() 은 Data 클래스에 저장된 데이터를 출력
  println("Data 클래스의 객체 출력 : ${data1.toString()}")

  println("\n ----- object 클래스 ----- \n")
  
//  object 클래스 : 코틀린에서 제공하는 익명 클래스
//  익명 클래스 내부에서 연산을 진행
//  이름이 없기 때문에 1회용으로 사용됨
//  object 클래스를 통해서 객체 생성 시 기본적으로 Any 타입으로 지정됨
//  실제로 사용하려면 object 클래스가 다른 클래스 및 인터페이스를 상속받아야 함
//  사용법
//  val 변수명 = object {
//    구현한 익명 클래스의 멤버...
//  }

  obj.data = 30
  obj.some()


//  인터페이스 : 코트린의 인터페이스도 자바의 인터페이스와 비슷
//  interface 키워드를 사용하여 선언
//  해당 인터페이스 구현 시 implements 가 아닌 ' : ' 을 사용함
//  인터페이스의 멤버로 자바와 같이 추상메소드와 디폴트 메소드가 존재함
//  디폴트 메소스 선언 시 default 키워드가 없음 (일반 메소드 선언과 같음)
//  추상 메소드 선언 시 abstract 키워드가 없음 (일반 메소드 선언부와 같음)
//  추상 메소드에 open 키워드가 없음
//  인터페이스는 자바와 같이 다중 상속이 가능함
//  코틀린에는 static 이 없음 (companion 이라는 개념이 존재함)

//  사용법 (선언)
//  interface 인터페이스명 {
//    추상메소드 ...
//    디폴트메소드 ...
//  }

//  사용법 (구현)
//  class 자식클래스명 : 인터페이스1, 인터페이스2, ... {
//    override 오버라이딩하는 메소드명 ...
//  }

  println("\n ----- 인터페이스 ----- \n")

  val impla = ImplA()
  impla.interAMethod()
  impla.interADefaultMethod()

  println()

  val implb = ImplB()
  implb.interAMethod()
  implb.interADefaultMethod()
  implb.interBMethod()
  implb.interBDefaultMethod()

  println("\n ----- 컴패니언 클래스 ----- \n")
  
//  컴패니언 클래스 : 자바의 static 과 동일한 역할을 하는 클래스
//  일반 클래스의 멤버로 companion object 키워드를 사용한 코드 블록을 선언하고, 해당 코드 블록 안에 변수와 함수를 선언
//  실제 사용 시 객체 생성없이 '클래스명.멤버명' 으로 사용

  println(MyClass.data)
  MyClass.some()

}

class MyClass {
  companion object {
    var data = 10
    fun some() {
      println("컴패니언 클래스 사용 : $data")
    }
  }
}








