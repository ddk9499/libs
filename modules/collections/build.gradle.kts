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
        }
    }
}

publishing {
    setupAllPublications(project)
}

bintray {
    setupPublicationsUpload(project, publishing)
}