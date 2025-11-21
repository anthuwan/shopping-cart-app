package com.capco.pricing.service;


import com.capco.pricing.domain.ProductType;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal unitPrice(ProductType productType);
}