package p2p.commerce.commerceapi.web.service;

import p2p.commerce.commerceapi.web.model.UserType;

import java.util.List;

public interface UserTypeService {
    List<UserType> findAll();
    UserType findById(int userTypeId);
}
