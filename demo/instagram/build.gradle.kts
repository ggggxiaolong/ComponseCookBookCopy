import com.chuntian.buildsrc.dependencies.addComposeOfficialDependencies
import com.chuntian.buildsrc.dependencies.addComposeThirdPartyDependencies
import com.chuntian.buildsrc.dependencies.addCoreAndroidUiDependencies

plugins {
    id("common-compose-module-configs-script-plugin")
}

dependencies {
    implementation(project(":data"))
    implementation(project(":theme"))

    addComposeOfficialDependencies()
    addComposeThirdPartyDependencies()
    addCoreAndroidUiDependencies()
}