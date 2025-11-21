package com.capco.pricing.dto;

import com.capco.pricing.domain.ProductType;
import lombok.Getter;

@Getter
public class CartItemDto {

    private ProductType productType;
    private int quantity;

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}