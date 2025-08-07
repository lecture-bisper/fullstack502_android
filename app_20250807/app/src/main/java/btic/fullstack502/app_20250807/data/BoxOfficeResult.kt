package btic.fullstack502.app_20250807.data

import com.google.gson.annotations.SerializedName

data class BoxOfficeResult(
  @SerializedName("boxofficeType")
  var boxOfficeType: String? = null,
  @SerializedName("showRange")
  var showRange: String? = null,
  @SerializedName("dailyBoxOfficeList")
  var dailyBoxOfficeList: ArrayList<DailyBoxOfficeList> = arrayListOf<DailyBoxOfficeList>()
)
