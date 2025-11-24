package com.capco.pricing.producer;

import com.capco.pricing.domain.CartEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "shopping.cart.sqs.enabled", havingValue = "true", matchIfMissing = false)
public class CartEventPublisher {

    @Value("${aws.sqs.cart-events-queue-url}")
    private String queueUrl;

    private final SqsProducer sqsProducer;

    public void publishCartUpdated(String clientId, double total) {
        CartEvent event = new CartEvent();
        event.setClientId(clientId);
        event.setEventType("CART_UPDATED");
        event.setTotal(total);

        sqsProducer.sendMessage(queueUrl, event);
    }
}
