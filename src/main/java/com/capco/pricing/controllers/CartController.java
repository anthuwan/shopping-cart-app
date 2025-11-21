package com.capco.pricing.controllers;
import com.capco.pricing.domain.Client;
import com.capco.pricing.domain.IndividualClient;
import com.capco.pricing.domain.ProfessionalClient;
import com.capco.pricing.domain.ProductType;
import com.capco.pricing.domain.ShoppingCart;
import com.capco.pricing.dto.*;
import com.capco.pricing.service.CartPricer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartPricer cartPricer;

    public CartController(CartPricer cartPricer) {
        this.cartPricer = cartPricer;
    }

    @PostMapping("/total")
    public ResponseEntity<CartTotalResponse> calculateTotal(@RequestBody CartRequestDto request) {

        Client client = toDomainClient(request.getClient());
        ShoppingCart cart = new ShoppingCart(client);

        for (CartItemDto itemDto : request.getItems()) {
            ProductType productType = itemDto.getProductType();
            int quantity = itemDto.getQuantity();
            cart.addItem(productType, quantity);
        }

        BigDecimal total = cartPricer.calculateTotal(cart);
        return ResponseEntity.ok(new CartTotalResponse(total));
    }

    private Client toDomainClient(ClientDto dto) {
        if (dto.getType() == ClientType.INDIVIDUAL) {
            return new IndividualClient(
                    dto.getClientId(),
                    dto.getFirstName(),
                    dto.getLastName()
            );
        } else if (dto.getType() == ClientType.PROFESSIONAL) {
            return new ProfessionalClient(
                    dto.getClientId(),
                    dto.getCompanyName(),
                    dto.getVatNumber(),
                    dto.getRegistrationNumber(),
                    dto.getAnnualRevenue()
            );
        } else {
            throw new IllegalArgumentException("Unsupported client type: " + dto.getType());
        }
    }
}