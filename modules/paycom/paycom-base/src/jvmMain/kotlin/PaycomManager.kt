package libs.paycom.base

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

interface PaycomManager {

    fun <T : PaycomResponse> executeRequest(
        methodName: String,
        paycomRequest: PaycomRequest,
        returnType: Class<T>
    ): Result<T>

}
