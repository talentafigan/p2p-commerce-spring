package p2p.commerce.commerceapi.web.service;

import p2p.commerce.commerceapi.web.model.Status;

import java.util.List;

public interface StatusService {
    List<Status> findAll();
    Status findById(int statusId);
}
