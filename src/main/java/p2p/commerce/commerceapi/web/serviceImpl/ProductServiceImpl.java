package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.web.model.Products;
import p2p.commerce.commerceapi.web.model.Status;
import p2p.commerce.commerceapi.web.repository.ProductRepository;
import p2p.commerce.commerceapi.web.repository.StatusRepository;
import p2p.commerce.commerceapi.web.service.ProductService;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private StatusRepository statusRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<Products> findAllProduct(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Status status = statusRepository.findById(1).get();
        return productRepository.findAllByProductNameContainingIgnoreCaseAndStatus(name, status, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Products> recommendProduct() {
        Pageable pageable = PageRequest.of(0, 10);
        Status status = statusRepository.findById(1).get();
        return productRepository.findAllByStatusOrderByRatingDesc(status,pageable);
    }
}
