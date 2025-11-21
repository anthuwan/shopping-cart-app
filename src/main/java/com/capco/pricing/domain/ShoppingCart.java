package com.capco.pricing.domain;

import lombok.Getter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class ShoppingCart {
    private final Client client;
    private final Map<ProductType, Integer> items = new EnumMap<>(ProductType.class);

    public ShoppingCart(Client client) {
        this.client = Objects.requireNonNull(client, "client must not be null");
    }

    public Map<ProductType, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public void addItem(ProductType productType, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        items.merge(productType, quantity, Integer::sum);
    }
}