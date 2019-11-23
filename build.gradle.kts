@file:Suppress("SpellCheckingInspection")

allprojects {
    repositories {
        setupForProject()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}