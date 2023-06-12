package laustrup.sophieglimsagerpsykologi.models.dtos.mobilepay.refunds;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;

import java.time.LocalDateTime;

public class MobilePayRefundResponse {

    @Getter
    private final String refundId;

    @Getter
    private final String paymentId;

    @Getter
    private final double amount;

    @Getter
    private final String description;

    @Getter
    private final String reference;

    @Getter
    private final LocalDateTime createdOn;

    @Getter
    private final int remainingAmount;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MobilePayRefundResponse(String refundId, String paymentId, double amount, String description, String reference, LocalDateTime createdOn, int remainingAmount) {
        this.refundId = refundId;
        this.paymentId = paymentId;
        this.amount = amount;
        this.description = description;
        this.reference = reference;
        this.createdOn = createdOn;
        this.remainingAmount = remainingAmount;
    }


}
