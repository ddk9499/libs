package libs.paycomsdk.utils

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive

/**
 * Created at December 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

internal object GsonObjectBuilder {
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
