package libs.paycom.backend

import com.google.gson.annotations.SerializedName
import libs.paycom.base.PaycomResponse

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

data class CreateTxResponse(@SerializedName("_id") val id: String) : PaycomResponse

data class PayTxResponse(val isPaid: Boolean) : PaycomResponse
