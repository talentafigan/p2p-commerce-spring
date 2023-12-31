package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.model.ProductCategories;
import p2p.commerce.commerceapi.web.service.ProductCategoryService;

import java.util.List;


@RestController
@RequestMapping("/api/product-category")
@AllArgsConstructor
public class ProductCategoryController {
    private ProductCategoryService productCategoryService;

    @GetMapping
    public CommonResponse<List<ProductCategories>> findAll(@RequestParam(name = "parentId", required = false) Integer parentId) {
        return ResponseHelper.ok(productCategoryService.findAll(parentId));
    }

    @GetMapping("/{productCategoryId}")
    public CommonResponse<ProductCategories> findById(@PathVariable("productCategoryId") int productCategoryId) {
        return ResponseHelper.ok(productCategoryService.findById(productCategoryId));
    }
}
