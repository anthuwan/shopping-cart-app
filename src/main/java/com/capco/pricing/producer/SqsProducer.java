package com.capco.pricing.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "shopping.cart.sqs.enabled", havingValue = "true", matchIfMissing = false)
public class SqsProducer {

    private final SqsAsyncClient sqsAsyncClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(String queueUrl, Object message) {
        try {
            String body = objectMapper.writeValueAsString(message);

            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(body)
                    .build();

            sqsAsyncClient.sendMessage(request);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to send SQS message", ex);
        }
    }
}
