package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.model.Products;
import p2p.commerce.commerceapi.web.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public CommonResponse<Page<Products>> findAllProduct(@RequestParam(name = "productName", defaultValue = "", required = false) String productName, @RequestParam(name = "page", defaultValue = "0", required = false) Integer page, @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        return ResponseHelper.ok(productService.findAllProduct(productName, page, size));
    }

    @GetMapping("/recommendation")
    public CommonResponse<List<Products>> recommendProduct() {
        return ResponseHelper.ok(productService.recommendProduct());
    }
}
