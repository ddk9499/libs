package libs.paycomsdk.entities

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
