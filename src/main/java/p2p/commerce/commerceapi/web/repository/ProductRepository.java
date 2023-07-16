package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.Products;
import p2p.commerce.commerceapi.web.model.Sellers;
import p2p.commerce.commerceapi.web.model.Status;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
    List<Products> findAllBySeller(Sellers sellers);

    @Query(value = "SELECT a.* FROM products a inner JOIN sellers s on (a.seller_id = s.seller_id)  WHERE a.product_name LIKE CONCAT('%', ?1,'%') AND s.username LIKE CONCAT('%', ?1,'%') AND a.status_id = ?2", nativeQuery = true)
    Page<Products> findAllProductLikeWhere(String searchKey, int statusId, Pageable pageable);

    @Query(value = "SELECT a.* FROM products a inner JOIN sellers s on (a.seller_id = s.seller_id)  WHERE a.product_name LIKE CONCAT('%', ?1,'%') AND s.username LIKE CONCAT('%', ?1,'%') AND a.product_category_id = ?2 AND a.seller_id = ?3", nativeQuery = true)
    Page<Products> findAllProductLikeWhereCategory(String searchKey, int productCategoryId, int statusId, Pageable pageable);

    List<Products> findAllByDeleteDateIsNullAndStatusOrderByRatingDesc(Status status, Pageable pageable);
}
