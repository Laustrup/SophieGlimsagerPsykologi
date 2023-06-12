package laustrup.sophieglimsagerpsykologi.services;

import laustrup.sophieglimsagerpsykologi.Defaults;
import laustrup.sophieglimsagerpsykologi.models.dtos.mobilepay.MobilePayPayment;
import laustrup.sophieglimsagerpsykologi.models.dtos.mobilepay.MobilePayPaymentRequest;
import laustrup.sophieglimsagerpsykologi.models.dtos.mobilepay.MobilePayPaymentResponse;
import laustrup.utilities.console.Printer;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * For sending requests and receiving responses from APIs.
 * Some functions are specific for a purpose of a specific Object.
 */
public class Curler {

    /**
     * Sends a request to an API with an uri.
     * Will catch IOException.
     * @param method The mapping method used for the API URI.
     * @param uri The URI location for the API.
     * @return An InputStream, that contains the response.
     */
    public static InputStream curl(Mapping method, String uri) {
        try {
            return Runtime.getRuntime().exec(
                    "curl " + uri + " -X " + method.name() +
                            " -H 'Content-Type: application/json' "
            ).getInputStream();
        } catch (IOException e) {
            Printer.get_instance().print("Couldn't curl " + uri + " with " + method + "...",e);
            return null;
        }
    }

    /**
     * Sends a request to an API with an uri.
     * Will catch IOException.
     * @param method The mapping method used for the API URI.
     * @param uri The URI location for the API.
     * @param headers The plain values for the header, both '' and -H are inserted in the algorithm.
     * @return An InputStream, that contains the response.
     */
    public static InputStream curl(Mapping method, String uri, String[] headers) {
        try {
            StringBuilder header = new StringBuilder();

            for (String head : headers)
                header.append(" -H '").append(head).append("' ");

            return Runtime.getRuntime().exec("curl " + uri + " -X " + method.name() + header).getInputStream();
        } catch (IOException e) {
            Printer.get_instance().print("Couldn't curl " + uri + " with " + method + "...",e);
            return null;
        }
    }

    /**
     * For creating a payment at MobilePay.
     * Will catch IO- or ClassNotFoundExceptions.
     * @param mobilePay The Request object of MobilePay for creating a payment.
     * @return The response of the curl as a custom MobilePayPaymentResponse Object that is cast from .readObject().
     */
    public static MobilePayPaymentResponse curl(MobilePayPaymentRequest mobilePay) {
        try {
            return (MobilePayPaymentResponse) new ObjectInputStream(Runtime.getRuntime().exec(
                    "curl https://api.mobilepay.dk/v1/payments -X POST " +
                            "-H 'Content-Type: " + ContentType.JSON.get_content() + "' " +
                            "-H 'Authorization: Bearer {" + mobilePay.getApiKey() + "}' " +
                            "-d '{" +
                                "\"amount\": " + mobilePay.getAmount() + "," +
                                "\"idempotencyKey\": \"" + mobilePay.getIdempotencyKey() + "\"," +
                                "\"paymentPointId\": \"" + mobilePay.getPaymentPointId() + "\"," +
                                "\"redirectUri\": \"" + mobilePay.getRedirectUri() + "\"" +
                                "\"reference\": \"" + mobilePay.getReference() + "\"," +
                                "\"description\": \"" + mobilePay.getDescription() + "\"" +
                            "}' "
            ).getInputStream()).readObject();
        } catch (IOException e) {
            Printer.get_instance().print("Couldn't make a MobilePay Payment curl...",e);
        } catch (ClassNotFoundException e) {
            Printer.get_instance().print("Couldn't read response of MobilePay Payment curl...",e);
        }

        return null;
    }

    /**
     * Will send a request as a curl in order to get a payment from MobilePay.
     * @param paymentId Is used to identify this specific payment, also used for GET of this from MobilePay API.
     * @return The response of the curl as a custom MobilePayPayment Object that is cast from .readObject().
     */
    public static MobilePayPayment getMobilePay(String paymentId) {
        try {
            return (MobilePayPayment) new ObjectInputStream(Runtime.getRuntime().exec(
                    "curl https://api.mobilepay.dk/v1/payments/" + paymentId +
                            " -X " + Mapping.GET +
                            " -H '" + Defaults.get_instance().get_mobilePayApiKey() + "'"
            ).getInputStream()).readObject();
        } catch (IOException e) {
            Printer.get_instance().print("Couldn't get MobilePayPayment from curl...",e);
        } catch (ClassNotFoundException e) {
            Printer.get_instance().print("Couldn't read response of getting MobilePay Payment curl...",e);
        }

        return null;
    }

    /**
     * The Mapping methods that are available.
     * Is created to prevent misspellings and keep the same procedure.
     */
    public enum Mapping {
        POST,GET,DELETE,PATCH,PUT
    }

    /**
     * The ContentTypes that are available.
     * The _content is the value to insert into the header.
     * Is created to prevent misspellings and keep the same procedure.
     */
    public enum ContentType {
        JSON("application/json");

        @Getter
        private String _content;
        ContentType(String content) { _content = content; }
    }
}
