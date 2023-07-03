package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.WalletResponse;
import p2p.commerce.commerceapi.web.model.WalletTransaction;
import p2p.commerce.commerceapi.web.service.WalletService;
import p2p.commerce.commerceapi.web.service.WalletTransactionService;

@RestController
@RequestMapping("/api/wallet")
@AllArgsConstructor
public class WalletController {
    private WalletService walletService;
    private WalletTransactionService walletTransactionService;

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping("/balance")
    public CommonResponse<WalletResponse> getBalance() {
        return ResponseHelper.ok(walletService.getWallet());
    }

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping("/history")
    public CommonResponse<Page<WalletTransaction>> findTransaction(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseHelper.ok(walletTransactionService.getAll(page, size));
    }
}
