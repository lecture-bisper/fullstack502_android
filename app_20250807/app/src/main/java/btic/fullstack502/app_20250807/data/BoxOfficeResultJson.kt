package btic.fullstack502.app_20250807.data

import com.google.gson.annotations.SerializedName

data class BoxOfficeResultJson(
  @SerializedName("boxofficeType")
  val boxOfficeType: String,
  val showRange: String,
  val dailyBoxOfficeList: List<DailyBoxOfficeListItemJson>
)
