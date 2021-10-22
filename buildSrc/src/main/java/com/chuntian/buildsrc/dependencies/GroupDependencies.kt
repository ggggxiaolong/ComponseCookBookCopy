package com.chuntian.buildsrc.dependencies

internal val composeOfficialDependencies = listOf(
    Dependencies.composeUi,
    Dependencies.composeUiTooling,
    Dependencies.composeMaterial,
    Dependencies.composeMaterialIconsExtended,
    Dependencies.composeRuntimeLivedata,
    Dependencies.composeConstraintLayout,
    Dependencies.composePaging,
    Dependencies.composeViewModel,
    Dependencies.composeActivity,
    Dependencies.composeNavigation,
)

internal val composeThirdPartyDependencies = listOf(
    Dependencies.fontAwesomeCompose,
    Dependencies.composeLottie,
    Dependencies.flinger,
    Dependencies.timber,
)

internal val composeDebugDependencies = listOf(
    Dependencies.kotlinReflect,
    Dependencies.composeUiTooling,
    Dependencies.composeUiTestManifest,
)

internal val kotlinDependencies = listOf(
    Dependencies.kotlinStandardLibrary,
    Dependencies.coroutinesCore,
    Dependencies.coroutinesAndroid
)

internal val kotlinTestDependencies = listOf(
    Dependencies.kotlinTest,
    Dependencies.kotlinJunit5
)

internal val dataDependencies = listOf(
    Dependencies.roomRuntime,
    Dependencies.roomKtx,
    Dependencies.androidPagingRuntime
)

internal val coreAndroidDependencies = listOf(
    Dependencies.androidCoreKtx,
    Dependencies.androidAppCompat
)

internal val coreAndroidUiDependencies = listOf(
    Dependencies.googleMaterial,
    Dependencies.androidPaletteKtx,
    Dependencies.androidPagingRuntime,
    Dependencies.lifecycleRuntimeKtx,
    Dependencies.lifecycleSavedState,
    Dependencies.liveDataKtx,
    Dependencies.viewModelKtx
)

internal val googleAndroidLibraries = listOf(
    Dependencies.androidExoPlayer,
//    Dependencies.playServicesAds,
//    Dependencies.playServicesMaps,
//    Dependencies.googleMaps
)

internal val networkingDependencies = listOf(
    Dependencies.retrofit,
    Dependencies.okHttpLoggingInterceptor,
    Dependencies.kotlinSerialization,
)

internal val junit5TestDependencies = listOf(
    Dependencies.junitJupiterApi,
    Dependencies.junitJupiterEngine
)

internal val thirdPartyUnitTestsDependencies = listOf(
    Dependencies.truth
)

internal val androidInstrumentationTestsDependencies = listOf(
    Dependencies.composeUiTestJunit4,
    Dependencies.composeActivity,
    Dependencies.androidXJunit,
    Dependencies.composeUiTestManifest
)

internal val thirdPartyUiDependencies = listOf(
    Dependencies.coilAccompanist,
    Dependencies.coilCompose,
    Dependencies.lottie,
//    Dependencies.dialogsCore,
//    Dependencies.dialogsDatePicker
)

internal val accompanist = listOf(
    Dependencies.composeInsets,
    Dependencies.composeSystemUiController,
    Dependencies.composePager,
    Dependencies.composePagerIndicator,
    Dependencies.composePermission,
    Dependencies.composePlaceholder,
    Dependencies.composeFlowlayout,
    Dependencies.composeNavigationAnimation,
    Dependencies.composeNavigationMaterial,
    Dependencies.composeDrawablePainer,
    Dependencies.composeSwipeRefresh,
)