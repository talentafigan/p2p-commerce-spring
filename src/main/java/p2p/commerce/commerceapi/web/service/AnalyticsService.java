package p2p.commerce.commerceapi.web.service;

import p2p.commerce.commerceapi.web.dto.AnalyticsAdminResponse;
import p2p.commerce.commerceapi.web.dto.AnalyticsSellerResponse;

public interface AnalyticsService {
    AnalyticsAdminResponse findAnalyticsAdmin();
    AnalyticsSellerResponse findAnalyticsSeller();
}
