package libs.paycom.base

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import java.util.concurrent.Executors

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

val backgroundThread = Executors.newFixedThreadPool(1)

val gson: Gson = Gson()

val JSON: MediaType = "application/json; charset=utf-8".toMediaType()

fun gson(builder: GsonObjectBuilder.() -> Unit): JsonObject = GsonObjectBuilder.build(builder)

fun PaycomManager.getUrl(isDemo: Boolean): String =
    if (isDemo) "https://checkout.test.paycom.uz/api"
    else "https://checkout.paycom.uz/api"

inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, T::class.java)
inline fun <reified T> Gson.fromJson(json: JsonElement): T = fromJson(json, T::class.java)

object GsonObjectBuilder {
    private val jsonObject = JsonObject()

    fun build(builder: GsonObjectBuilder.() -> Unit): JsonObject {
        this.builder()
        return jsonObject
    }

    infix fun <T : JsonElement> String.to(value: T) {
        jsonObject.add(this, value)
    }

    infix fun String.to(value: String) {
        jsonObject.add(this, JsonPrimitive(value))
    }
}
