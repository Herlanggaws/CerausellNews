plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.ksp)
	alias(libs.plugins.hilt)
	alias(libs.plugins.kotlin.serialization)
}

android {
	namespace = "com.herlangga.core"
	compileSdk = libs.versions.compileSdk.get().toInt()

	defaultConfig {
		minSdk = libs.versions.minSdk.get().toInt()

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = libs.versions.jvmTarget.get()
	}
	buildFeatures {
		buildConfig = true
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.1"
	}
}

dependencies {
	implementation(libs.dagger.hilt)
	ksp(libs.dagger.hilt.compiler)
	api(libs.dagger.hilt.compose.navigation)

	api(libs.bundles.networking)

	debugImplementation(libs.chucker)
	releaseImplementation(libs.chucker.no.op)

	api(libs.gson)
	api(libs.retrofit.gson)

	api(libs.androidx.core.ktx)
	api(libs.androidx.appcompat)
	api(libs.material)

	api(libs.androidx.lifecycle.runtime.ktx)
	api(libs.androidx.activity.compose)
	api(platform(libs.androidx.compose.bom))
	api(libs.androidx.ui)
	api(libs.androidx.ui.graphics)
	api(libs.androidx.ui.tooling.preview)
	api(libs.androidx.material3)
	api(libs.androidx.lifecycle.runtime.compose.android)
	api(libs.coil)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}
