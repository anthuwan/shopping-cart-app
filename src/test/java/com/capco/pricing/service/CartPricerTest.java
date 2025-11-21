package com.capco.pricing.service;

import com.capco.pricing.domain.IndividualClient;
import com.capco.pricing.domain.ProfessionalClient;
import com.capco.pricing.domain.ProductType;
import com.capco.pricing.domain.ShoppingCart;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartPricerTest {

    private CartPricer cartPricer;

    @BeforeEach
    void setUp() {
        IndividualPricingStrategy individualPricingStrategy = new IndividualPricingStrategy();
        BigRevenueProfessionalPricingStrategy bigRevenueStrategy =
                new BigRevenueProfessionalPricingStrategy();
        SmallRevenueProfessionalPricingStrategy smallRevenueStrategy =
                new SmallRevenueProfessionalPricingStrategy();

        PricingStrategyResolver resolver =
                new PricingStrategyResolver(
                        individualPricingStrategy,
                        bigRevenueStrategy,
                        smallRevenueStrategy
                );

        cartPricer = new CartPricer(resolver);
    }

    @Test
    void shouldCalculateTotalForIndividualClient() {
        IndividualClient client = new IndividualClient("C001", "John", "Doe");
        ShoppingCart cart = new ShoppingCart(client);

        cart.addItem(ProductType.HIGH_END_PHONE, 1);
        cart.addItem(ProductType.MID_RANGE_PHONE, 2);
        cart.addItem(ProductType.LAPTOP, 1);

        BigDecimal total = cartPricer.calculateTotal(cart);

        assertEquals(new BigDecimal("4300.00"), total);
    }

    @Test
    void shouldCalculateTotalForProfessionalClientWithRevenueGreaterThan10M() {
        ProfessionalClient client = new ProfessionalClient(
                "C100",
                "BigCorp Ltd",
                "FR123456789",
                "RC123456",
                new BigDecimal("15000000")
        );
        ShoppingCart cart = new ShoppingCart(client);

        cart.addItem(ProductType.HIGH_END_PHONE, 3);
        cart.addItem(ProductType.MID_RANGE_PHONE, 5);
        cart.addItem(ProductType.LAPTOP, 2);

        BigDecimal total = cartPricer.calculateTotal(cart);

        assertEquals(new BigDecimal("7550.00"), total);
    }

    @Test
    void shouldCalculateTotalForProfessionalClientWithRevenueBelowOrEqual10M() {
        ProfessionalClient client = new ProfessionalClient(
                "C200",
                "SmallBiz SARL",
                null,
                "RC987654",
                new BigDecimal("5000000")
        );
        ShoppingCart cart = new ShoppingCart(client);
        cart.addItem(ProductType.HIGH_END_PHONE, 1);
        cart.addItem(ProductType.MID_RANGE_PHONE, 1);
        cart.addItem(ProductType.LAPTOP, 1);

        BigDecimal total = cartPricer.calculateTotal(cart);

        assertEquals(new BigDecimal("2750.00"), total);
    }

    @Test
    void shouldReturnZeroForEmptyCart() {
        IndividualClient client = new IndividualClient("C999", "Empty", "Cart");
        ShoppingCart cart = new ShoppingCart(client);

        BigDecimal total = cartPricer.calculateTotal(cart);

        assertEquals(new BigDecimal("0.00"), total);
    }
}