plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "nullex.updater"
    compileSdkPreview = "DEV"

    signingConfigs {
        create("test") {
            storeFile = rootProject.file("testkey.keystore")
            storePassword = "android"
            keyAlias = "androidtestkey"
            keyPassword = "android"
        }
    }

    defaultConfig {
        applicationId = "nullex.updater"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
            applicationIdSuffix = ".sha"
            signingConfig = signingConfigs.getByName("test")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
}