plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31

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