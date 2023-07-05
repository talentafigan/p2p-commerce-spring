package p2p.commerce.commerceapi.configuration;

import ch.qos.logback.core.net.server.Client;
import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.model.Invoice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.WalletTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class XenditConfig {

    @Value("${app.xendit.api-key}")
    private String apiKey;
    @Value("${app.coin-price}")
    private int coinPrice;
    private static String[] paymentOptions = {"CREDIT_CARD", "BCA", "BNI", "BSI", "BRI", "MANDIRI", "PERMATA", "SAHABAT_SAMPOERNA", "ALFAMART", "INDOMARET", "OVO", "DANA", "SHOPEEPAY", "LINKAJA", "DD_BRI", "DD_BCA_KLIKPAY", "KREDIVO", "AKULAKU", "UANGME", "ATOME", "QRIS"};

    public Invoice createInvoice(WalletTransaction walletTransaction, Clients clients) {
        ArrayList<Object> items = new ArrayList<>();
        Xendit.Opt.setApiKey(apiKey);

        Map<String, Object> item = new HashMap<>();
        item.put("name", walletTransaction.getTransactionType().getName());
        item.put("price", coinPrice);
        item.put("quantity", walletTransaction.getAmount());
        items.add(item);

        Map<String, Object> customer = new HashMap<>();
        customer.put("given_names", clients.getFullname());
        customer.put("surname", clients.getUsername());
        customer.put("email", clients.getEmail());
        customer.put("mobile_number", clients.getPhone());

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", walletTransaction.getTransactionId());
            params.put("amount", (walletTransaction.getTotalPayment() + walletTransaction.getFee()));
            params.put("description", "Invoice " + clients.getEmail() + " Type " +walletTransaction.getTransactionType().getName());
            params.put("payer_email", clients.getEmail());
            params.put("should_send_email", true);
            params.put("payment_methods", Arrays.asList(paymentOptions));
            params.put("items", items);
            params.put("customer",customer);
            params.put("success_redirect_url", "https://p2p-web.project.jovalab.com/account/wallet");
            Invoice invoice = Invoice.create(params);
            return invoice;
        } catch (XenditException e) {
            e.printStackTrace();
            throw new BussinesException(e.getMessage());
        }
    }
}

