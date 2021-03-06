plugins {
    kotlin("multiplatform")
    `maven-publish`
    id("com.jfrog.bintray")
}

kotlin {
    metadataPublication(project)
    jvmWithPublication(project)
    sourceSets {
        getByName("jvmMain").dependencies {
            api(kotlin("stdlib"))
            api(Libs.network.okHttp)
            api(Libs.gson)
        }
    }
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xallow-result-return-type")
        }
    }
}

publishing {
    setupAllPublications(project)
}

bintray {
    setupPublicationsUpload(project, publishing)
}
