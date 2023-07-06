package p2p.commerce.commerceapi.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CallBackDtoRequest {
    private String id;
    private String external_id;
    private String user_id;
    private Boolean is_high;
    private String payment_method;
    private String status;
    private String merchant_name;
    private Integer amount;
    private Integer paid_amount;
    private String bank_code;
    private Date paid_at;
    private String payer_email;
    private String description;
    private Integer adjusted_received_amount;
    private Integer fees_paid_amount;
    private Date updated;
    private Date created;
    private String currency;
    private String payment_channel;
    private String payment_destination;
}
