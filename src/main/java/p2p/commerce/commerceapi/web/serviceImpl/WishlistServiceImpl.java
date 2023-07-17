package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.dto.WishlistRequest;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Products;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.model.Wishlist;
import p2p.commerce.commerceapi.web.repository.ClientRepository;
import p2p.commerce.commerceapi.web.repository.ProductRepository;
import p2p.commerce.commerceapi.web.repository.WishlistRepository;
import p2p.commerce.commerceapi.web.service.WishlistService;

@Slf4j
@Service
@AllArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private WishlistRepository wishlistRepository;
    private ClientRepository clientRepository;
    private ProductRepository productRepository;
    private AuthenticationFacade authenticationFacade;

    @Transactional(readOnly = true)
    @Override
    public Wishlist addWishlist(WishlistRequest wishlistRequest) {
        Clients client = clientRepository.findById(wishlistRequest.getClientId()).orElseThrow(() -> new BussinesException("Client Id not found"));
        Products product= productRepository.findById(wishlistRequest.getProductId()).orElseThrow(() -> new BussinesException("Product Id not found"));
        return wishlistRepository.save(Wishlist.builder().client(client).product(product).build());
    }

    @Transactional(readOnly = true)
    @Override
    public Wishlist findWishlistByProduct(int productId) {
        Products product= productRepository.findById(productId).orElseThrow(() -> new BussinesException("Product Id not found"));
        return wishlistRepository.findByProduct(product).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Wishlist> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Users user= authenticationFacade.getAuthentication();
        Clients client = clientRepository.findByUser(user);
        return wishlistRepository.findAllByClient(client, pageable);
    }

    @Transactional
    @Override
    public String remove(int wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new BussinesException("WISHLIST ID NOT FOUND"));
        wishlistRepository.delete(wishlist);
        return "SUCCESS";
    }
}
