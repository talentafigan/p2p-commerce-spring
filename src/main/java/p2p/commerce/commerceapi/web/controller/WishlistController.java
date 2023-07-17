package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.WishlistRequest;
import p2p.commerce.commerceapi.web.model.Wishlist;
import p2p.commerce.commerceapi.web.service.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
@AllArgsConstructor
public class WishlistController {
    private WishlistService wishlistService;

    @PreAuthorize("hasAuthority('Client')")
    @PostMapping
    public CommonResponse<Wishlist> createWishlist(@RequestBody WishlistRequest wishlistRequest) {
        return ResponseHelper.ok(wishlistService.addWishlist(wishlistRequest));
    }

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping("/product/{productId}")
    public CommonResponse<Wishlist> findWishlistByProduct(@PathVariable("productId") int productId) {
        return ResponseHelper.ok(wishlistService.findWishlistByProduct(productId));
    }

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping
    public CommonResponse<Page<Wishlist>> findAll(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseHelper.ok(wishlistService.getAll(page,size));
    }

    @PreAuthorize("hasAuthority('Client')")
    @DeleteMapping("/{wishlistId}")
    public CommonResponse<String> delete(@PathVariable("wishlistId") int wishlistId) {
        return ResponseHelper.ok(wishlistService.remove(wishlistId));
    }
}
