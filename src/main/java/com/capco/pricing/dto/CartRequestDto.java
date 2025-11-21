package com.capco.pricing.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class CartRequestDto {

    private ClientDto client;
    private List<CartItemDto> items;

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }
}