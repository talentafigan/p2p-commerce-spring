package p2p.commerce.commerceapi.web.service;

import org.springframework.data.domain.Page;
import p2p.commerce.commerceapi.web.dto.WishlistRequest;
import p2p.commerce.commerceapi.web.model.Wishlist;

public interface WishlistService {
    Wishlist addWishlist(WishlistRequest wishlistRequest);

    Wishlist findWishlistByProduct(int productId);

    Page<Wishlist> getAll(int page, int size);
    String remove(int wishlistId);
}
