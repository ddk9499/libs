package libs.paycom.base

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

abstract class PaycomException(override val message: String? = null) : Throwable(message)

object NetworkException : PaycomException()
class DetailedException(message: String) : PaycomException(message)
