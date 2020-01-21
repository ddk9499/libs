plugins {
    id("com.android.library")
    kotlin("multiplatform")
    `maven-publish`
    id("com.jfrog.bintray")
}

android {
    setDefaults()
}

kotlin {
    metadataPublication(project)
    androidWithPublication(project)
    sourceSets {
        getByName("androidMain").dependencies {
            api(kotlin("stdlib"))
            api(Libs.toothpick.runtime)
        }
    }
}

afterEvaluate {
    publishing {
        setupAllPublications(project)
    }

    bintray {
        setupPublicationsUpload(project, publishing, skipMetadataPublication = true)
    }
}