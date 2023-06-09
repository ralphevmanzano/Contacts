plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.safeArgs)
    id(Plugins.hiltAndroid)
    kotlin(Plugins.kapt)
}

android {
    namespace = "com.codev.recruitment.ralphemersonmanzano"
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.androidTestInstrumentation
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
    implementation(project(Modules.featureHome))
    implementation(project(Modules.featureFavorites))
    implementation(project(Modules.featureDetails))
    implementation(project(Modules.shared))

    implementation(Deps.androidxCore)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)

    // navigation
    implementation(Deps.navigationFragment)
    implementation(Deps.navigationUI)

    // hilt
    implementation(Deps.hilt)
    kapt(Deps.hiltCompiler)

    // viewmodel and livedata
    implementation(Deps.lifecycleViewModel)
    implementation(Deps.lifecycleLiveData)
    kapt(Deps.lifecycleCompiler)

    // coroutines
    implementation(Deps.coroutines)

    // room
    implementation(Deps.roomRuntime)
    implementation(Deps.roomKtx)
    kapt(Deps.roomKtx)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.junitTest)
    androidTestImplementation(Deps.espresso)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}