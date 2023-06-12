package laustrup.sophieglimsagerpsykologi.models.dtos.mobilepay;

import com.fasterxml.jackson.annotation.JsonCreator;

import laustrup.services.RandomCreatorService;
import laustrup.sophieglimsagerpsykologi.Defaults;

import lombok.Getter;

/** A DTO object as meant to be the body of a curl for creating a payment for MobilePay. */
public class MobilePayPaymentRequest {

    /**
     * The API key that will determine the MyShop.
     * Will be inserted into the header of a curl of authorization with bearer {}.
     */
    @Getter
    private final String apiKey = Defaults.get_instance().get_mobilePayApiKey();

    /** Will redirect back to the booking url after transaction of MobilePay app is completed. */
    @Getter
    private final String redirectUri = Defaults.get_instance().get_paymentRedirectUri();

    /**
     * A unique id that resembles the current payment.
     * Are generated in constructor.
     */
    @Getter
    private final String reference;

    /** The price of the payment. */
    @Getter
    private final double amount;

    /** A key created by the client. */
    @Getter
    private final String idempotencyKey;

    /** An id that will be used to follow with the transaction. */
    @Getter
    private final String paymentPointId = Defaults.get_instance().get_paymentPointId();

    /** The description that will be printed on the client's MobilePay app, when approving payment. */
    @Getter
    private final String description;

    /**
     * Contains all the values, including reference, to be gathered from db.
     * @param amount The price of the payment.
     * @param reference A unique id that resembles the current payment. Are generated in constructor.
     * @param idempotencyKey A key created by the client.
     * @param description The description that will be printed on the client's MobilePay app, when approving payment.
     */
    public MobilePayPaymentRequest(double amount, String reference, String idempotencyKey, String description) {
        this.amount = amount;
        this.reference = reference;
        this.idempotencyKey = idempotencyKey;
        this.description = description;
    }

    /**
     * With JsonCreator.
     * Is meant to be the received request of a Json.
     * Generates a random reference value with unique characters and a length of 30.
     * @param amount The price of the payment.
     * @param idempotencyKey A key created by the client.
     * @param description The description that will be printed on the client's MobilePay app, when approving payment.
     */
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MobilePayPaymentRequest(double amount, String idempotencyKey, String description) {
        this.amount = amount;
        this.reference = RandomCreatorService.get_instance().generateString(true,30);
        this.idempotencyKey = idempotencyKey;
        this.description = description;
    }
}
