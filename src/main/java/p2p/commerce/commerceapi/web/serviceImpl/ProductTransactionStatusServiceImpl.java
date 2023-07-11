package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.model.ProductTransactionStatus;
import p2p.commerce.commerceapi.web.repository.ProductTransactionStatusRepository;
import p2p.commerce.commerceapi.web.service.ProductTransactionStatusService;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductTransactionStatusServiceImpl implements ProductTransactionStatusService {

    private ProductTransactionStatusRepository productTransactionStatusRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProductTransactionStatus> findAll() {
        return productTransactionStatusRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public ProductTransactionStatus findById(int id) {
        return productTransactionStatusRepository.findById(id).orElseThrow(() -> new BussinesException("Product transaction id NOT FOUND"));
    }
}
