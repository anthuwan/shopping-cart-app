package com.capco.pricing.consumer;

import com.capco.pricing.domain.CartEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "shopping.cart.sqs.enabled", havingValue = "true", matchIfMissing = false)
public class CartEventConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SqsListener("${aws.sqs.cart-events-queue-url}")
    public void consumeMessage(@Payload String message) {
        try {
            CartEvent cartEvent = objectMapper.readValue(message, CartEvent.class);
            log.info("📥 Received Cart Event: {}", cartEvent);

            handleCartEvent(cartEvent);

        } catch (Exception e) {
            log.error(" Error processing message: {}", message, e);
            throw new RuntimeException("Failed to process message");
        }
    }

    private void handleCartEvent(CartEvent event) {
        // Business logic
        log.info("Processing event for clientId={} and total={}",
                event.getClientId(), event.getTotal());

    }
}
