package libs.paycomsdk.exceptions

/**
 * Created at December 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

sealed class PaymentExceptions(override val message: String? = null) : Throwable(message)

object NetworkException : PaymentExceptions()
class DetailedException(message: String) : PaymentExceptions(message)
