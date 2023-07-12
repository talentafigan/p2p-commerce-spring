package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.model.ProductCategories;
import p2p.commerce.commerceapi.web.repository.ProductCategoryRepository;
import p2p.commerce.commerceapi.web.service.ProductCategoryService;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private ProductCategoryRepository productCategoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProductCategories> findAll() {
        return productCategoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public ProductCategories findById(int productCategoryId) {
        return productCategoryRepository.findById(productCategoryId).orElseThrow(() -> new BussinesException("Product Category NOT FOUND"));
    }
}
