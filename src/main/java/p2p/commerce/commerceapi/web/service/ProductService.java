package p2p.commerce.commerceapi.web.service;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.web.dto.ProductCategoryRequest;
import p2p.commerce.commerceapi.web.dto.ProductRequest;
import p2p.commerce.commerceapi.web.model.ProductCategories;
import p2p.commerce.commerceapi.web.model.Products;

import java.util.List;

public interface ProductService {
    Page<Products> findAllProduct(String name, Integer productCategoryId, int page, int size);

    Products createProduct(ProductRequest productRequest);


    Products updateProduct(int productId, ProductRequest productRequest);
    Products productById(int productId);

    Products deleteProduct(int productId);

    List<Products> recommendProduct();
}
