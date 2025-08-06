package btic.fullstack502.app_20250806.helper

//  데이터베이스와 kotlin 간의 데이터 전달을 위해서 만든 data class (스프링의 DTO 클래스 역할)
data class PhoneBookData(
  var seq: Int = 0, // 기본값 0, not null
  var name: String = "test0", // 기본값 test0
  var phone: String = "", // 기본값 빈 문자열
  var email: String?, // null 가능
  var addr: String? // null 가능
)
