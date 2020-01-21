package libs.paycom.android

import libs.paycom.base.PaycomRequest

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

data class UzCard(val number: String, val expire: String)

data class CreateCardRequest(
    val card: UzCard,
    val amount: Int,
    val save: Boolean = false
) : PaycomRequest

data class GetVerifyCodeRequest(val token: String) : PaycomRequest

data class VerifyRequest(val token: String, val code: String) : PaycomRequest
