package p2p.commerce.commerceapi.web.service;

import p2p.commerce.commerceapi.web.dto.AnalyticsAdminResponse;
import p2p.commerce.commerceapi.web.dto.AnalyticsMostProductResponse;
import p2p.commerce.commerceapi.web.dto.AnalyticsSellerResponse;

import java.util.List;

public interface AnalyticsService {
    AnalyticsAdminResponse findAnalyticsAdmin();

    List<AnalyticsMostProductResponse> analyticsMostProduct();

    AnalyticsSellerResponse findAnalyticsSeller();
}
