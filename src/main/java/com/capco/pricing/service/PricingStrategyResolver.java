package com.capco.pricing.service;

import com.capco.pricing.domain.Client;
import com.capco.pricing.domain.IndividualClient;
import com.capco.pricing.domain.ProfessionalClient;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class PricingStrategyResolver {

    private static final BigDecimal TEN_MILLION = new BigDecimal("10000000");

    private final IndividualPricingStrategy individualPricing;
    private final BigRevenueProfessionalPricingStrategy bigRevenuePricing;
    private final SmallRevenueProfessionalPricingStrategy smallRevenuePricing;

    public PricingStrategyResolver(IndividualPricingStrategy individualPricing,
                                   BigRevenueProfessionalPricingStrategy bigRevenuePricing,
                                   SmallRevenueProfessionalPricingStrategy smallRevenuePricing) {
        this.individualPricing = Objects.requireNonNull(individualPricing);
        this.bigRevenuePricing = Objects.requireNonNull(bigRevenuePricing);
        this.smallRevenuePricing = Objects.requireNonNull(smallRevenuePricing);
    }

    public PricingStrategy resolve(Client client) {
        if (client instanceof IndividualClient) {
            return individualPricing;
        }

        if (client instanceof ProfessionalClient) {
            ProfessionalClient pc = (ProfessionalClient) client;
            BigDecimal revenue = pc.getAnnualRevenue();

            // > 10M → big revenue; else ≤ 10M → small revenue
            if (revenue.compareTo(TEN_MILLION) > 0) {
                return (PricingStrategy) bigRevenuePricing;
            } else {
                return smallRevenuePricing;
            }
        }

        throw new IllegalArgumentException("Unknown client type: " + client.getClass());
    }
}