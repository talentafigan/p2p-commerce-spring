package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Products;
import p2p.commerce.commerceapi.web.model.Wishlist;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    Page<Wishlist> findAllByClient(Clients clients, Pageable pageable);

    Optional<Wishlist> findByProduct(Products products);
}
