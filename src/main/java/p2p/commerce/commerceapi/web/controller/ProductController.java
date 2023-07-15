package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.ProductCategoryRequest;
import p2p.commerce.commerceapi.web.dto.ProductRequest;
import p2p.commerce.commerceapi.web.model.ProductCategories;
import p2p.commerce.commerceapi.web.model.Products;
import p2p.commerce.commerceapi.web.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public CommonResponse<Page<Products>> findAllProduct(@RequestParam(name = "searchKey", defaultValue = "", required = false) String searchKey, @RequestParam(name = "productCategoryId", defaultValue = "", required = false) Integer productCategoryId, @RequestParam(name = "page", defaultValue = "0", required = false) Integer page, @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        return ResponseHelper.ok(productService.findAllProduct(searchKey, productCategoryId, page, size));
    }

    @GetMapping("/{productId}")
    public CommonResponse<Products> getProductDetail(@PathVariable("productId") int productId) {
        return ResponseHelper.ok(productService.productById(productId));
    }

    @GetMapping("/recommendation")
    public CommonResponse<List<Products>> recommendProduct() {
        return ResponseHelper.ok(productService.recommendProduct());
    }
    @PreAuthorize("hasAuthority('Seller')")
    @PostMapping
    public CommonResponse<Products> createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseHelper.ok(productService.createProduct(productRequest));
    }

    @PreAuthorize("hasAuthority('Seller')")
    @PutMapping("/{productId}")
    public CommonResponse<Products> updateProduct(@PathVariable("productId") int productId, @RequestBody ProductRequest productRequest) {
        return ResponseHelper.ok(productService.updateProduct(productId, productRequest));
    }

    @PreAuthorize("hasAuthority('Seller')")
    @DeleteMapping("/{productId}")
    public CommonResponse<Products> deleteProduct(@PathVariable("productId") int productId) {
        return ResponseHelper.ok(productService.deleteProduct(productId));
    }



}
