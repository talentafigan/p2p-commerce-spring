package p2p.commerce.commerceapi.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.ProductRequest;
import p2p.commerce.commerceapi.web.model.Prod;
import p2p.commerce.commerceapi.web.model.Products;
import p2p.commerce.commerceapi.web.repository.ProdRepository;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProdController {
    private ProdRepository prodRepository;
    @GetMapping
    public CommonResponse<List<Prod>> findAllProduct() {
        return ResponseHelper.ok(prodRepository.findAll());
    }

    @GetMapping("/{productId}")
    public CommonResponse<Prod> getProductDetail(@PathVariable("productId") int productId) {
        return ResponseHelper.ok(prodRepository.findById(productId).get());
    }


    @PostMapping
    public CommonResponse<Prod> createProduct(@RequestBody Prod productRequest) {
        return ResponseHelper.ok(prodRepository.save(productRequest));
    }

    @PreAuthorize("hasAuthority('Seller')")
    @PutMapping("/{productId}")
    public CommonResponse<Prod> updateProduct(@PathVariable("productId") int productId, @RequestBody Prod productRequest) {
        Prod prod = prodRepository.findById(productId).get();
        prod.setImage(productRequest.getImage());
        prod.setProductPrice(productRequest.getProductPrice());
        prod.setRating(productRequest.getRating());
        prod.setProductName(productRequest.getProductName());
        prod.setProductDescription(productRequest.getProductDescription());
        prod.setProductCategory(productRequest.getProductCategory());
        return ResponseHelper.ok(prodRepository.save(prod));
    }

    @PreAuthorize("hasAuthority('Seller')")
    @DeleteMapping("/{productId}")
    public CommonResponse<String> deleteProduct(@PathVariable("productId") int productId) {
        prodRepository.deleteById(productId);
        return ResponseHelper.ok("Success");
    }
}
