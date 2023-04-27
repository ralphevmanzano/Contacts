plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.hiltAndroid)
    id(Plugins.safeArgs)
    kotlin(Plugins.kapt)
}

android {
    namespace = "com.codev.recruitment.ralphemersonmanzano.details"
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion

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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(project(Modules.shared))
    implementation(Deps.androidxCore)
    implementation(Deps.appCompat)
    implementation(Deps.material)

    implementation(Deps.hilt)
    kapt(Deps.hiltCompiler)

    implementation(Deps.navigationUI)
    implementation(Deps.navigationFragment)

    testImplementation(project(Modules.testUtils))
    testImplementation(Deps.junit)
    testImplementation(Deps.mockito)
    testImplementation(Deps.archTesting)
    testImplementation(Deps.coroutinesTest)
    androidTestImplementation(Deps.junitTest)
    androidTestImplementation(Deps.espresso)
}