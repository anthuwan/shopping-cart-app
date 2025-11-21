package com.capco.pricing.service;

import com.capco.pricing.domain.ProductType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class IndividualPricingStrategy implements PricingStrategy {

    private static final BigDecimal HIGH_END_PHONE_PRICE = new BigDecimal("1500");
    private static final BigDecimal MID_RANGE_PHONE_PRICE = new BigDecimal("800");
    private static final BigDecimal LAPTOP_PRICE = new BigDecimal("1200");

    @Override
    public BigDecimal unitPrice(ProductType productType) {
        return switch (productType) {
            case HIGH_END_PHONE -> HIGH_END_PHONE_PRICE;
            case MID_RANGE_PHONE -> MID_RANGE_PHONE_PRICE;
            case LAPTOP -> LAPTOP_PRICE;
            default -> throw new IllegalArgumentException("Unknown product type: " + productType);
        };
    }
}