package p2p.commerce.commerceapi.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;

@RestController
@RequestMapping("/api/seller")
public class SellerController {
    @PreAuthorize("hasAuthority('Seller')")
    @GetMapping
    public CommonResponse<String> response() {
        return ResponseHelper.ok("Seller");
    }
}
