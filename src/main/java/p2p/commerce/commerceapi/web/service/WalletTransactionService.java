package p2p.commerce.commerceapi.web.service;

import org.springframework.data.domain.Page;
import p2p.commerce.commerceapi.web.model.WalletTransaction;

public interface WalletTransactionService {
    Page<WalletTransaction> getAll(int page, int size);
}
