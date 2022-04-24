plugins {
    id(Plugins.ANDROID_LIB)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.KOTLIN_PARCELIZE)
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK

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
    implementation (Dependencies.RETROFIT_CONVERTER_GSON)

    // Coroutines
    implementation (Dependencies.COROUTINES_CORE)
    implementation (Dependencies.COROUTINES_ANDROID)
}