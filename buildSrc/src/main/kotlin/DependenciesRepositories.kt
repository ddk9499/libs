/**
 * Created at November 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

fun RepositoryHandler.setupForProject() {
    jcenter()

    mavenCentral().ensureGroupsStartingWith("com.jakewharton.", "com.squareup.")

    google().ensureGroups(
        "com.google.gms",
        "com.google.firebase",
        "io.fabric.sdk.android",
        "com.crashlytics.sdk.android",
        "org.chromium.net"
    ).ensureGroupsStartingWith(
        "androidx.",
        "com.android.",
        "com.google.android.",
        "com.google.ar",
        "android.arch"
    )

    maven(url = "https://kotlin.bintray.com/kotlinx").ensureModulesByRegexp("org.jetbrains.kotlinx:kotlinx-serialization\\-.*")

    maven(url = "https://oss.sonatype.org/content/repositories/snapshots").ensureGroups("org.androidannotations")
}
