package laustrup.sophieglimsagerpsykologi.models.dtos.mobilepay.refunds;

import lombok.Getter;

public class MobilePayRefundRequest {

    @Getter
    private final String idempotencyKey;

    @Getter
    private final String paymentId;

    @Getter
    private final double amount;

    @Getter
    private final String reference;

    @Getter
    private final String description;

    public MobilePayRefundRequest(String idempotencyKey, String paymentId, double amount, String reference, String description) {
        this.idempotencyKey = idempotencyKey;
        this.paymentId = paymentId;
        this.amount = amount;
        this.reference = reference;
        this.description = description;
    }
}
