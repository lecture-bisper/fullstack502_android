plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "btic.fullstack502.app_20250807"
  compileSdk = 36

  defaultConfig {
    applicationId = "btic.fullstack502.app_20250807"
    minSdk = 29
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  viewBinding {
    enable = true
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.constraintlayout)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

//  Volley 라이브러리 사용, 네트워크 통신용
  // https://mvnrepository.com/artifact/com.android.volley/volley
  implementation(libs.volley)

//  Gson 라이브러리 사용, Json 데이터 파싱용
// https://mvnrepository.com/artifact/com.google.code.gson/gson
  implementation(libs.gson)

//  Retrofit2 라이브러리 사용, 네트워크 통신용
  // https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
  implementation(libs.retrofit)

//  Retrofit2 의 Json 데이터 컨버터, Gson 라이브러리 사용 시 필요
  // https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson
  implementation(libs.converter.gson)

//  Retrofit2 의 Xml 데이터 컨버터
  // https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-simplexml
  implementation(libs.converter.simplexml)

//  Glide 라이브러리 사용, 네트워크를 통해서 이미지를 가져올 수 있음
  // https://mvnrepository.com/artifact/com.github.bumptech.glide/glide
  implementation(libs.glide)
}