import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlinter)
}

android {
    compileSdk = 34
    namespace = "com.example.rotationsubn"

    defaultConfig {
        applicationId = "com.example.rotationsubn"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        compose = true
    }
    packaging{
        resources {
            excludes += listOf("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    tasks.withType<KotlinCompile>{
        compilerOptions {
            freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
        }
    }
    tasks.withType<JavaCompile>{
        compileOptions{
            options.compilerArgs.add("-Xlint:deprecation")
        }
    }

    val tmpFilePath = "${System.getProperty("user.home")}/work/_temp/keystore/"
    val allFilesFromDir = File(tmpFilePath).listFiles()

    if (allFilesFromDir != null) {
        val keystoreFile = allFilesFromDir.first()
        keystoreFile.renameTo(File("keystore/rotation_n_key.jks"))
    }

    signingConfigs {
        create("release") {
            storeFile = file("keystore/rotation_n_key.jks")
            storePassword = System.getenv("SIGNING_STORE_PASSWORD")
            keyAlias = System.getenv("SIGNING_KEY_ALIAS")
            keyPassword = System.getenv("SIGNING_KEY_PASSWORD")
        }
        getByName("debug") {
            storeFile = file("../rotation-n-debug-key.jks")
            storePassword = "c8cbdcdf"
            keyAlias = "debug"
            keyPassword = "c8cbdcdf"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    namespace = "com.example.rotationsubn"
    buildFeatures {
        buildConfig = true
    }
}

composeCompiler {
    enableStrongSkippingMode = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.compose.theme.adapter)

    implementation(libs.androidx.runtime.livedata)
    implementation(libs.hilt.android)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.ui.test.junit4.android)
    ksp(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.mockito.core)
    androidTestImplementation(libs.mockito.android)

    implementation(libs.timber)
}