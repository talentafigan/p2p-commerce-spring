package p2p.commerce.commerceapi.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AnalyticsAdminResponse {
    private long consultation;
    private double subConsultationPercent;
    private long transaction;
    private long subTransaction;
    private long totalStudent;
    private long subTotalStudent;
    private long totalMentor;
    private long subTotalMentor;
}
