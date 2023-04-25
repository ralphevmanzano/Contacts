plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.codev.recruitment.ralphemersonmanzano.home"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(Deps.androidxCore)
    implementation(Deps.appCompat)
    implementation(Deps.material)

    implementation(Deps.hilt)
    kapt(Deps.hiltCompiler)

    implementation(Deps.navigationUI)
    implementation(Deps.navigationFragment)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.junitTest)
    androidTestImplementation(Deps.espresso)
}