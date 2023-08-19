plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.rickyslash.kidcineproval"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.rickyslash.kidcineproval"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    val roomVer = rootProject.extra["room_version"] as String
    val glideVer = rootProject.extra["glide_version"] as String
    val retrofitVer = rootProject.extra["retrofit_version"] as String
    val loggingInterceptorVer = rootProject.extra["logging_interceptor_version"] as String
    val koinVer = rootProject.extra["koin_version"] as String
    val lifecycleVer = rootProject.extra["lifecycle_version"] as String
    val recyclerviewVer = rootProject.extra["recyclerview_version"] as String

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // UI
    implementation("com.github.bumptech.glide:glide:$glideVer")
    implementation("androidx.recyclerview:recyclerview:$recyclerviewVer")

    // Room
    implementation("androidx.room:room-runtime:$roomVer")
    kapt("androidx.room:room-compiler:$roomVer")
    androidTestImplementation("androidx.room:room-testing:$roomVer")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVer")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVer")
    implementation("com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVer")

    // Koin
    implementation("io.insert-koin:koin-core:$koinVer")
    implementation("io.insert-koin:koin-android:$koinVer")
    implementation("io.insert-koin:koin-android-viewmodel:$koinVer")

    // Lifecycle
    api("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVer")

    // Coroutines Flow
    implementation("androidx.room:room-ktx:$roomVer")

}