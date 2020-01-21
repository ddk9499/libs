package libs.paycom.base

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.Callable

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

class RequestTask(
    private val client: OkHttpClient,
    private val request: Request
) : Callable<Response> {

    override fun call(): Response {
        return client.newCall(request).execute()
    }
}
