package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.model.Clients;
import p2p.commerce.commerceapi.web.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping
    public CommonResponse<List<Clients>> findAll() {
        return ResponseHelper.ok(clientService.findAll());
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/{id}")
    public CommonResponse<Clients> findById(@PathVariable("id") int id) {
        return ResponseHelper.ok(clientService.findById(id));
    }
}
