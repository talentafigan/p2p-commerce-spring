package p2p.commerce.commerceapi.configuration.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    @JsonProperty("status")
    private String status;

    @JsonProperty("statusCode")
    private int statusCode;

    @JsonProperty("data")
    private T data;
}
