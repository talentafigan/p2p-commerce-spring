package p2p.commerce.commerceapi.web.service;

import org.springframework.data.domain.Page;
import p2p.commerce.commerceapi.web.model.WalletTransaction;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface WalletTransactionService {
    List<WalletTransaction> getAll(String date);
}
