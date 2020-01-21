package libs.paycom.backend

/**
 * Created at January 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

enum class PaycomBackendMethods(val methodName: String) {
    CARDS_CHECK("cards.check"),
    RECEIPTS_CREATE("receipts.create"),
    RECEIPTS_PAY("receipts.pay")
}
