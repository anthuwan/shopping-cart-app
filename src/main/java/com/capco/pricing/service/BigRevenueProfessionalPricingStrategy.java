package com.capco.pricing.service;

import com.capco.pricing.domain.ProductType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigRevenueProfessionalPricingStrategy implements PricingStrategy {

    private static final BigDecimal HIGH_END_PHONE_PRICE = new BigDecimal("1000");
    private static final BigDecimal MID_RANGE_PHONE_PRICE = new BigDecimal("550");
    private static final BigDecimal LAPTOP_PRICE = new BigDecimal("900");

    @Override
    public BigDecimal unitPrice(ProductType productType) {
        switch (productType) {
            case HIGH_END_PHONE:
                return HIGH_END_PHONE_PRICE;
            case MID_RANGE_PHONE:
                return MID_RANGE_PHONE_PRICE;
            case LAPTOP:
                return LAPTOP_PRICE;
            default:
                throw new IllegalArgumentException("Unknown product type: " + productType);
        }
    }
}