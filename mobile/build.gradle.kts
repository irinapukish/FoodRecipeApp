plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = Versions.COMPILE_SDK
    defaultConfig {
        applicationId = "com.foodreceipeapp.app"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.versionCodeMobile
        versionName = Versions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["dagger.hilt.disableModulesHaveInstallInCheck"] = "true"
                arguments["room.incremental"] = "true"
            }
        }
        manifestPlaceholders["googleMapsKey"] = "AIzaSyAlPDIoP7vmHfGJwQrTjA8-29OToUIESBA"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
        }
    }

    // debug and release variants share the same source dir
    sourceSets {
        getByName("debug") {
            java.srcDir("src/debugRelease/java")
        }
        getByName("release") {
            java.srcDir("src/debugRelease/java")
        }
    }

    lint {
        // Eliminates UnusedResources false positives for resources used in DataBinding layouts
        isCheckGeneratedSources = true
        // Running lint over the debug variant is enough
        isCheckReleaseBuilds = false
        // See lint.xml for rules configuration
        isAbortOnError = false
    }

    // Required for AR because it includes a library built with Java 8
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    // To avoid the compile error: "Cannot inline bytecode built with JVM target 1.8
    // into bytecode that is being built with JVM target 1.6"
    kotlinOptions {
        val options = this
        options.jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }

    buildFeatures {
        compose = true
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
        viewBinding = true
    }

    packagingOptions {
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies {
    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))
    implementation(project(":shared"))
    testImplementation(project(":test-shared"))
    api(project(":model"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libs.APP_COMPAT)
    implementation(Libs.CORE_KTX)

    // Architecture Components
    implementation(Libs.LIFECYCLE_LIVE_DATA_KTX)
    kapt(Libs.LIFECYCLE_COMPILER)
    testImplementation(Libs.ARCH_TESTING)
    implementation(Libs.NAVIGATION_FRAGMENT_KTX)
    implementation(Libs.NAVIGATION_UI_KTX)
    implementation(Libs.FRAGMENT_KTX)
    implementation(Libs.ROOM_KTX)
    implementation(Libs.ROOM_RUNTIME)
    kapt(Libs.ROOM_COMPILER)
    implementation(Libs.LIFECYCLE_EXTENSION)
    implementation(Libs.LIFECYCLE_RUN_TIME)

    // Dagger Hilt
    implementation(Libs.HILT_ANDROID)
    implementation(Libs.HILT_VIEWMODEL)
    kapt(Libs.HILT_COMPILER)
    kapt(Libs.ANDROIDX_HILT_COMPILER)
    kaptAndroidTest(Libs.HILT_COMPILER)
    kaptAndroidTest(Libs.ANDROIDX_HILT_COMPILER)

    // Kotlin
    implementation(Libs.KOTLIN_STDLIB)

    // Local unit tests
    testImplementation(Libs.JUNIT)
    testImplementation(Libs.EXT_JUNIT)
    testImplementation(Libs.ASSERT_J)
    testImplementation(Libs.MOCKK)
    testImplementation(Libs.FAKER)

    // unit tests livedata
    testImplementation(Libs.ARCH_TESTING)

    // flow
    testImplementation(Libs.TURBINE)

    // COMPOSE
    implementation(Libs.COMPOSE_RUNTIME)
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.COMPOSE_FOUNDATION_LAYOUT)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_UI_GRAPHICS)
    implementation(Libs.COMPOSE_UI_TOOLING)
    implementation(Libs.COMPOSE_RUNTIME_LIVEDATA)
    implementation(Libs.COMPOSE_ANIMATION)
    implementation(Libs.COMPOSE_NAVIGATION)
    implementation(Libs.COMPOSE_ICON)
    implementation(Libs.COMPOSE_ACTIVITY)
    implementation(Libs.COMPOSE_CONSTRAINT)
    implementation(Libs.COMPOSE_PAGING)
    implementation(Libs.COMPOSE_VIEW_MODEL)

    implementation(Libs.INSETS)
    implementation(Libs.COIL)
    implementation(Libs.ACCOMPANIST_PERMISSION)

    androidTestImplementation(Libs.COMPOSE_TEST)
    // play service
    implementation(Libs.COROUTINES_PLAY_SERVICE)
    implementation(Libs.PLAY_SERVICE_LOCATION)

    // Maps
    api(Libs.GOOGLE_MAP_UTILS_KTX) {
        exclude(group = "com.google.android.gms")
    }
    api(Libs.GOOGLE_PLAY_SERVICES_MAPS_KTX)
}
apply(plugin = "com.google.gms.google-services")
