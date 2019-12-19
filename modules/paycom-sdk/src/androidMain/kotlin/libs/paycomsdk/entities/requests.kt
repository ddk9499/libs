package libs.paycomsdk.entities

import org.json.JSONObject

/**
 * Created at December 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

sealed class PaycomRequests

data class UzCard(val number: String, val expire: String)

data class CreateCardRequest(
    val card: UzCard,
    val amount: Int,
    val save: Boolean = false
) : PaycomRequests()

data class GetVerifyCodeRequest(val token: String) : PaycomRequests()

data class VerifyRequest(val token: String, val code: String) : PaycomRequests()

data class CreateTxAndPayRequest(val amount: Int, val token: String) : PaycomRequests()

internal data class CreateTxRequest(val amount: Int, val account: JSONObject = JSONObject()) : PaycomRequests()
internal data class PayTxRequest(val id: String, val token: String) : PaycomRequests()
