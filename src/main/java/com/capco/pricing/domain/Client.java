package com.capco.pricing.domain;

import lombok.Getter;

import java.util.Objects;
@Getter
public abstract class Client {

    private final String clientId;

    protected Client(String clientId) {
        this.clientId = Objects.requireNonNull(clientId, "clientId must not be null");
    }

}