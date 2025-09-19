//plugins {
//    id("com.android.application")
//    id("com.google.gms.google-services") version "4.4.2" apply false
//    kotlin("android")
//    kotlin("kapt")
//}
//
//android {
//    namespace = "com.example.memestreamapp"
//    compileSdk = 35
//
//    defaultConfig {
//        applicationId = "com.example.memestreamapp"
//        minSdk = 21
//        targetSdk = 35
//        versionCode = 1
//        versionName = "1.0"
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//
//    buildFeatures {
//        viewBinding = true
//    }
//
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
//    kotlinOptions {
//        jvmTarget = "11"
//    }
//}
//
//dependencies {
//
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//
//    // Retrofit + Moshi
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
//    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
//
//    // Glide
//    implementation("com.github.bumptech.glide:glide:4.12.0")
//    kapt("com.github.bumptech.glide:compiler:4.12.0")
//
//    // Google Maps + Location
//    implementation("com.google.android.gms:play-services-maps:18.1.0")
//    implementation("com.google.android.gms:play-services-location:21.0.1")
//
//    // Twitter SDK (or use ACTION_SEND intent instead)
//    implementation("com.twitter.sdk.android:twitter:3.3.0")
//
//    // Room DB
//    implementation("androidx.room:room-runtime:2.6.1")
//    kapt("androidx.room:room-compiler:2.6.1")
//    implementation("androidx.room:room-ktx:2.6.1")
//
//    // Firebase BoM – keeps Firebase versions in sync
//    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
//
//    // Firebase Auth
//    implementation("com.google.firebase:firebase-auth-ktx")
//
//    // Google Sign-In
//    implementation("com.google.android.gms:play-services-auth:20.7.0")
//
//    // Biometric Authentication
//    implementation("androidx.biometric:biometric:1.1.0")
//}

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.gms.google-services") version "4.4.3"

}

android {
    compileSdk = 35
    namespace = "com.example.memestreamapp"

    defaultConfig {
        applicationId = "com.example.memestreamapp"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.firebase.messaging.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit + Moshi
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")



    // Gson converter for Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // WorkManager
    implementation ("androidx.work:work-runtime-ktx:2.8.1")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    kapt("com.github.bumptech.glide:compiler:4.12.0")

    // Google Maps + Location
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Room DB
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation ("com.google.firebase:firebase-messaging")

    // Firebase Auth
    implementation("com.google.firebase:firebase-auth-ktx")


    // Google Sign-In
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // Biometric Authentication
    implementation("androidx.biometric:biometric:1.2.0-alpha04")


    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

}
