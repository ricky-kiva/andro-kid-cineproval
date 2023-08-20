plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}
apply(from = "../shared_dependencies.gradle")

android {
    namespace = "com.rickyslash.kidcineproval.core"
    compileSdk = 33

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    val kotlinVer = rootProject.extra["kotlin_version"]
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVer")

    val roomVer = rootProject.extra["room_version"] as String
    val retrofitVer = rootProject.extra["retrofit_version"] as String
    val loggingInterceptorVer = rootProject.extra["logging_interceptor_version"] as String
    val lifecycleVer = rootProject.extra["lifecycle_version"] as String

    // Room
    implementation("androidx.room:room-runtime:$roomVer")
    kapt("androidx.room:room-compiler:$roomVer")
    androidTestImplementation("androidx.room:room-testing:$roomVer")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVer")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVer")
    implementation("com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVer")

    // Coroutines Flow
    implementation("androidx.room:room-ktx:$roomVer")

    // Lifecycle
    api("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVer")
}