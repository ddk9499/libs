/**
 * Created at November 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinAndroidTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinCommonCompilation
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinJvmCompilation
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinOnlyTarget

inline fun KotlinMultiplatformExtension.jvmWithPublication(
        project: Project,
        crossinline configure: KotlinOnlyTarget<KotlinJvmCompilation>.() -> Unit = { }
) = jvm {
    mavenPublication { artifactId = project.name }; configure()
}

inline fun KotlinMultiplatformExtension.androidWithPublication(
        project: Project,
        crossinline configure: KotlinAndroidTarget.() -> Unit = { }
) = android {
    publishLibraryVariants("release")
    mavenPublication { artifactId = project.name }; configure()
}

inline fun KotlinMultiplatformExtension.metadataPublication(
        project: Project,
        crossinline configure: KotlinOnlyTarget<KotlinCommonCompilation>.() -> Unit = { }
) = metadata {
    mavenPublication { artifactId = "${project.name}-metadata" }; configure()
}

object Publishing {
    const val gitUrl = "https://github.com/ddk9499/libs.git"
    const val siteUrl = "https://github.com/ddk9499/libs"
    const val libraryDesc = "A collection of light libraries in Kotlin."
}

@Suppress("UnstableApiUsage")
fun MavenPublication.setupPom() = pom {
    name.set("libs")
    description.set(Publishing.libraryDesc)
    url.set(Publishing.siteUrl)
    licenses {
        license {
            name.set("The Apache Software License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
        }
    }
    developers {
        developer {
            id.set("ddk9499")
            name.set("Dostonbek Kamalov")
            email.set("ddk9499@gmail.com")
        }
    }
    scm {
        connection.set(Publishing.gitUrl)
        developerConnection.set(Publishing.gitUrl)
        url.set(Publishing.siteUrl)
    }
}

fun PublishingExtension.setupAllPublications(project: Project) {
    project.configurations.create("compileClasspath")
    //TODO: Remove line above when https://youtrack.jetbrains.com/issue/KT-27170 is fixed
    project.group = "uz.dkamaloff.libs"
    project.version = ProjectVersions.thisLibrary
    val publications = publications.withType<MavenPublication>()
    publications.all { setupPom() }
    publications.findByName("kotlinMultiplatform")?.apply {
        artifactId = "${project.name}-mpp"
    }
}
