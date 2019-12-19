package libs.paycomsdk

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created at December 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

data class DefaultRequestOptions(
    val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    val merchantId: String = "",
    val merchantPassword: String = "",
    val isSandBoxMode: Boolean? = null,
    val isLoggable: Boolean = isSandBoxMode ?: false
)
