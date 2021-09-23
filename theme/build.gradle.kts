import com.chuntian.buildsrc.dependencies.addComposeOfficialDependencies
import com.chuntian.buildsrc.dependencies.addComposeThirdPartyDependencies
import com.chuntian.buildsrc.dependencies.addCoreAndroidUiDependencies

plugins {
    id("common-compose-module-configs-script-plugin")
}
dependencies {
    addComposeOfficialDependencies()
    addComposeThirdPartyDependencies()
    addCoreAndroidUiDependencies()
}