plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "kr.co.lion.application.finalproject_aparttalk"
    compileSdk = 34

    defaultConfig {
        applicationId = "kr.co.lion.application.finalproject_aparttalk"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.media3.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.google.android.material:material:1.11.0")
    implementation ("me.relex:circleindicator:2.1.6") // 이미지 몇 번째인지 파악하기 위한 circle indicator
    implementation ("androidx.viewpager2:viewpager2:1.0.0") // 뷰페이저

    //GifImageView 사용
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.19")

    implementation("com.google.android.material:material:1.11.0")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")

}