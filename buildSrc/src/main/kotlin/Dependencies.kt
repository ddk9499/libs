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
    val toothpick = Toothpick
    val network = Network

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3"
    }

    object Ktorm {
        private const val version = "2.6"
        const val pgsql = "me.liuwj.ktorm:ktorm-support-postgresql:$version"
    }

    object Toothpick {
        private const val version = "3.1.0"
        const val runtime = "com.github.stephanenicolas.toothpick:toothpick-runtime:$version"
    }

    object Network {
        private const val okHttpVersion = "4.2.2"

        const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
        const val logging = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    }

    val gson = "com.google.code.gson:gson:2.8.6"
}
