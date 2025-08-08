plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "btic.fullstack502.app_20250808"
  compileSdk = 36

  defaultConfig {
    applicationId = "btic.fullstack502.app_20250808"
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

  // https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
  implementation(libs.retrofit)

  // https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson
  implementation(libs.converter.gson)

  // https://mvnrepository.com/artifact/com.google.code.gson/gson
  implementation(libs.gson)

  // https://mvnrepository.com/artifact/com.github.bumptech.glide/glide
  implementation(libs.glide)
}