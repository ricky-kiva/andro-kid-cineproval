// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
}

buildscript {

    extra.apply {
        set("room_version", "2.5.2")
        set("glide_version", "4.15.1")
        set("retrofit_version", "2.9.0")
        set("logging_interceptor_version", "4.11.0")
    }

    repositories {
        google()
        mavenCentral()
    }

}