package libs.paycom.android

import libs.paycom.base.PaycomResponse

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

data class GetVerifyCodeResponse(val sent: Boolean, val phone: String, val wait: Int) : PaycomResponse
