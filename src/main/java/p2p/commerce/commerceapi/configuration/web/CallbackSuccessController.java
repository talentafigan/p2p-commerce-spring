package p2p.commerce.commerceapi.configuration.web;

import com.xendit.model.Invoice;
import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.CallBackDtoRequest;

@RestController
@RequestMapping("/api/payment/callback")
public class CallbackSuccessController {
    @PostMapping
    public CommonResponse<String> callbackPost(@RequestBody CallBackDtoRequest callBackDtoRequest) {
        // call back logice
        return ResponseHelper.ok("SUCCESS");
    }

    @PutMapping
    public CommonResponse<Object> callbackPut(@RequestBody Object obj) {
        return ResponseHelper.ok(obj);
    }
}
