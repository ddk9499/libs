package libs.paycomsdk.entities

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

/**
 * Created at December 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

internal class PaycomBaseResponse(
    val id: Int? = null,
    val error: Error? = null,
    val result: JsonObject = JsonObject()
)

internal data class Error(val message: String = "", val code: Int = Int.MIN_VALUE, val data: String = "")

sealed class PaycomResponse

data class CardResponse(
    @SerializedName("number") val cardNumber: String,
    @SerializedName("expire") val cardExpire: String,
    val token: String,
    val recurrent: Boolean,
    val verify: Boolean
) : PaycomResponse()

data class GetVerifyCodeResponse(val sent: Boolean, val phone: String, val wait: Int) : PaycomResponse()

data class TxResponse(val isPaid: Boolean) : PaycomResponse()

internal data class CreateTxResponse(@SerializedName("_id") val id: String) : PaycomResponse()
