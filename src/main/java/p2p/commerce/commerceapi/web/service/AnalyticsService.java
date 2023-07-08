package p2p.commerce.commerceapi.web.service;

import p2p.commerce.commerceapi.web.dto.AnalyticsResponse;

public interface AnalyticsService {
    AnalyticsResponse findAnalyticsAdmin();
    AnalyticsResponse findAnalyticsSeller();
}
