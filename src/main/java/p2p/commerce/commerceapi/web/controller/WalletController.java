package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.TopUpResponse;
import p2p.commerce.commerceapi.web.dto.TopupRequest;
import p2p.commerce.commerceapi.web.dto.WalletResponse;
import p2p.commerce.commerceapi.web.model.WalletTransaction;
import p2p.commerce.commerceapi.web.service.WalletService;
import p2p.commerce.commerceapi.web.service.WalletTransactionService;

import java.util.Date;
import java.util.List;

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
    public CommonResponse<List<WalletTransaction>> findTransaction(@RequestParam(name = "date") String date) {
        return ResponseHelper.ok(walletTransactionService.getAll(date));
    }

    @PreAuthorize("hasAuthority('Client')")
    @PostMapping("/top-up")
    public CommonResponse<TopUpResponse> testCreateInvoice(@RequestBody TopupRequest topupRequest) {
        return ResponseHelper.ok(walletService.topUp(topupRequest));
    }
}
