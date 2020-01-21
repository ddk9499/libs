package libs.paycom.base

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

interface PaycomRequest

interface PaycomResponse

data class CardResponse(
    @SerializedName("number") val cardNumber: String,
    @SerializedName("expire") val cardExpire: String,
    val token: String,
    val recurrent: Boolean,
    val verify: Boolean
) : PaycomResponse

data class PaycomBaseResponse(
    val id: Int? = null,
    val error: PaycomError? = null,
    val result: JsonObject = JsonObject()
)

data class PaycomError(val message: String = "", val code: Int = Int.MIN_VALUE, val data: String = "")
