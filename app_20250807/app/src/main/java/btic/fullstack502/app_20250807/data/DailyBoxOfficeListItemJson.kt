package btic.fullstack502.app_20250807.data

import com.google.gson.annotations.SerializedName

data class DailyBoxOfficeListItemJson(
  val rnum: String,
  val rank: String,
  val rankInten: String,
  val rankOldAndNew: String,
  @SerializedName("movieCd")
  val movieCD: String,
  val movieNm: String,
  val openDt: String,
  val salesAmt: String,
  val salesShare: String,
  val salesInten: String,
  val salesChange: String,
  val salesAcc: String,
  val audiCnt: String,
  val audiInten: String,
  val audiChange: String,
  val audiAcc: String,
  val scrnCnt: String,
  val showCnt: String
)
