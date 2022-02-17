import com.chuntian.buildsrc.configurations.ProjectConfigs
import com.chuntian.buildsrc.dependencies.*
import org.jetbrains.kotlin.gradle.targets.js.npm.includedRange

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    compileSdk = ProjectConfigs.compileSdkVersion

    defaultConfig {
        multiDexEnabled = true
        applicationId = ProjectConfigs.applicationId
        minSdk = ProjectConfigs.minSdkVersion
        targetSdk = ProjectConfigs.targetSdkVersion
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        compose=true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ProjectConfigs.kotlinCompileExtensionVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    bundle {
        density{
            enableSplit = true
        }
        language{
            enableSplit = true
        }
        abi {
            enableSplit = true
        }
    }

    testOptions{
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":theme"))
    implementation(project(":demo:youtube"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
    implementation("com.google.android.libraries.maps:maps:3.1.0-beta")
//    implementation("com.mrtan.renderscript-toolkit:renderscript-toolkit:1.0")
    addDesugarDependencies()
    addKotlinDependencies()
    addDataDependencies()
    addComposeOfficialDependencies()
    addComposeDebugDependencies()
    addComposeThirdPartyDependencies()
    addThirdPartyUiDependencies()
    addCoreAndroidDependencies()
    addCoreAndroidUiDependencies()
    addGoogleAndroidDependencies()
    addNetworkingDependencies()
    addKotlinTestDependencies()
    addAccompanist()
    addJunit5TestDependencies()
    addThirdPartyUnitTestsDependencies()
    addAndroidInstrumentationTestsDependencies()
}