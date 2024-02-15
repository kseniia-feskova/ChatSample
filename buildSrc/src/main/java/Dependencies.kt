import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial = "androidx.compose.material3:material3"
    const val composeTest = "androidx.compose.ui:ui-test-manifest"
    const val composeTooling = "androidx.compose.ui:ui-tooling"

    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebase}"
    const val firestore = "com.google.firebase:firebase-firestore-ktx"

    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"

    const val kotpref = "com.chibatching.kotpref:kotpref:${Versions.kotpref}"

    const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxJava}"

    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

}

fun DependencyHandler.compose() {
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composePreview)
    implementation(platform(Dependencies.firebaseBom))

    androidTestImplementation(platform(Dependencies.composeBom))
    debugImplementation(Dependencies.composeTooling)
    debugImplementation(Dependencies.composeTest)
}

fun DependencyHandler.firebase() {
    implementation(Dependencies.firestore)
    implementation(platform(Dependencies.firebaseBom))
}

fun DependencyHandler.navigation() {
    implementation(Dependencies.navigation)
}

fun DependencyHandler.dagger() {
    implementation(Dependencies.dagger)
    implementation(Dependencies.daggerAndroid)
    implementation(Dependencies.daggerSupport)
    kapt(Dependencies.daggerCompiler)
    kapt(Dependencies.daggerProcessor)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConverter)
    implementation(Dependencies.retrofitRxJava)
}

fun DependencyHandler.kotpref() {
    implementation(Dependencies.kotpref)
}

fun DependencyHandler.rxJava() {
    implementation(Dependencies.rxJava)
    implementation(Dependencies.rxAndroid)
}

fun DependencyHandler.coil() {
    implementation(Dependencies.coil)
}

fun DependencyHandler.room() {
    implementation(Dependencies.room)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.data() {
    implementation(project(":chat-data"))
}

fun DependencyHandler.domain() {
    implementation(project(":chat-domain"))
}

fun DependencyHandler.presentation() {
    implementation(project(":chat-presentation"))
}