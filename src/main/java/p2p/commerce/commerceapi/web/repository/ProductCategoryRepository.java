package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.ProductCategories;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategories, Integer> {
    List<ProductCategories> findAllByProductCategoryParentId(Integer parentId);
}
