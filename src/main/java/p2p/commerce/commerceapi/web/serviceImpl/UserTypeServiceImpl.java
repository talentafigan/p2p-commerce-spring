package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.model.UserType;
import p2p.commerce.commerceapi.web.repository.UserTypeRepository;
import p2p.commerce.commerceapi.web.service.UserTypeService;

import java.util.List;

@Slf4j
@Service
public class UserTypeServiceImpl implements UserTypeService {

    private UserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeServiceImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public UserType findById(int userTypeId) {
        return userTypeRepository.findById(userTypeId).orElseThrow(() -> new BussinesException("USER TYPE ID NOT FOUND"));
    }
}
