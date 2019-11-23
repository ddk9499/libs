plugins {
    kotlin("jvm")
}

kotlin {
    sourceSets {
        getByName("main").dependencies {
            api(Libs.kotlin.stdlib)
            api(Libs.ktorm.ktormPGSQL)
        }
    }
}
