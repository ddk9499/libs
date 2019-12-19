package libs.paycomsdk

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonElement
import libs.paycomsdk.entities.*
import libs.paycomsdk.exceptions.DetailedException
import libs.paycomsdk.exceptions.NetworkException
import libs.paycomsdk.utils.fromJson
import libs.paycomsdk.utils.gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

/**
 * Created at December 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

internal class RealRequestManager(
    private val defaults: DefaultRequestOptions,
    private val client: OkHttpClient
) : RequestManager {

    companion object {
        private const val TAG = "RealRequestManager"
        private val JSON = "application/json; charset=utf-8".toMediaType()
    }

    private val gson = Gson()
    private val isDemo = defaults.isSandBoxMode ?: true
    private val url = if (isDemo) "https://checkout.test.paycom.uz/api" else "https://checkout.paycom.uz/api"

    override fun createCard(request: CreateCardRequest): Result<CardResponse> {
        val response = callApiMethod(Methods.CARDS_CREATE, request)
        return checkErrorAndReturn(response)
    }

    override fun getVerifyCode(request: GetVerifyCodeRequest): Result<GetVerifyCodeResponse> {
        val response = callApiMethod(Methods.CARDS_GET_VERIFY_CODE, request)
        return checkErrorAndReturn(response)
    }

    override fun verify(request: VerifyRequest): Result<CardResponse> {
        val response = callApiMethod(Methods.CARDS_VERIFY, request)
        return checkErrorAndReturn(response)
    }

    override fun createAndPayTx(request: CreateTxAndPayRequest): Result<TxResponse> {
        val createTxResponse = callApiMethod(Methods.RECEIPTS_CREATE, CreateTxRequest(request.amount), true)
        val createTxResult = checkErrorAndReturn<CreateTxResponse>(createTxResponse)
        if (createTxResult.isFailure) {
            return Result.failure(createTxResult.exceptionOrNull()!!)
        }

        val txId = createTxResult.getOrNull()!!.id
        val response = callApiMethod(Methods.RECEIPTS_PAY, PayTxRequest(txId, request.token))
        return checkErrorAndReturn(response)
    }

    private fun callApiMethod(method: Methods, requestParams: PaycomRequests, isMerchantCall: Boolean = false): Response? {
        val params = when (requestParams) {
            is CreateCardRequest -> requestParams.copy(amount = requestParams.amount * 100)
            is CreateTxRequest -> requestParams.copy(amount = requestParams.amount * 100)
            else -> requestParams
        }
        val body = gson {
            "method" to method.methodName
            "params" to gson.toJsonTree(params)
        }.toString().toRequestBody(JSON)
        val request = with(Request.Builder()) {
            if (isMerchantCall.not()) header("X-Auth", defaults.merchantId)
            else header("X-Auth", "${defaults.merchantId}:${defaults.merchantPassword}")
            url(url)
            post(body)
            build()
        }

        return try {
            client.newCall(request).execute()
        } catch (e: IOException) {
            loge(e)
            null
        }
    }

    private inline fun <reified T : PaycomResponse> checkErrorAndReturn(response: Response?): Result<T> {
        if (response != null) {
            val baseResponse = gson.fromJson<PaycomBaseResponse>(response.body!!.string())

            if (baseResponse.error != null) return Result.failure(DetailedException(baseResponse.error.message))
            val result = when (T::class) {
                CardResponse::class -> gson.fromJson<CardResponse>(baseResponse.result.get("card"))
                CreateTxResponse::class -> gson.fromJson<CreateTxResponse>(baseResponse.result.get("receipt"))
                TxResponse::class -> checkPaidStatus(baseResponse.result.get("receipt"))
                else -> gson.fromJson<T>(baseResponse.result)
            }

            return Result.success(result as T)
        }

        return Result.failure(NetworkException)
    }

    private fun checkPaidStatus(element: JsonElement): TxResponse {
        val state = element.asJsonObject.get("state").asInt
        return TxResponse(state == 4)
    }

    private fun loge(e: Throwable) {
        if (defaults.isLoggable) Log.e(TAG, e.message, e)
    }
}
