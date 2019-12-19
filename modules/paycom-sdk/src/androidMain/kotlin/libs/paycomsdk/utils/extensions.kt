package libs.paycomsdk.utils

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject

/**
 * Created at December 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

internal fun gson(builder: GsonObjectBuilder.() -> Unit): JsonObject = GsonObjectBuilder.build(builder)

internal inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, T::class.java)
internal inline fun <reified T> Gson.fromJson(json: JsonElement): T = fromJson(json, T::class.java)
