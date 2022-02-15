// Top-level build file where you can add configuration options common to all sub-projects/modules.
apply(plugin = "com.github.ben-manes.versions")
task clean(type: Delete) {
    delete rootProject.buildDir
}