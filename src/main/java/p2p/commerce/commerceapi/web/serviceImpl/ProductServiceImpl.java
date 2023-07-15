package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.dto.ProductRequest;
import p2p.commerce.commerceapi.web.model.*;
import p2p.commerce.commerceapi.web.repository.ProductCategoryRepository;
import p2p.commerce.commerceapi.web.repository.ProductRepository;
import p2p.commerce.commerceapi.web.repository.SellesRepository;
import p2p.commerce.commerceapi.web.repository.StatusRepository;
import p2p.commerce.commerceapi.web.service.ProductService;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private StatusRepository statusRepository;
    private AuthenticationFacade authenticationFacade;
    private SellesRepository sellesRepository;
    private ProductCategoryRepository productCategoryRepository;
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public Page<Products> findAllProduct(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Status status = statusRepository.findById(1).get();
        return productRepository.findAllByProductNameContainingIgnoreCaseAndStatusAndDeleteDateIsNull(name, status, pageable);
    }

    @Transactional
    @Override
    public Products createProduct(ProductRequest productRequest) {
        Users user= authenticationFacade.getAuthentication();
        Sellers sellers = sellesRepository.findByUser(user);
        Products products = modelMapper.map(productRequest, Products.class);
        products.setStatus(statusRepository.findById(1).get());
        products.setSeller(sellers);
        products.setRating(0);
        if (productRequest.getProductCategoryId() != null) {
            products.setProductCategories(productCategoryRepository.findById(productRequest.getProductCategoryId()).orElseThrow(() -> new BussinesException("Product Category ID NOT FOUND")));
        }
        return productRepository.save(products);
    }

    @Transactional
    @Override
    public Products updateProduct(int productId, ProductRequest productRequest) {
        Products products = productRepository.findById(productId).orElseThrow(() -> new BussinesException("PRODUCT ID NOT FOUND"));
        products.setProductName(products.getProductName());
        products.setProductPrice(productRequest.getProductPrice());
        products.setImage(products.getImage());
        products.setProductDescription(products.getProductDescription());
        if (productRequest.getProductCategoryId() != null) {
            products.setProductCategories(productCategoryRepository.findById(productRequest.getProductCategoryId()).orElseThrow(() -> new BussinesException("Product Category ID NOT FOUND")));
        }
        return productRepository.save(products);
    }

    @Transactional(readOnly = true)
    @Override
    public Products productById(int productId) {
        return productRepository.findById(productId).orElseThrow(() -> new BussinesException("PRODUCT ID NOT FOUND"));
    }

    @Transactional
    @Override
    public Products deleteProduct(int productId) {
        Products products = productRepository.findById(productId).orElseThrow(() -> new BussinesException("PRODUCT ID NOT FOUND"));
        products.setDeleteDate(new Date());
        return productRepository.save(products);
    }


    @Transactional(readOnly = true)
    @Override
    public List<Products> recommendProduct() {
        Pageable pageable = PageRequest.of(0, 10);
        Status status = statusRepository.findById(1).get();
        return productRepository.findAllByDeleteDateIsNullAndStatusOrderByRatingDesc(status,pageable);
    }
}
