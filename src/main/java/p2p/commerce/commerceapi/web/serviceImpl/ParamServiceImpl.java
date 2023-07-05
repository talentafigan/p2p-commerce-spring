package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import p2p.commerce.commerceapi.web.service.ParamService;

@Slf4j
@Service
public class ParamServiceImpl implements ParamService {

    @Value("${app.coin-price}")
    private int coinPrice;

    @Value("${app.fees}")
    private int adminFee;

    @Override
    public Integer priceCoin() {
        return coinPrice;
    }

    @Override
    public Integer adminFee() {
        return adminFee;
    }
}
