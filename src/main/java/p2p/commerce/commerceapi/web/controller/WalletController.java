package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.WalletResponse;
import p2p.commerce.commerceapi.web.service.WalletService;

@RestController
@RequestMapping("/api/wallet")
@AllArgsConstructor
public class WalletController {
    private WalletService walletService;

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping("/balance")
    public CommonResponse<WalletResponse> getBalance() {
        return ResponseHelper.ok(walletService.getWallet());
    }
}
