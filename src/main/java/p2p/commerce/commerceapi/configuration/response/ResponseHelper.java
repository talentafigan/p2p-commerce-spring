package p2p.commerce.commerceapi.configuration.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class ResponseHelper {
    public static <T> CommonResponse<T> ok(T data) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setStatus("SUCCESS");
        response.setStatusCode(HttpStatus.OK.value());
        response.setData(data);
        return response;
    }
    public static CommonResponse<Object> ok() {
        return null;
    }
    public static <T> ResponseEntity<CommonResponseErr<T>> err(T message, HttpStatus http, HttpServletRequest request) {
        CommonResponseErr<T> response = new CommonResponseErr<>();
        response.setStatusCode(http.value());
        response.setStatus("FAILED");
        response.setMessage(message);
        response.setPath(request.getRequestURI());
        return new ResponseEntity<>(response, http);
    }
}
