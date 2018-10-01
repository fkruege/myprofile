

object Kotlin {
    val version = "1.2.51"
    val reflectVersion = "1.2.51"

    val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$reflectVersion"

    val jdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    val reflection = "org.jetbrains.kotlin:kotlin-reflect:$version"
}

object SdkLevel {
    val compileSdk = 28
    val minSdk = 21
    val targetSdk = 28
}


object DroidSupport {
    val support_version = "28.0.0"

    val annotations = "com.android.support:support-annotations:$support_version"
    val appcompatv7 = "com.android.support:appcompat-v7:$support_version"
    val design = "com.android.support:design:$support_version"
    val v4Support = "com.android.support:support-v4:$support_version"
    val cardView = "com.android.support:cardview-v7:$support_version"
    val recyclerView = "com.android.support:recyclerview-v7:$support_version"
    val palette = "com.android.support:palette-v7:$support_version"
    val percentLayout = "com.android.support:percent:$support_version"
    val vectoryDrawable = "com.android.support:animated-vector-drawable:$support_version"
    val customTabs = "com.android.support:customtabs:$support_version"
    val preferences = "com.android.support:preference-v7:$support_version"
    val constraintLayout = "com.android.support.constraint:constraint-layout:1.1.2"
}

object GoogleArch {
    val lifeCycleVersion = "1.1.1"
    val databindingVersion = "2.3.0"


    val lifeCycleRuntime = "android.arch.lifecycle:runtime:$lifeCycleVersion"
    val lifeCycleCompiler = "android.arch.lifecycle:compiler:$lifeCycleVersion"
    val lifeCycleExtensions = "android.arch.lifecycle:extensions:$lifeCycleVersion"

    val databinding = "com.android.databinding:compiler:$databindingVersion"
}


object Firebase {
    val otherVersion = "16.0.3"
    val coreVersion = "16.0.1"

    val core = "com.google.firebase:firebase-core:$otherVersion"
    val database = "com.google.firebase:firebase-database:$coreVersion"
    val cloudStorage = "com.google.firebase:firebase-storage:$coreVersion"

}

object Dagger {
    val version = "2.16"

    val main = "com.google.dagger:dagger:$version"
    val compiler = "com.google.dagger:dagger-compiler:$version"
    val android = "com.google.dagger:dagger-android:$version"
    val androidSupport = "com.google.dagger:dagger-android-support:$version"
    val androidProcessor = "com.google.dagger:dagger-android-processor:$version"

}

object Rx {
    val rx2Version = "2.2.0"

    val rx2Main = "io.reactivex.rxjava2:rxjava:$rx2Version"
    val rx2Android = "io.reactivex.rxjava2:rxandroid:2.1.0"
    val rxPreferences = "com.f2prateek.rx.preferences2:rx-preferences:2.0.0"

}

object Glide {
    val glideVersion = "4.8.0"

    val main = "com.github.bumptech.glide:glide:$glideVersion"
    val compiler = "com.github.bumptech.glide:compiler:$glideVersion"
}

object Utils {

    val javaxValidation = "javax.validation:validation-api:1.1.0.Final"
    val javaxAnnotation = "javax.annotation:jsr250-api:1.0"
    val javaxInject = "javax.inject:javax.inject:1"
    val timber = "com.jakewharton.timber:timber:4.7.1"
    val jodaTime = "joda-time:joda-time:2.10"
    val circularView = "xyz.schwaab:avvylib:0.0.1"
    val materialDialogs = "com.afollestad.material-dialogs:color:2.0.0-beta2"

}

object Testing {
    val testRunnerVersion = "1.0.2"
    val espressoVersion = "3.0.2"
    val junitVersion = "4.12"


    val mockitoVersion = "2.19.1"
    val powermockVersion = "2.0.0-beta.5"
    val roboElectricVersion = "3.5.1"
    val androidTestSupport = "1.0.2"
    val hamcrestVersion = "1.3"

    val junit = "junit:junit:$junitVersion"
    val testRunner = "com.android.support.test:runner:$testRunnerVersion"
    val espressoCore = "com.android.support.test.espresso:espresso-core:$espressoVersion"

    val mockServer = "com.squareup.okhttp3:mockwebserver:3.9.1"
    val mockitoCore = "org.mockito:mockito-core:$mockitoVersion"
    val mockitoInline = "org.mockito:mockito-inline:$mockitoVersion"
    val mockitoAndroid = "org.mockito:mockito-android:$mockitoVersion"
    val mockitoKotlin = "com.nhaarman:mockito-kotlin:1.5.0"

    val powerMockJunit4 = "org.powermock:powermock-module-junit4:$powermockVersion"
    val powerMockJunit4Rule = "org.powermock:powermock-module-junit4-rule:$powermockVersion"
    val powerMockMockito = "org.powermock:powermock-api-mockito2:$powermockVersion"
    val powerMockClassLoading = "org.powermock:powermock-classloading-xstream:$powermockVersion"
}


