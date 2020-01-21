package libs.paycom.android

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

enum class PaycomClientMethods(val methodName: String) {
    CARDS_CREATE("cards.create"),
    CARDS_GET_VERIFY_CODE("cards.get_verify_code"),
    CARDS_VERIFY("cards.verify"),
}
