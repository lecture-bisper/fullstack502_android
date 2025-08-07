package btic.fullstack502.app_20250807.data

import com.google.gson.annotations.SerializedName

//  @SerializedName : 연동되는 Json 데이터의 실제 키 명과 코틀린 클래스의 필드명이 다를 경우 서로 일치 시키기 위해서 사용하는 어노테이션
//  Json 데이터와 코틀린 클래스의 필드명이 동일하면 생략 가능
data class Kobis(
  @SerializedName("boxOfficeResult")
  var boxOfficeResult: BoxOfficeResult? = null
)
