plugins {
    id(Plugins.ANDROID_APP)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.KOTLIN_PARCELIZE)
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APP_ID
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = Releases.VERSION_CODE
        versionName = Releases.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }
}

dependencies {

    implementation (Dependencies.KTX_CORE)
    implementation (Dependencies.APPCOMPAT)
    implementation (Dependencies.ANDROID_MATERIAL)
    implementation (Dependencies.CONSTRAINT_LAYOUT)
    testImplementation (Dependencies.JUNIT)
    androidTestImplementation (Dependencies.TEST_JUNIT)
    androidTestImplementation (Dependencies.TEXT_ESPRESSO)

    // Retrofit
    implementation (Dependencies.RETROFIT)
    implementation (Dependencies.RETROFIT_CONVERTER_GSON)

    // Koin
    implementation (Dependencies.KOIN_CORE)
    implementation (Dependencies.KOIN_ANDROID)

    // Coroutines
    implementation (Dependencies.COROUTINES_CORE)
    implementation (Dependencies.COROUTINES_ANDROID)
    implementation (Dependencies.RETROFIT_COROUTINES_ADAPTER)

    //Glide
    implementation (Dependencies.GLIDE)
    kapt (Dependencies.GLIDE_COMPILER)

    //Room
    implementation (Dependencies.ROOM_RUNTIME)
    implementation (Dependencies.ROOM_KTX)
    kapt (Dependencies.ROOM_COMPILER)

    //modules
    implementation(project(Modules.CORE_MODULE))
}