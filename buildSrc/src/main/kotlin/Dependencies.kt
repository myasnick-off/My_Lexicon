object Config {
    const val APP_ID = "com.example.mylexicon"
    const val COMPILE_SDK = 31
    const val MIN_SDK = 21
    const val TARGET_SDK = 31
}

object Releases {
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
}

object Modules {
    const val CORE_MODULE = ":Core"
}

object Plugins {
    const val ANDROID_APP = "com.android.application"
    const val ANDROID_LIB = "com.android.library"
    const val KOTLIN_ANDROID = "kotlin-android"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val KOTLIN_PARCELIZE = "kotlin-parcelize"
}

object Versions {
    const val KTX_CORE_VERSION = "1.7.0"
    const val APPCOMPAT_VERSION = "1.4.1"
    const val ANDROID_MATERIAL_VERSION = "1.5.0"
    const val CONSTRAINT_LAYOUT_VERSION = "2.1.3"
    const val TEST_JUNIT_VERSION = "1.1.3"
    const val TEST_ESPRESSO_VERSION = "3.4.0"
    // Retrofit
    const val RETROFIT_VERSION = "2.9.0"
    // Koin
    const val KOIN_VERSION = "3.1.2"
    // Coroutines
    const val COROUTINES_CORE_VERSION = "1.5.1"
    const val COROUTINES_ANDROID_VERSION = "1.5.2"
    const val RETROFIT_COROUTINES_ADAPTER_VERSION = "0.9.2"
    //Glide
    const val GLIDE_VERSION = "4.12.0"
    const val GLIDE_COMPILER_VERSION = "4.11.0"
    //Room
    const val ROOM_VERSION = "2.4.2"
    //Splash
    const val SPLASH_VERSION = "1.0.0-beta02"
}

object Dependencies {
    const val KTX_CORE = "androidx.core:core-ktx:${Versions.KTX_CORE_VERSION}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT_VERSION}"
    const val ANDROID_MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL_VERSION}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}"
    const val JUNIT = "junit:junit:4+"
    const val TEST_JUNIT = "androidx.test.ext:junit:${Versions.TEST_JUNIT_VERSION}"
    const val TEXT_ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.TEST_ESPRESSO_VERSION}"
    // Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_VERSION}"
    // Koin
    const val KOIN_CORE = "io.insert-koin:koin-core:${Versions.KOIN_VERSION}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${Versions.KOIN_VERSION}"
    // Coroutines
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_CORE_VERSION}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_ANDROID_VERSION}"
    const val RETROFIT_COROUTINES_ADAPTER = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.RETROFIT_COROUTINES_ADAPTER_VERSION}"
    //Glide
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE_VERSION}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE_COMPILER_VERSION}"
    //Room
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM_VERSION}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM_VERSION}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM_VERSION}"

    //splash
    const val SPLASH_SCREEN = "androidx.core:core-splashscreen:${Versions.SPLASH_VERSION}"
}
