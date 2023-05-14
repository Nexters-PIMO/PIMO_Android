plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.serialization").version("1.7.20")
}

dependencies {
    implementation(project(Modules.DATA))

    implementation(libs.coroutines.core)
    implementation(libs.bundles.retrofit)

    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}
