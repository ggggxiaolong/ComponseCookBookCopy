package com.chuntian.buildsrc.dependencies

object Versions {
    const val compose = "1.1.0"
    const val lifecycleViewModelCompose = "2.4.0-rc01"
    const val activityCompose = "1.3.1"
    const val pagingCompose = "1.0.0-alpha12"
    const val navCompose = "2.4.0-rc01"
    const val constraintLayoutCompose = "1.0.0"
    const val fontAwesomeCompose = "1.0.0-beta02"
    const val accompanistCoil = "0.14.0"
    const val coilCompose = "1.4.0"
    const val kotlin = "1.6.10"
    const val coroutines = "1.6.0"
    const val androidLifecycleGrouped = "2.3.1"
    const val flinger = "1.0.5"
    const val paging = "3.0.1"
    const val lottie = "4.2.0"
    const val room = "2.4.1"
    const val coreKtx = "1.6.0"
    const val appcompat = "1.3.1"
    const val paletteKtx = "1.0.0"
    const val material = "1.4.0"
    const val material3 = "1.0.0-alpha06"
    const val playServicesAds = "19.6.0"
    const val googleMaps = "3.1.0-beta"
    const val playServicesMaps = "17.0.1"
    const val exoplayer = "2.14.2"
    const val loggingInterceptor ="4.9.1"
    const val retrofit = "2.9.0"
    const val serialization = "1.2.2"
    const val junit = "5.7.0"
    const val truth = "1.1.3"
    const val androidXJunit = "1.1.2"
    const val desugar = "1.1.5"
    const val dialogs = "0.6.0"
    const val timber = "5.0.1"
    const val accompanist = "0.20.0"
    const val biometric = "1.2.0-alpha03"
}

object Dependencies {
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val fontAwesomeCompose = "com.github.Gurupreet:FontAwesomeCompose:${Versions.fontAwesomeCompose}"
    const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
    const val androidCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val androidAppCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val androidPaletteKtx = "androidx.palette:palette-ktx:${Versions.paletteKtx}"
    const val googleMaterial = "com.google.android.material:material:${Versions.material}"
    const val playServicesAds = "com.google.android.gms:play-services-ads:${Versions.playServicesAds}"
    const val googleMaps = "com.google.android.libraries.maps:maps:${Versions.googleMaps}"
    const val playServicesMaps = "com.google.android.gms:play-services-maps:${Versions.playServicesMaps}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeUiUtil = "androidx.compose.ui:ui-util:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.material3}"
    const val composeMaterialIconsExtended =
        "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val composeRuntimeLivedata =
        "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val composeConstraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutCompose}"
    const val composePaging = "androidx.paging:paging-compose:${Versions.pagingCompose}"
    const val composeViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewModelCompose}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeLottie = "com.airbnb.android:lottie-compose:${Versions.lottie}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"

    const val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

    const val desugar = "com.android.tools:desugar_jdk_libs:${Versions.desugar}"

    const val dialogsCore = "io.github.vanpra.compose-material-dialogs:core:${Versions.dialogs}"
    const val dialogsDatePicker = "io.github.vanpra.compose-material-dialogs:datetime:${Versions.dialogs}"
    /**
     * Custom fling behaviour
     * refer to https://github.com/iamjosephmj/flinger for more insights on the library
     */
    const val flinger = "com.github.iamjosephmj:flinger:${Versions.flinger}"

    const val androidPagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val androidExoPlayer = "com.google.android.exoplayer:exoplayer:${Versions.exoplayer}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    const val coilAccompanist =
        "com.google.accompanist:accompanist-coil:${Versions.accompanistCoil}"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val viewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidLifecycleGrouped}"
    const val liveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidLifecycleGrouped}"
    const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidLifecycleGrouped}"
    const val lifecycleSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.androidLifecycleGrouped}"
    const val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    const val junitJupiterEngine =
        "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val kotlinJunit5 = "org.jetbrains.kotlin:kotlin-test-junit5:${Versions.kotlin}"
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
    const val androidXJunit = "androidx.test.ext:junit:${Versions.androidXJunit}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val composeInsets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
    const val composeSystemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    const val composePager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
    const val composePagerIndicator = "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanist}"
    const val composePermission = "com.google.accompanist:accompanist-permissions:${Versions.accompanist}"
    const val composePlaceholder = "com.google.accompanist:accompanist-placeholder:${Versions.accompanist}"
    const val composeFlowlayout = "com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}"
    const val composeNavigationAnimation = "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
    const val composeNavigationMaterial = "com.google.accompanist:accompanist-navigation-material:${Versions.accompanist}"
    const val composeDrawablePainer = "com.google.accompanist:accompanist-drawablepainter:${Versions.accompanist}"
    const val composeSwipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
    const val biometric = "androidx.biometric:biometric:${Versions.biometric}"
}