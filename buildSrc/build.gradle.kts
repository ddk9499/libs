plugins {
    id("org.gradle.kotlin.kotlin-dsl") version "1.3.2"
}

repositories {
    google()
    jcenter()
}

val kotlinVersion = "1.3.70" // Don't forget to update in Dependencies.kt too!

dependencies {
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:3.6.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4")
}
