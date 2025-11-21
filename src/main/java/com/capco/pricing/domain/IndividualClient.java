package com.capco.pricing.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class IndividualClient extends Client {

    private final String firstName;
    private final String lastName;

    public IndividualClient(String clientId, String firstName, String lastName) {
        super(clientId);
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}