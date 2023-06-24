package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.model.Status;
import p2p.commerce.commerceapi.web.repository.StatusRepository;
import p2p.commerce.commerceapi.web.service.StatusService;
import p2p.commerce.commerceapi.web.service.UserService;

import java.util.List;

@Slf4j
@Service
public class StatusServiceImpl implements StatusService {

    private StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Status findById(int statusId) {
        return statusRepository.findById(statusId).orElseThrow(() -> new BussinesException("STATUS ID NOT FOUND"));
    }
}
