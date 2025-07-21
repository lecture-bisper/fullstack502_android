//  스프링 프로젝트의 전체 설정 및 종속성 설정을 하는 설정 파일과 동일한 역할을 하는 파일

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
//    패키지 명
    namespace = "btic.fullstack502.myapp"
//    컴파일 시 사용하는 SDK 버전 설정
    compileSdk = 36

    defaultConfig {
        applicationId = "btic.fullstack502.myapp"
//        최소 지원 SDK 버전
        minSdk = 29
//        실제 동작하는 SDK 버전
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
//        코틀린 언어가 자바 코드로 변환 시 사용할 자바 버전 설정
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
//        사용할 자바 가상 머신 버전 설정
        jvmTarget = "11"
    }
}

//  종속성 설정
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.13.1")
}