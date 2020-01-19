package libs.paycomsdk

import libs.paycomsdk.entities.*

/**
 * Created at December 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

interface RequestManager {

    fun createCard(request: CreateCardRequest): Result<CardResponse>
    fun getVerifyCode(request: GetVerifyCodeRequest): Result<GetVerifyCodeResponse>
    fun verify(request: VerifyRequest): Result<CardResponse>

    companion object {
        operator fun invoke(builder: RequestManagerBuilder.() -> Unit): RequestManager =
            RequestManagerBuilder().apply(builder).build()
    }
}
