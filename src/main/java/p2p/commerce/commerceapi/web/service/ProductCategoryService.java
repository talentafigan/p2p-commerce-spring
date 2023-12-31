package p2p.commerce.commerceapi.web.service;

import p2p.commerce.commerceapi.web.model.ProductCategories;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategories> findAll(Integer parentId);
    ProductCategories findById(int productCategoryId);
}
