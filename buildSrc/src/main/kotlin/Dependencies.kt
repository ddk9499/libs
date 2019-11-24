/**
 * Created at November 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

object Versions {
    const val kotlin = "1.3.60" // Don't forget to update in buildSrc/build.gradle.kts too!
}

object Libs {

    val kotlin = Kotlin
    val ktorm = Ktorm

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    }

    object Ktorm {
        private const val version = "2.6"
        const val pgsql = "me.liuwj.ktorm:ktorm-support-postgresql:$version"
    }
}
