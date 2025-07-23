package btic.fullstack502.kotlin_20250722

fun main() {

  println("\n ----- Array ----- \n")

//  배열 : 코틀린에서는 배열을 클래스로 표현
//  기본 사용법(데이터 입력, 출력)은 자바의 배열과 동일함
//  자바와 같이 index 를 통해서 데이터를 입/출력

//  사용법(선언)
//  val 배열명: 데이터타입 = Array(배열크기, {초기값})

  val arrData1: Array<Int> = Array(5, {0})
  println(arrData1)
//  배열에서 데이터 출력 시 자바와 동일함
  println("arrData1 0 index : ${arrData1[0]}")
  println("arrData1 4 index : ${arrData1[4]}")

  println("----- data edit -----")
//  배열에서 데이터 입력 시 자바와 동일함
  arrData1[0] = 10
  arrData1[1] = 20
  arrData1[2] = 30
//  코틀린의 배열은 get()/set() 메소드를 사용하여 접근
//  set(index, 저장할 데이터) 형태로 데이터 저장
//  get(index) 형태로 데이터 출력
  arrData1.set(3, 40)
  arrData1.set(4, 50)

  println(
    """
      array size : ${arrData1.size}
      array data : ${arrData1[0]}, ${arrData1[1]}, ${arrData1.get(2)}, ${arrData1.get(3)}, ${arrData1.get(4)}
    """.trimIndent()
  )

//  Double 타입의 배열 선언
  val arrData2: Array<Double> = Array(3, {0.0})
//  String 타입의 배열 선언
  val arrData3: Array<String> = Array(3, {""})

//  코틀린에서는 기본 타입에 대해서 배열 클래스를 따로 제공함
//  ByteArray, ShortArray, IntArray, LongArray, FloatArray, DoubleArray, BooleanArray, CharArray
//  기본 타입 배열 클래스를 사용 시 타입 지정이 미리 되어 있기 때문에 제네릭을 통한 데이터 타입 지정이 필요없음
  val arrByte: ByteArray = ByteArray(3, { 0 })
  val arrShort: ShortArray = ShortArray(3, { 0 })
  val arrInt: IntArray = IntArray(3, { 0 })
  val arrLong: LongArray = LongArray(3, { 0L })
  val arrFloat: FloatArray = FloatArray(3, { 0.0F })
  val arrDouble: DoubleArray = DoubleArray(3, { 0.0 })
  val arrChar: CharArray = CharArray(3, { 'a' })
  val arrBoolean: BooleanArray = BooleanArray(3, { false })

  println("byte array size : ${arrByte.size}, value : ${arrByte[0]}")
  println("short array size : ${arrShort.size}, value : ${arrShort.get(0)}")
  println("int array size : ${arrInt.size}, value : ${arrInt[0]}")
  println("long array size : ${arrLong.size}, value : ${arrLong.get(0)}")
  println("float array size : ${arrFloat.size}, value : ${arrFloat[0]}")
  println("double array size : ${arrDouble.size}, value : ${arrDouble.get(0)}")
  println("char array size : ${arrChar.size}, value : ${arrChar[0]}")
  println("boolean array size : ${arrBoolean.size}, value : ${arrBoolean.get(0)}")

//  코틀린에서 배열 선언과 동시에 데이터 입력하는 함수를 제공
//  arrayOf() : 코틀린에서 배열 생셩과 동시에 지정한 값으로 데이터를 초기화하는 함수
//  기본 타입 배열 클래스용 arrayOf() 가 존재함
//  byteArrayOf(), shortArrayOf(), intArrayOf(), longArrayOf(), floatArrayOf(), doubleArrayOf(), charArrayOf(), booleanArrayOf()

//  사용법
//  val 배열명: Array<데이터타입> = arrayOf(데이터1, 데이터2, ... )
//  val 배열명: IntArray = intArrayOf(10, 20, ... )

  println("\b ----- arrayOf() ----- \n")

  val arrData4: Array<Int> = arrayOf(10, 20, 30)
  println(arrData4)
  println("use arrayOf() 0 index : ${arrData4[0]}")
  println("use arrayOf() 1 index : ${arrData4[1]}")
  println("use arrayOf() 2 index : ${arrData4.get(2)}")

  println("\n----- use basic type arrayOf() -----\n")

  val arrByte2: ByteArray = byteArrayOf(0b00, 0b01, 0b10)
  val arrShort2: ShortArray = shortArrayOf(10, 20, 30)
  val arrInt2: IntArray = intArrayOf(10, 20, 30)
  val arrLong2: LongArray = longArrayOf(10L, 20L, 30L)
  val arrFloat2: FloatArray = floatArrayOf(0.1F, 0.2F, 0.3F)
  val arrDouble2: DoubleArray = doubleArrayOf(0.1, 0.2, 0.3)
  val arrChar2: CharArray = charArrayOf('a', 'b', 'c')
  val arrBoolean2: BooleanArray = booleanArrayOf(true, false)

  println("arrByte2 0 index : ${arrByte2[0]}")
  println("arrShort2 0 index : ${arrShort2[0]}")
  println("arrInt2 0 index : ${arrInt2[0]}")
  println("arrLong2 0 index : ${arrLong2[0]}")
  println("arrFloat2 0 index : ${arrFloat2.get(0)}")
  println("arrDouble2 0 index : ${arrDouble2.get(0)}")
  println("arrChar2 0 index : ${arrChar2.get(0)}")
  println("arrBoolean2 0 index : ${arrBoolean2.get(0)}")


  println("\n ----- list, set, map ----- \n")

//  코틀린에서는 자바의 ArrayList, HashSet, HashMap 에 대응하는 List, Set, Map 클래스를 제공하고 있음
//  코틀린의 Array 의 arrayOf() 와 같은 메소드인 listOf, setOf, mapOf 를 제공하여 List, Set, Map 타입의 변수를 생성함
  
//  코틀린의 List, Set, Map 은 불변(Immutable), 가변(Mutable) 이라는 개념이 존재함
//  Mutable 은 수정 가능한 타입, 처음 데이터 초기화 후 데이터를 계속 수정 가능
//  Immutable 은 수정 불가능 타입, 처음 데이터 초기화 후 데이터 수정 불가
//  Immutable 방식 코틀린 컬렉션의 기본 타입
  
//  Mutable 방식의 List, Set, Map 을 생성하려면, mutableListOf(), mutableSetOf(), mutableMapOf() 를 사용해야 함

  println("----- Immutable list -----")
//  아래의 리스트는 기본 리스트 생성 방식을 사용하여 Immutable 방식의 list 임
  val list1: List<Int> = List(3, { 0 }) // 기본 List 생성 방식
  val list2 = listOf(10, 20, 30) // 주로 사용하는 방식
  val list3: List<Int> = listOf(10, 20, 30)
  val list4 = listOf<Int>(10, 20, 30)

  println(
    """
      list1 size : ${list1.size}
      list1 data : ${list1[0]}, ${list1[1]}, ${list1.get(2)}
    """.trimIndent()
  )

  println(
    """
      list2 size : ${list2.size}
      list2 data : ${list2[0]}, ${list2[1]}, ${list2.get(2)}
    """.trimIndent()
  )

  println(
    """
      list3 size : ${list3.size}
      list3 data : ${list3[0]}, ${list3[1]}, ${list3.get(2)}
    """.trimIndent()
  )

  println(
    """
      list4 size : ${list4.size}
      list4 data : ${list4[0]}, ${list4[1]}, ${list4.get(2)}
    """.trimIndent()
  )

//  생성된 리스트가 불변 클래스 타입의 리스트이기 때문에 생성된 동시에 입력된 데이터를 읽기 전용으로 사용함
//  한번 생성된 Immutable 리스트는 데이터 수정이 불가능함
//  list1[0] = 100
//  list1.set(0, 200)
//  list2[0] = 100
//  list3.set(0, 100)

  println("\n----- Mutable List -----")

  val list5: MutableList<Int> = MutableList(3, {0}) // 빈 리스트 생성
  val list6 = mutableListOf(10, 20, 30) // 주로 사용하는 방식

  println(
    """
      list5 size : ${list5.size}
      list5 data : ${list5[0]} ${list5[1]}, ${list5.get(2)}
    """.trimIndent())

  println(
    """
      list6 size : ${list6.size}
      list6 data : ${list6[0]} ${list6[1]}, ${list6.get(2)}
    """.trimIndent())

  println("\n----- Mutable List update -----")

//  Mutable 방식의 리스트이기 때문에 데이터 수정 가능
  list5[0] = 100
  list5.set(1, 200)
  list6[0] = 100
  list6.set(1, 200)

  println(
    """
      list5 size : ${list5.size}
      list5 data : ${list5[0]} ${list5[1]}, ${list5.get(2)}
    """.trimIndent())

  println(
    """
      list6 size : ${list6.size}
      list6 data : ${list6[0]} ${list6[1]}, ${list6.get(2)}
    """.trimIndent())

//  List 컬렉션 멤버
//  add(index, 데이터) : 지정한 리스트의 지정한 index에 데이터를 추가, 매개변수를 1개만 사용 시 기존 리스트의 가장 마지막에 추가
//  removeAt(index) : 리스트의 지정한 index 에 저장된 데이터를 삭제
//  contains() : 지정한 데이터가 리스트에 존재하는지 확인
//  reverse() : 지정한 리스트의 순서를 반대로 뒤집어 줌
//  shuffle() : 리스트의 순서를 섞어 줌

  println("\n ----- List member ----- \n")
  
  val list7 = mutableListOf(10, 20, 40)
  println("list7 size : ${list7.size}")
  list7.add(2, 30) // index 2 에 데이터 30을 저장
  list7.add(50) // 가장 마지막에 데이터 50을 추가
  println("list7 size : ${list7.size}")
  println("list7 index 2 : ${list7[2]}")
  println("list7 index 3 : ${list7[3]}")
  println("list7 index 4 : ${list7[4]}")
  list7.removeAt(3)
  println("list7 size : ${list7.size}")
  println("list7 index 3 : ${list7[3]}")

  println("\n ----- Map ----- \n")

//  Immutable 방식의 Map 객체 생성
//  key의 타입을 String 으로 설정, value 의 타입을 String으로 설정
  val map1: Map<String, String> = mapOf(Pair("one", "two"), "two" to "world")
//  Mutable 방식의 Map 객체 생성
//  key의 타입을 String 으로 자동 추론함, Value의 타입을 String으로 자동 추론
  val map2 = mutableMapOf(Pair("one", "two"), "two" to "world")

//  코틀린에서 Map 타입에 데이터 입력 시 Pair() 을 사용하거나, 'to' 키워드를 사용함
//  Pair(key, value) 형태로 사용
//  key to value 형태로 사용

//  Map 타입의 데이터에 접근하는 방식은 map타입 변수명["key명"] 형태로 사용
//  get()/set() 함수를 사용하여 데이터 접근

  println(
    """
      map1 size : ${map1.size}
      map1 data : one - ${map1.get("one")}, two - ${map1.get("two")}
    """.trimIndent())

  println(
    """
      map2 size : ${map2.size}
      map2 data : one - ${map2["one"]}, two - ${map2["two"]}
    """.trimIndent()
  )

//  Immutable 방식으로 생성된 Map 객체이기 때문에 수정 불가
//  map1["one"] = "헬로"
//  Mutable 방식으로 생성된 Map 객체이므로 수정 가능
  map2["two"] = "월드"
  
//  Map 컬렉션 멤버
//  get(key) : 지정한 key에 대응하는 value 를 출력
//  set(key, value) : 지정한 key 에 대응하는 value 를 Map 에 저장, Map에 사용자가 지정한 key 가 없을 경우 사용자가 입력한 key 와 value 를 Map 에 저장
//  size : Map 에 저장된 데이터의 수를 출력, 프로퍼티
//  count() : Map 에 저장된 데이터의 수를 출력, 메소드
//  keys : Map 에 저장된 key 만 모두 모아서 출력
//  values : Map 에 저장된 value 만 모두 모아서 출력
//  containsKey(key) : 지정한 key 가 Map 에 있는지 확인, true/false
//  containsValue(data) : 지정한 value 가 Map 에 있는지 확인, true/false
//  remove(key) : 지정한 key 에 대응하는 value 를 Map 에서 삭제
//  clear() : Map 에 저장된 모든 데이터를 삭제

//  [key] 를 사용하는 방식이나 set(key,value) 을 사용하는 방식으로 새로운 데이터 추가
//  map2["three"] = "!!"
  map2.set("three", "!!")

  println(
    """
      map2 size : ${map2.size}
      map2 data : one - ${map2["one"]}, two - ${map2["two"]}, three - ${map2.get("three")}
    """.trimIndent()
  )

  println("\n ----- keys/values -----")

  val keys = map2.keys
  val values = map2.values

  println(keys)
  println(values)

  println(map2.containsKey("one"))
  println(map2.containsKey("aaa"))
  println(map2.containsValue("!!"))
  println(map2.containsValue("$$"))

  println("map2 size : ${map2.size}")
  map2.remove("three")
  println("map2 size : ${map2.size}")
  map2.clear()
  println("map2 size : ${map2.size}")
}















