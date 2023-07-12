package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.ProductTransactionProofRequest;
import p2p.commerce.commerceapi.web.dto.ProductTransactionRequest;
import p2p.commerce.commerceapi.web.dto.ProductTransactionStatusRequest;
import p2p.commerce.commerceapi.web.model.ProductTransactions;
import p2p.commerce.commerceapi.web.service.ProductTransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/product-transaction")
@AllArgsConstructor
public class ProductTransactionController {
    private ProductTransactionService productTransactionService;

    @GetMapping
    public CommonResponse<List<ProductTransactions>> findAll(@RequestParam(name = "productTransactionStatusId", required = false) Integer productTransactionStatusId) {
        return ResponseHelper.ok(productTransactionService.findAll(productTransactionStatusId));
    }

    @GetMapping("/{productTransactionId}")
    public CommonResponse<ProductTransactions> findById(@PathVariable("productTransactionId") int id) {
        return ResponseHelper.ok(productTransactionService.findById(id));
    }

    @PreAuthorize("hasAuthority('Client')")
    @PostMapping
    public CommonResponse<ProductTransactions> create(@RequestBody ProductTransactionRequest productTransactionRequest) {
        return ResponseHelper.ok(productTransactionService.createTransaction(productTransactionRequest));
    }

    @PreAuthorize("hasAuthority('Client')")
    @PutMapping("/proof/{productTransactionId}")
    public CommonResponse<ProductTransactions> proofTransaction(@PathVariable("productTransactionId") int productTransactionId, @RequestBody ProductTransactionProofRequest productTransactionProofRequest) {
        return ResponseHelper.ok(productTransactionService.proofTransaction(productTransactionId, productTransactionProofRequest));
    }

    @PreAuthorize("hasAnyAuthority('Client', 'Seller')")
    @PutMapping("/{productTransactionId}")
    public CommonResponse<ProductTransactions> changeStatus(@PathVariable("productTransactionId") int productTransactionId, @RequestBody ProductTransactionStatusRequest productTransactionStatusRequest) {
        return ResponseHelper.ok(productTransactionService.setStatusTransaction(productTransactionId, productTransactionStatusRequest));
    }

    @PreAuthorize("hasAnyAuthority('Client', 'Seller')")
    @DeleteMapping("/{productTransactionId}")
    public CommonResponse<ProductTransactions> deleteTransaction(@PathVariable("productTransactionId") int productTransactionId) {
        return ResponseHelper.ok(productTransactionService.deleteTransaction(productTransactionId));
    }


}
