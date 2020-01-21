package libs.paycom.backend

import com.google.gson.Gson
import com.google.gson.JsonElement
import libs.paycom.base.*
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.util.logging.Logger

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

class PaycomBackendManager(private val options: PaycomBackendOptions) : PaycomManager {

    private val gson by lazy { Gson() }
    private val client = options.client
    private val logger = Logger.getLogger(this::class.java.simpleName)
    private val url by lazy {
        if (options.isDemo) "https://checkout.test.paycom.uz/api"
        else "https://checkout.paycom.uz/api"
    }

    override fun <T : PaycomResponse> executeRequest(
        methodName: String,
        paycomRequest: PaycomRequest,
        returnType: Class<T>
    ): Result<T> {
        val body = gson {
            "method" to methodName
            "params" to libs.paycom.base.gson.toJsonTree(paycomRequest)
        }.toString().toRequestBody(JSON)

        val request = with(Request.Builder()) {
            header("X-Auth", "${options.merchantId}:${options.merchantPassword}")
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
                CreateTxResponse::class.java -> gson.fromJson<CreateTxResponse>(baseResponse.result.get("receipt"))
                PayTxResponse::class.java -> checkPaidStatus(baseResponse.result.get("receipt"))
                else -> gson.fromJson(baseResponse.result, returnType)
            }

            return Result.success(result as T)
        }

        return Result.failure(NetworkException)
    }

    private fun checkPaidStatus(element: JsonElement): PayTxResponse {
        val state = element.asJsonObject.get("state").asInt
        return PayTxResponse(state == 4)
    }

    private fun loge(e: Throwable) {
        if (options.isLoggable) logger.severe(e.message)
    }
}
