package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.AnalyticsAdminResponse;
import p2p.commerce.commerceapi.web.dto.AnalyticsMostProductResponse;
import p2p.commerce.commerceapi.web.dto.AnalyticsSellerResponse;
import p2p.commerce.commerceapi.web.service.AnalyticsService;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@AllArgsConstructor
public class AnalyticsController {
    private AnalyticsService analyticsService;

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/admin")
    public CommonResponse<AnalyticsAdminResponse> getAnalyticsOverviewAdmin() {
        return ResponseHelper.ok(analyticsService.findAnalyticsAdmin());
    }


    @PreAuthorize("hasAnyAuthority('Admin', 'Seller')")
    @GetMapping("/most-product")
    public CommonResponse<List<AnalyticsMostProductResponse>> analyticsMostProduct() {
        return ResponseHelper.ok(analyticsService.analyticsMostProduct());
    }

    @PreAuthorize("hasAuthority('Seller')")
    @GetMapping("/seller")
    public CommonResponse<AnalyticsSellerResponse> getAnalyticsOverviewSeller() {
        return ResponseHelper.ok(analyticsService.findAnalyticsSeller());
    }
}
