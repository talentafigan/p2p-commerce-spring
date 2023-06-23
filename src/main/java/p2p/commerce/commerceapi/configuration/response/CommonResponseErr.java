package p2p.commerce.commerceapi.configuration.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommonResponseErr<T> {
    @JsonProperty("timestamp")
    private Date timestamp = new Date();
    @JsonProperty("status")
    private int status;
    @JsonProperty("error")
    private T error;
    @JsonProperty("path")
    private Object path;
}
