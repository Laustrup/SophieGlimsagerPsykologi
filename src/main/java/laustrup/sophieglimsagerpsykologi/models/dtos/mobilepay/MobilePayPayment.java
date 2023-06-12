package laustrup.sophieglimsagerpsykologi.models.dtos.mobilepay;

import lombok.Getter;

import java.time.LocalDateTime;

/** The object of the detail from a payment from MobilePay's API. */
public class MobilePayPayment {

    /**
     * Is used to identify this specific payment,
     * also used for GET of this from MobilePay API.
     */
    @Getter
    private final String paymentId;

    /**
     * A unique id that resembles the current payment.
     * Are generated from the request when payment is being approved.
     */
    @Getter
    private final String reference;

    /** The price of the payment. */
    @Getter
    private final double amount;

    /** The description that will be printed on the client's MobilePay app, when approving payment. */
    @Getter
    private final String description;

    /** An id that will be used to follow with the transaction. */
    @Getter
    private final String paymentPointId;

    /** Will redirect back to the booking url after transaction of MobilePay app is completed. */
    @Getter
    private final String redirectUri;

    /** The state of the payment. */
    @Getter
    private final String state;

    /** The date and time that this payment was initiated. */
    @Getter
    private final LocalDateTime initiatedOn;

    /** The date and time that this payment was updated last time. */
    @Getter
    private final LocalDateTime lastUpdatedOn;

    /** The received id of the merchant from the API. */
    @Getter
    private final String merchantId;

    /** The code of the country the payment is from. */
    @Getter
    private final String isoCurrentCode;

    /** An id that will be used to follow with the transaction. */
    @Getter
    private final String paymentPointName;

    /**
     * For when getting a payment info from the MobilePay API.
     * @param paymentId Is used to identify this specific payment, also used for GET of this from MobilePay API.
     * @param reference A unique id that resembles the current payment. Are generated from the request when payment is being approved.
     * @param amount The price of the payment.
     * @param description The description that will be printed on the client's MobilePay app, when approving payment.
     * @param paymentPointId An id that will be used to follow with the transaction.
     * @param redirectUri Will redirect back to the booking url after transaction of MobilePay app is completed.
     * @param state The state of the payment.
     * @param initiatedOn The date and time that this payment was initiated.
     * @param lastUpdatedOn The date and time that this payment was updated last.
     * @param merchantId The received id of the merchant from the API.
     * @param isoCurrentCode The code of the country the payment is from.
     * @param paymentPointName An id that will be used to follow with the transaction.
     */
    public MobilePayPayment(String paymentId, String reference, double amount, String description, String paymentPointId,
                            String redirectUri, String state, LocalDateTime initiatedOn, LocalDateTime lastUpdatedOn,
                            String merchantId, String isoCurrentCode, String paymentPointName) {
        this.paymentId = paymentId;
        this.reference = reference;
        this.amount = amount;
        this.description = description;
        this.paymentPointId = paymentPointId;
        this.redirectUri = redirectUri;
        this.state = state;
        this.initiatedOn = initiatedOn;
        this.lastUpdatedOn = lastUpdatedOn;
        this.merchantId = merchantId;
        this.isoCurrentCode = isoCurrentCode;
        this.paymentPointName = paymentPointName;
    }
}
