package p2p.commerce.commerceapi.web.service;

import p2p.commerce.commerceapi.web.dto.TopUpResponse;
import p2p.commerce.commerceapi.web.dto.TopupRequest;
import p2p.commerce.commerceapi.web.dto.WalletResponse;

public interface WalletService {
    WalletResponse getWallet();
    TopUpResponse topUp(TopupRequest topupRequest);
}
