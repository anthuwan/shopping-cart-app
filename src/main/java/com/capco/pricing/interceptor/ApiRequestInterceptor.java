package com.capco.pricing.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Simple interceptor for the shopping-cart app.
 *
 * - Generates / propagates a requestId and puts it in MDC.
 * - Logs caller and request details.
 * - Optionally performs a basic API-key check on incoming requests.
 */
@Slf4j
@Component
public class ApiRequestInterceptor implements HandlerInterceptor {

    private static final String X_REQUEST_ID = "X-Request-Id";
    private static final String X_CLIENT_ID = "X-Client-Id";

    private final boolean securityEnabled;
    private final String apiKeyHeaderName;
    private final String expectedApiKey;

    public ApiRequestInterceptor(
            @Value("${app.security.enabled:false}") boolean securityEnabled,
            @Value("${app.security.api-key-header:X-API-KEY}") String apiKeyHeaderName,
            @Value("${app.security.api-key-value:}") String expectedApiKey) {
        this.securityEnabled = securityEnabled;
        this.apiKeyHeaderName = apiKeyHeaderName;
        this.expectedApiKey = expectedApiKey;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) {

        // Set up request id for logging
        String requestId = generateRequestId(request.getHeader(X_REQUEST_ID));
        MDC.put(X_REQUEST_ID, requestId);

        String clientId = StringUtils.defaultIfBlank(request.getHeader(X_CLIENT_ID), "UNKNOWN");

        log.debug(
                "caller:{}, httpMethod:{}, uri:{}, query:{}, requestId:{}",
                clientId,
                request.getMethod(),
                request.getRequestURI(),
                request.getQueryString(),
                requestId);

        if (securityEnabled) {
            authenticate(request);
        }

        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        MDC.remove(X_REQUEST_ID);
    }

    private void authenticate(HttpServletRequest request) {
        if (StringUtils.isBlank(expectedApiKey)) {
            log.warn("Security enabled but no expected API key configured");
            return;
        }

        String apiKey = request.getHeader(apiKeyHeaderName);
        if (!StringUtils.equals(apiKey, expectedApiKey)) {
            log.warn("Invalid or missing API key for uri={}", request.getRequestURI());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid API key");
        }
    }

    private String generateRequestId(String incoming) {
        if (StringUtils.isNotBlank(incoming)) {
            return incoming;
        }
        String newRequestId = UUID.randomUUID().toString();
        log.debug("X-Request-Id is absent, generating one: {}", newRequestId);
        return newRequestId;
    }
}