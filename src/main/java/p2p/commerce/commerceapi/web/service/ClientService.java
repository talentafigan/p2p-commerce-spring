package p2p.commerce.commerceapi.web.service;

import p2p.commerce.commerceapi.web.model.Clients;

import java.util.List;

public interface ClientService {
    List<Clients> findAll();
    Clients findById(int id);
}
