package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.model.ProductTransactions;
import p2p.commerce.commerceapi.web.model.Sellers;
import p2p.commerce.commerceapi.web.model.WalletTransaction;
import p2p.commerce.commerceapi.web.service.ProductTransactionService;
import p2p.commerce.commerceapi.web.service.WalletTransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@AllArgsConstructor
public class TransactionController {
    private ProductTransactionService productTransactionService;

    @PreAuthorize("hasAnyAuthority('Admin', 'Seller')")
    @GetMapping
    public CommonResponse<List<ProductTransactions>> findAll() {
        return ResponseHelper.ok(productTransactionService.findAll());
    }

    @PreAuthorize("hasAnyAuthority('Admin', 'Seller')")
    @GetMapping("/{id}")
    public CommonResponse<ProductTransactions> findById(@PathVariable("id") int id) {
        return ResponseHelper.ok(productTransactionService.findById(id));
    }


//    @PreAuthorize("hasAuthority('Client')")
//    @GetMapping("/active")
//    public CommonResponse<List<ProductTransactions>> findAllIsActive() {
//        return ResponseHelper.ok(productTransactionService.findAllActive());
//    }
}
