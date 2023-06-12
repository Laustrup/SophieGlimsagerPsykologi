package laustrup.sophieglimsagerpsykologi.models.dtos.mobilepay.payments;

import com.fasterxml.jackson.annotation.JsonCreator;

import laustrup.sophieglimsagerpsykologi.Defaults;

import lombok.Getter;

import java.util.InputMismatchException;

/** The response from the MobilePay API, that will be received when a payment has been sent as a curl. */
public class MobilePayPaymentResponse {

    /**
     * Is used to identify this specific payment,
     * also used for GET of this from MobilePay API.
     */
    @Getter
    private final String paymentId;

    /** Will redirect back to the booking url after transaction of MobilePay app is completed. */
    @Getter
    private final String mobilePayAppRedirectUri;

    /**
     * With all values.
     * Will be used for the response of the MobilePay API when creating a payment.
     * @param paymentId Is used to identify this specific payment, also used for GET of this from MobilePay API.
     * @param mobilePayAppRedirectUri Will redirect back to the booking url after transaction of MobilePay app is completed.
     * @throws InputMismatchException If the mobilePayAppRedirectUri doesn't match the default redirect value.
     */
    public MobilePayPaymentResponse(String paymentId, String mobilePayAppRedirectUri) throws InputMismatchException {
        if (!mobilePayAppRedirectUri.equals(Defaults.get_instance().get_paymentRedirectUri()))
            throw new InputMismatchException();

        this.paymentId = paymentId;
        this.mobilePayAppRedirectUri = mobilePayAppRedirectUri;
    }

    /**
     * With JsonCreator.
     * Is meant to be the received request of a Json.
     * redirectUri is gathered from Defaults.
     * @param paymentId Is used to identify this specific payment, also used for GET of this from MobilePay API.
     */
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MobilePayPaymentResponse(String paymentId) {
        this.paymentId = paymentId;
        this.mobilePayAppRedirectUri = Defaults._instance.get_paymentRedirectUri();
    }
}
