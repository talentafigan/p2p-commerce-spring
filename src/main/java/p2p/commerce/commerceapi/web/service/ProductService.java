package p2p.commerce.commerceapi.web.service;

import org.springframework.data.domain.Page;
import p2p.commerce.commerceapi.web.model.Products;

import java.util.List;

public interface ProductService {
    Page<Products> findAllProduct(String name, int page, int size);

    List<Products> recommendProduct();
}
