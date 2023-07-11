package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.Products;
import p2p.commerce.commerceapi.web.model.Sellers;
import p2p.commerce.commerceapi.web.model.Status;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
    List<Products> findAllBySeller(Sellers sellers);
    Page<Products> findAllByProductNameContainingIgnoreCaseAndStatus(String name, Status status, Pageable pageable);

    List<Products> findAllByStatusOrderByRatingDesc(Status status, Pageable pageable);
}
