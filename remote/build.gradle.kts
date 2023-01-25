plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(Modules.DATA))

    implementation(libs.bundles.ktor)
}
