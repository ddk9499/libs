package libs.paycomsdk.entities

/**
 * Created at December 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

internal enum class Methods(val methodName: String) {
    CARDS_CREATE("cards.create"),
    CARDS_GET_VERIFY_CODE("cards.get_verify_code"),
    CARDS_VERIFY("cards.verify"),

    RECEIPTS_CREATE("receipts.create"),
    RECEIPTS_PAY("receipts.pay")
}
