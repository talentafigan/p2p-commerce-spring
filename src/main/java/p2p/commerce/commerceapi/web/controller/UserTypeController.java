package p2p.commerce.commerceapi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.model.Status;
import p2p.commerce.commerceapi.web.model.UserType;
import p2p.commerce.commerceapi.web.service.StatusService;
import p2p.commerce.commerceapi.web.service.UserTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/user-type")
public class UserTypeController {
    @Autowired
    private UserTypeService userTypeService;

    @GetMapping
    public CommonResponse<List<UserType>> findAll() {
        return ResponseHelper.ok(userTypeService.findAll());
    }

    @GetMapping("/{userTypeId}")
    public CommonResponse<UserType> findById(@PathVariable("userTypeId") int userTypeId) {
        return ResponseHelper.ok(userTypeService.findById(userTypeId));
    }
}

