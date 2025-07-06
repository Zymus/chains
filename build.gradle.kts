plugins {
    alias(libs.plugins.kotlinMultiplatform) apply false
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.BIN
    version = "8.13"
}
