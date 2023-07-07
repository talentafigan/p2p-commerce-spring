package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.model.Sellers;
import p2p.commerce.commerceapi.web.repository.SellesRepository;
import p2p.commerce.commerceapi.web.service.SellerService;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {

    private SellesRepository sellesRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Sellers> findAll() {
        return sellesRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Sellers findById(int id) {
        return sellesRepository.findById(id).orElseThrow(() -> new BussinesException("Seller ID NOT FOUND"));
    }
}
