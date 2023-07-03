package p2p.commerce.commerceapi.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;

@RestController
@RequestMapping("/api/coin")
public class CoinController {

    @Value("${app.coin-price}")
    private int coinPrice;

    @GetMapping("/price")
    public CommonResponse<Integer> priceCoin() {
        return ResponseHelper.ok(coinPrice);
    }
}
