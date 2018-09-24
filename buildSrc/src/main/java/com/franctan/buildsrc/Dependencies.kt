object Gradle {
    val version = "3.1.4"
}



object Kotlin {
    val version = "1.2.50"
    val serializationVersion = "0.6.0"
    val reflectVersion = "1.2.0"
    val coroutinesVersion = "0.24.0"

    val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$reflectVersion"
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"

    val jdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    val reflection = "org.jetbrains.kotlin:kotlin-reflect:$version"
    val serialization = "org.jetbrains.kotlinx:kotlinx-gradle-serialization-plugin:$serializationVersion"
    val serializationRuntime = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion"
}

object SdkLevel {
    val compileSdk = 28
    val minSdk = 21
    // Do not use target SDK 27, till google figures out these issues
    // https://issuetracker.google.com/issues/110172258
    // https://issuetracker.google.com/issues/68454482
    val targetSdk = 28
    val buildTools = "28.0.1"
}

object LpLibs {
    val lpshared = "lpm-shared:1.0.3@aar"
    val auth = "lpm-authlib:2.0.1.21@aar"
    val imageview = "subsampling-scale-image-view:3.4.1@aar"
}

object DroidSupport {
//    val support_version = "28.0.0-alpha1"
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
    val roomVersion = "1.1.1"
    val lifeCycleVersion = "1.1.1"
    val testingVersion = "1.1.1"
    val workManagerVersion = "1.0.0-alpha08"
    val databindingVersion = "2.3.0"

    val roomRuntime = "android.arch.persistence.room:runtime:$roomVersion"
    val roomCompiler = "android.arch.persistence.room:compiler:$roomVersion"
    val roomRx = "android.arch.persistence.room:rxjava2:$roomVersion"

    val lifeCycleRuntime = "android.arch.lifecycle:runtime:$lifeCycleVersion"
    val lifeCycleCompiler = "android.arch.lifecycle:compiler:$lifeCycleVersion"
    val lifeCycleExtensions = "android.arch.lifecycle:extensions:$lifeCycleVersion"

    val workManagerRuntime = "android.arch.work:work-runtime-ktx:$workManagerVersion"

    val databinding = "com.android.databinding:compiler:$databindingVersion"
}


object GooglePlay {
    val anyalyticsVersion = "16.0.0"
    val playServicesVersion = "15.0.1"

    val servicePluginVersion = "3.3.1"

    val auth = "com.google.android.gms:play-services-auth:$playServicesVersion"
    val location = "com.google.android.gms:play-services-location:$playServicesVersion"
    val gcm = "com.google.android.gms:play-services-gcm:$playServicesVersion"
    val tagManager = "com.google.android.gms:play-services-tagmanager:$anyalyticsVersion"
    val analytics = "com.google.android.gms:play-services-analytics:$anyalyticsVersion"

}

object Firebase {
    val version1 = "16.0.3"
    val version2 = "16.0.1"

    val core = "com.google.firebase:firebase-core:$version1"
    val database = "com.google.firebase:firebase-database:$version2"

}

object Retrofit {
    val version = "2.4.0"

    val main = "com.squareup.retrofit2:retrofit:$version"
    val rx2Support = "com.squareup.retrofit2:adapter-rxjava2:$version"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
}

object OkHttp {
    val version = "3.11.0"

    val main = "com.squareup.okhttp3:okhttp:$version"
    val interceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    val urlConnection = "com.squareup.okhttp3:okhttp-urlconnection:$version"
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
    val relay = "com.jakewharton.rxrelay2:rxrelay:2.0.0"
    val replay = "com.jakewharton.rx2:replaying-share:2.0.1"
    val reactiveNetwork = "com.github.pwittchen:reactivenetwork-rx2:2.1.0"
    val rxLint = "nl.littlerobots.rxlint:rxlint:1.6.1"

}

object UI {
    val butterknifeVersion = "8.8.1"

    val materialDrawer = "com.mikepenz:materialdrawer:5.9.0@aar"
    val calligraphy = "uk.co.chrisjenx:calligraphy:2.3.0"

    val butterKnifeMain = "com.jakewharton:butterknife:$butterknifeVersion"
    val butterKnifeCompiler = "com.jakewharton:butterknife-compiler:$butterknifeVersion"


    val circleIndicator = "me.relex:circleindicator:1.2.2@aar"
    val parallaxScroll = "com.github.nirhart:parallaxscroll:1.0"
    val autofitText = "me.grantland:autofittextview:0.2.1"
    val expandableRecyclerView = "com.bignerdranch.android:expandablerecyclerview:3.0.0-RC1"
    val pixplicity = "com.pixplicity.htmlcompat:library:1.1.1"
    val rating = "net.mediavrog:integrated-rating-request:1.1.2"
    val groupie = "com.xwray:groupie:2.1.0"
    val groupieKotlinExt = "com.xwray:groupie-kotlin-android-extensions:2.1.0"
}

