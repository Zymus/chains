plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
            testTask {
                useKarma {
                    useFirefox()
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        jsMain.dependencies {
            implementation(libs.kotlin.wrappers.css)
            implementation(libs.kotlin.wrappers.react)
            implementation(libs.kotlin.wrappers.react.dom)
            implementation(libs.kotlinx.serialization.core)
            implementation(project(":core"))
        }
    }
}
