package com.capco.pricing.dto;

import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class CartTotalResponse {

    private BigDecimal total;

    public CartTotalResponse() {
    }

    public CartTotalResponse(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}