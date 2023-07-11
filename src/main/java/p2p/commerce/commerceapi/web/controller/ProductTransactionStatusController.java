package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.model.ProductTransactionStatus;
import p2p.commerce.commerceapi.web.service.ProductTransactionStatusService;

import java.util.List;

@RestController
@RequestMapping("/api/product-transaction-status")
@AllArgsConstructor
public class ProductTransactionStatusController {
    private ProductTransactionStatusService productTransactionStatusService;

    @GetMapping
    public CommonResponse<List<ProductTransactionStatus>> findAll() {
        return ResponseHelper.ok(productTransactionStatusService.findAll());
    }

    @GetMapping("/{id}")
    public CommonResponse<ProductTransactionStatus> findById(@PathVariable("id") int id) {
        return ResponseHelper.ok(productTransactionStatusService.findById(id));
    }
}
