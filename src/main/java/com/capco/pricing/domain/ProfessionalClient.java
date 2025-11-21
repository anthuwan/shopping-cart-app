package com.capco.pricing.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;
@Getter
public class ProfessionalClient extends Client {

    private final String companyName;
    private final String vatNumber;
    private final String registrationNumber;
    private final BigDecimal annualRevenue;

    public ProfessionalClient(String clientId,
                              String companyName,
                              String vatNumber,
                              String registrationNumber,
                              BigDecimal annualRevenue) {
        super(clientId);
        this.companyName = Objects.requireNonNull(companyName, "companyName must not be null");
        this.vatNumber = vatNumber;
        this.registrationNumber = Objects.requireNonNull(registrationNumber, "registrationNumber must not be null");
        this.annualRevenue = Objects.requireNonNull(annualRevenue, "annualRevenue must not be null");
    }

}