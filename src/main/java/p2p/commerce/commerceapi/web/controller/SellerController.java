package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.AvatarSallerRequest;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.model.Sellers;
import p2p.commerce.commerceapi.web.service.ClientService;
import p2p.commerce.commerceapi.web.service.SellerService;

import java.util.List;

@RestController
@RequestMapping("/api/seller")
@AllArgsConstructor
public class SellerController {
    private SellerService sellerService;
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping
    public CommonResponse<List<Sellers>> findAll() {
        return ResponseHelper.ok(sellerService.findAll());
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/{id}")
    public CommonResponse<Sellers> findById(@PathVariable("id") int id) {
        return ResponseHelper.ok(sellerService.findById(id));
    }

    @PreAuthorize("hasAuthority('Seller')")
    @PostMapping("/{sellerId}/avatar")
    public CommonResponse<Sellers> changeAvatar(@PathVariable("sellerId") int sellerId, @RequestBody AvatarSallerRequest avatarSallerRequest){
        return ResponseHelper.ok(sellerService.changeAvatar(sellerId, avatarSallerRequest));
    }
}
