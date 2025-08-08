package btic.fullstack502.app_20250808.data

import com.google.gson.annotations.SerializedName

//  서버와 통신 시 사용할 데이터 클래스
data class BoardItem(
  val boardIdx: Int,
  val title: String,
  val contents: String,
  @SerializedName("createId")
  val createID: String,
  val createDate: String,
  @SerializedName("updateId")
  val updateID: String?,
  val updateDate: String?,
  val hitCnt: Int = 0
)
