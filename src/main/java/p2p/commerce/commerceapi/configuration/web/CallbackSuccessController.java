package p2p.commerce.commerceapi.configuration.web;

import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;

@RestController
@RequestMapping("/api/callback")
public class CallbackSuccessController {
    @PostMapping
    public CommonResponse<Object> callbackPost(@RequestBody Object obj) {
        System.out.println("Post");
        return ResponseHelper.ok(obj);
    }

    @PutMapping
    public CommonResponse<Object> callbackPut(@RequestBody Object obj) {
        System.out.println("Put");
        return ResponseHelper.ok(obj);
    }
}
