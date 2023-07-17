package p2p.commerce.commerceapi.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class AnalyticsSellerResponse {
    private long transaction;
    private long subTransaction;
    private long salesAmount;
    private long subSalesAmount;
}
