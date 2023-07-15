package p2p.commerce.commerceapi.configuration.async;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import p2p.commerce.commerceapi.web.model.ProductTransactions;
import p2p.commerce.commerceapi.web.model.Products;
import p2p.commerce.commerceapi.web.repository.ProductRepository;
import p2p.commerce.commerceapi.web.repository.ProductTransactionRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class MainAsync {
    private ProductTransactionRepository productTransactionRepository;
    private ProductRepository productRepository;

    @Async
    public void updateRatingProduct() {
        List<Products> products = productRepository.findAll();
        for (Products p : products) {
            int point1 = 0;
            int point2 = 0;
            int point3 = 0;
            int point4 = 0;
            int point5 = 0;
            List<ProductTransactions> productTransactions = productTransactionRepository.findAllByProduct(p);
            for (ProductTransactions p2 : productTransactions) {
                if (p2.getRating() == 1) {
                    point1 += 1;
                } else if (p2.getRating() == 2) {
                    point2 += 1;
                } else if (p2.getRating() == 3) {
                    point3 += 1;
                } else if (p2.getRating() == 4) {
                    point4 += 1;
                } else if (p2.getRating() == 5) {
                    point5 += 1;
                }
            }
            try {
                p.setRating(((5 * point5) + (4 * point4) + (3 * point3) + (2 * point2) + (1 * point1)) / (point5 + point4 + point3 + point2 + point1));
                productRepository.save(p);
            } catch (Exception e) {
            }
        }
    }
}
