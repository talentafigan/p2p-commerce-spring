package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.service.ParamService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ParamController {

    private ParamService paramService;

    @GetMapping("/coin/price")
    public CommonResponse<Integer> priceCoin() {
        return ResponseHelper.ok(paramService.priceCoin());
    }

    @GetMapping("/admin/fees")
    public CommonResponse<Integer> adminFee() {
        return ResponseHelper.ok(paramService.adminFee());
    }

}
