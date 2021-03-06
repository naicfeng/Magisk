plugins {
    id("com.android.application")
}

android {
    val canary = !Config.appVersion.contains(".")

    defaultConfig {
        applicationId = "com.topjohnwu.magisk"
        versionCode = 1
        versionName = Config.appVersion
        buildConfigField("int", "STUB_VERSION", "15")
        buildConfigField("boolean", "CANARY", if (canary) "true" else "false")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = false
            proguardFiles("proguard-rules.pro")
        }
    }

    aaptOptions {
        additionalParameters("--package-id", "0x80")
    }

    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }
}

dependencies {
    implementation(project(":app:shared"))
}
