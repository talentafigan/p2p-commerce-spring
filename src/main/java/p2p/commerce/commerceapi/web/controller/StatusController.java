package p2p.commerce.commerceapi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.model.Status;
import p2p.commerce.commerceapi.web.service.StatusService;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping
    public CommonResponse<List<Status>> findAll() {
        return ResponseHelper.ok(statusService.findAll());
    }

    @GetMapping("/{statusId}")
    public CommonResponse<Status> findById(@PathVariable("statusId") int statusId) {
        return ResponseHelper.ok(statusService.findById(statusId));
    }
}
