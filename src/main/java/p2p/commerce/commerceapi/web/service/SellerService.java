package p2p.commerce.commerceapi.web.service;

import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Sellers;

import java.util.List;

public interface SellerService {
    List<Sellers> findAll();
    Sellers findById(int id);
}
