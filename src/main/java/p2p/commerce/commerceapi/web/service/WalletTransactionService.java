package p2p.commerce.commerceapi.web.service;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.model.WalletTransaction;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface WalletTransactionService {
    List<WalletTransaction> getAll(String date);
    List<WalletTransaction> findAll();

    WalletTransaction findById(int id);


    WalletTransaction createDebitConsultation(Users user, Clients clients, int amount);

    List<WalletTransaction> findAllActive();
}
