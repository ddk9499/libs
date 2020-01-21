package libs.paycom.backend

import libs.paycom.base.PaycomOptions
import okhttp3.OkHttpClient

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

data class PaycomBackendOptions(
    override val merchantId: String,
    val merchantPassword: String,
    val client: OkHttpClient,
    override val isLoggable: Boolean = false,
    override val isDemo: Boolean = false
) : PaycomOptions
