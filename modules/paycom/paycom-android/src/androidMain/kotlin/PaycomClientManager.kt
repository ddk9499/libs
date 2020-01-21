package libs.paycom.android

import android.util.Log
import libs.paycom.base.*
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

class PaycomClientManager(private val options: PaycomClientOptions) : PaycomManager {

    private val client = options.client
    private val TAG = "PaycomClientManager"

    override fun <T : PaycomResponse> executeRequest(
        methodName: String,
        paycomRequest: PaycomRequest,
        returnType: Class<T>
    ): Result<T> {
        val body = gson {
            "method" to methodName
            "params" to gson.toJsonTree(paycomRequest)
        }.toString().toRequestBody(JSON)

        val request = with(Request.Builder()) {
            header("X-Auth", options.merchantId)
            url(getUrl(options.isDemo))
            post(body)
            build()
        }

        val response = try {
            backgroundThread.submit(RequestTask(client, request)).get()
        } catch (e: IOException) {
            loge(e)
            null
        }

        return checkErrorAndReturn(response, returnType)
    }

    private fun <T : PaycomResponse> checkErrorAndReturn(response: Response?, returnType: Class<T>): Result<T> {
        if (response != null) {
            val baseResponse = gson.fromJson<PaycomBaseResponse>(response.body!!.string())
            if (baseResponse.error != null) return Result.failure(DetailedException(baseResponse.error!!.message))
            val result = when (returnType) {
                CardResponse::class.java -> gson.fromJson<CardResponse>(baseResponse.result.get("card"))
                else -> gson.fromJson(baseResponse.result, returnType)
            }

            return Result.success(result as T)
        }

        return Result.failure(NetworkException)
    }

    private fun loge(e: Throwable) {
        if (options.isLoggable) Log.e(TAG, e.message, e)
    }
}
