package com.capco.pricing.service;

import com.capco.pricing.domain.ProductType;
import com.capco.pricing.domain.ShoppingCart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;

@Service
public class CartPricer {

    private final PricingStrategyResolver pricingStrategyResolver;

    public CartPricer(PricingStrategyResolver pricingStrategyResolver) {
        this.pricingStrategyResolver = Objects.requireNonNull(pricingStrategyResolver);
    }

    public BigDecimal calculateTotal(ShoppingCart cart) {
        PricingStrategy strategy = pricingStrategyResolver.resolve(cart.getClient());

        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<ProductType, Integer> entry : cart.getItems().entrySet()) {
            ProductType productType = entry.getKey();
            int quantity = entry.getValue();

            BigDecimal unitPrice = strategy.unitPrice(productType);
            BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
            total = total.add(lineTotal);
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}