object Glide {
    val glideVersion = "4.7.1"

    val main = "com.github.bumptech.glide:glide:$glideVersion"
    val compiler = "com.github.bumptech.glide:compiler:$glideVersion"
    val okHttp = "com.github.bumptech.glide:okhttp3-integration:$glideVersion"
    val transformations = "jp.wasabeef:glide-transformations:3.3.0@aar"
    val annotations = "com.github.bumptech.glide:annotations:$glideVersion"
    val gpuImage = "jp.co.cyberagent.android.gpuimage:gpuimage-library:1.4.1"
}

object BrightCove {
    val version = "6.0.1"

    val main = "com.brightcove.player:android-sdk:$version"
    val exoplayer = "com.brightcove.player:exoplayer:$version"
}

object Parse {
    val main = "com.parse:parse-android:1.16.6"
    val bolts = "com.parse.bolts:bolts-android:1.4.0"
    val interceptors = "com.parse:parseinterceptors:0.0.2@aar"
}

object ReleaseSupport {
    val fabricVersion = "1.25.4"

    val hockey = "net.hockeyapp.android:HockeySDK:5.1.0"
    val crashlyticsCore = "com.crashlytics.sdk.android:crashlytics:2.9.3"
    val crashlyticsAnswers = "com.crashlytics.sdk.android:answers:1.4.2"
    val branch = "io.branch.sdk.android:library:2.19.2"
    val zendesk = "com.zendesk:sdk:1.11.0.1"
    val comscore = "com.comscore:android-analytics:5.6.0"

}


object Utils {
    val paperParcelVersion = "2.0.6"

    val javaxValidation = "javax.validation:validation-api:1.1.0.Final"
    val javaxAnnotation = "javax.annotation:jsr250-api:1.0"
    val javaxInject = "javax.inject:javax.inject:1"
    val apacheLangCommons = "org.apache.commons:commons-lang3:3.6"
    val ioCommons = "commons-io:commons-io:2.5"
    val timber = "com.jakewharton.timber:timber:4.7.1"
    val eventBus = "org.greenrobot:eventbus:3.1.1"
    val jobQueue = "com.birbit:android-priority-jobqueue:2.0.1"
    val jsoup = "org.jsoup:jsoup:1.11.3"
    val jodaTime = "joda-time:joda-time:2.10"
    val stetho = "com.facebook.stetho:stetho:1.5.0"
    val findBugs = "com.google.code.findbugs:jsr305:3.0.2"
    val moshi = "com.squareup.moshi:moshi-kotlin:1.6.0"
    val guavaAndroid = "com.google.guava:guava:24.0-android"
    val gson = "com.google.code.gson:gson:2.8.0"

    val paperMain = "nz.bradcampbell:paperparcel:$paperParcelVersion"
    val paperKotlin = "nz.bradcampbell:paperparcel-kotlin:$paperParcelVersion"
    val paperCompiler = "nz.bradcampbell:paperparcel-compiler:$paperParcelVersion"

    val nimBus = "com.nimbusds:nimbus-jose-jwt:4.34.2"
}

object Maps {
    val mapboxSdk = "com.mapbox.mapboxsdk:mapbox-android-sdk:6.3.0"
    val locationLayer = "com.mapbox.mapboxsdk:mapbox-android-plugin-locationlayer:0.7.1"
}

object Buy {

    val appBilling = "com.anjlab.android.iab.v3:library:1.0.44"
}


object Social {
    val twitterVersion = "3.1.0"

    val helpShift = "com.helpshift:android-helpshift-aar:7.2.0"
    val facebook = "com.facebook.android:facebook-android-sdk:4.31.0"
    val twitter_core = "com.twitter.sdk.android:twitter-core:$twitterVersion"
    val twitter_tweet = "com.twitter.sdk.android:tweet-ui:$twitterVersion"
}

object GraphQL {
    val apolloVersion = "1.0.0-alpha"
    val apolloGradle = "com.apollographql.apollo:apollo-gradle-plugin:$apolloVersion"

    val apolloAndroid = "com.apollographql.apollo:apollo-android-support:$apolloVersion"
    val apolloRuntime = "com.apollographql.apollo:apollo-runtime:$apolloVersion"
    val apolloRx = "com.apollographql.apollo:apollo-rx2-support:$apolloVersion"

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

object DebugDb {
    val debugDb = "com.amitshekhar.android:debug-db:1.0.3"
}


