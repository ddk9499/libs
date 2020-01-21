package libs.paycom.backend

import libs.paycom.base.PaycomRequest

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

data class CheckTokenRequest(val token: String) : PaycomRequest

data class CreateTxRequest(val amount: Int, val account: Map<String, Any> = emptyMap()) : PaycomRequest

data class PayTxRequest(val id: String, val token: String) : PaycomRequest
