plugins {
    id("org.gradle.kotlin.kotlin-dsl") version "1.3.2"
}

repositories {
    google()
    jcenter()
}

val kotlinVersion = "1.3.60" // Don't forget to update in Dependencies.kt too!

dependencies {
    compileOnly(gradleApi())
//    implementation("com.android.tools.build:gradle:3.5.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
}
