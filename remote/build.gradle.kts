plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(project(Modules.DATA))

    implementation(libs.bundles.ktor)

    implementation(libs.hilt.core)
    implementation(libs.hilt.compiler)
}
