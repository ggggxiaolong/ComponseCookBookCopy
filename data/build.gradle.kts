import com.chuntian.buildsrc.dependencies.addDataDependencies

plugins {
    id("org.jetbrains.kotlin.plugin.serialization")
    id("common-kotlin-module-configs-script-plugin")
    id("org.jetbrains.kotlin.plugin.parcelize")
}
android {
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas"
                )
            }
        }
    }
    namespace = "com.chuntian.data"
}
dependencies {
    addDataDependencies()
}