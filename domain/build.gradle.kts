plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinxSerialization)
    id(Plugins.kotlinParcelize)
    kotlin(Plugins.kapt)
}

android {
    namespace = "com.codev.recruitment.ralphemersonmanzano.mylibrary"
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion

        testInstrumentationRunner = Config.androidTestInstrumentation
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
}

dependencies {
    implementation(Deps.roomRuntime)
    implementation(Deps.roomKtx)
    kapt(Deps.roomCompiler)

    implementation(Deps.hilt)
    kapt(Deps.hiltCompiler)
}