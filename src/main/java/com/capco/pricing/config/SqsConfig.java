package com.capco.pricing.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
@ConditionalOnProperty(name = "shopping.cart.sqs.enabled", havingValue = "true", matchIfMissing = false)
public class SqsConfig {

    @Bean
    public SqsClient provideSqsClient(@Value("${aws.region}") final String region) {
        return SqsClient.builder()
                .region(Region.of(region))
                .build();
    }

}
