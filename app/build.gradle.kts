import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())
val kakaoApikey = localProperties.getProperty("KAKAO_API_KEY")?:""
val nativeAppkey = localProperties.getProperty("NATIVE_APP_KEY")?:""
val kakaoRestApiKey = localProperties.getProperty("KAKAO_REST_API_KEY")?:""
val serverClientId = localProperties.getProperty("SERVER_CLIENT_ID") ?: ""
val clientId = localProperties.getProperty("CLIENT_ID") ?: ""
val clientSecret = localProperties.getProperty("CLIENT_SECRET") ?: ""
val clientName = localProperties.getProperty("CLIENT_NAME") ?: ""
val naverCallbackUrl = localProperties.getProperty("NAVER_CALLBACK_URL") ?: ""

android {
    namespace = "kr.co.lion.application.finalproject_aparttalk"
    compileSdk = 34

    defaultConfig {
        applicationId = "kr.co.lion.application.finalproject_aparttalk"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "KAKAO_API_KEY", kakaoApikey)
        buildConfigField("String", "KAKAO_REST_API_KEY", kakaoRestApiKey)
        manifestPlaceholders["NATIVE_APP_KEY"] = nativeAppkey
        buildConfigField("String", "SERVER_CLIENT_ID", serverClientId)
        buildConfigField("String", "CLIENT_ID", clientId)
        buildConfigField("String", "CLIENT_SECRET", clientSecret)
        buildConfigField("String", "CLIENT_NAME", clientName)
        buildConfigField("String", "NAVER_CALLBACK_URL", naverCallbackUrl)

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
    buildFeatures{
        buildConfig = true
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.media3.common)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.firebase.storage.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.google.android.material:material:1.11.0")
    implementation ("me.relex:circleindicator:2.1.6") // 이미지 몇 번째인지 파악하기 위한 circle indicator
    implementation ("androidx.viewpager2:viewpager2:1.1.0") // 뷰페이저

    // GifImageView 사용
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.19")

    // Navigation 사용
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // ViewModel Lifecycle
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //youtubePlayer 라이브러리를 사용
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")

    //indicator 라이브러리
    implementation("com.tbuonomo:dotsindicator:5.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-analytics-ktx:22.0.1")
    implementation("com.google.firebase:firebase-firestore-ktx:25.0.0")
    implementation("com.google.firebase:firebase-auth-ktx:23.0.0")

    // glide
    implementation ("com.firebaseui:firebase-ui-storage:7.2.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // 구글 Credential Manager 라이브러리
    implementation("androidx.credentials:credentials:1.2.2")
    implementation("androidx.credentials:credentials-play-services-auth:1.2.2")
    implementation(libs.googleid)

    // Gson 라이브러리
    implementation("com.google.code.gson:gson:2.10.1")
    
    implementation ("com.kakao.sdk:v2-all:2.20.1")
    implementation ("com.kakao.maps.open:android:2.9.5")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // 네이버 로그인
    implementation("com.navercorp.nid:oauth:5.9.1")
}