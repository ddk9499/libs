/**
 * Created at November 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

object Libs {

    val androidX = AndroidX
    val ktorm = Ktorm
    val toothpick = Toothpick
    val network = Network

    object AndroidX {
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel:2.2.0"
    }

    object Ktorm {
        private const val version = "2.6"
        const val pgsql = "me.liuwj.ktorm:ktorm-support-postgresql:$version"
    }

    object Toothpick {
        private const val version = "3.1.0"
        const val runtime = "com.github.stephanenicolas.toothpick:toothpick-runtime:$version"
        const val ktp = "com.github.stephanenicolas.toothpick:ktp:$version"
    }

    object Network {
        const val okHttp = "com.squareup.okhttp3:okhttp:4.2.2"
    }

    val gson = "com.google.code.gson:gson:2.8.6"
}
