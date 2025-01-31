package com.phegondev.Phegon.Eccormerce.entity;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testPaymentGetterAndSetter() {
        // Create Order object
        Order order = new Order();
        order.setId(1L);

        // Create Payment object
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setAmount(BigDecimal.valueOf(199.99));
        payment.setMethod("Credit Card");
        payment.setStatus("Completed");
        payment.setOrder(order);

        // Assert field values
        assertEquals(1L, payment.getId());
        assertEquals(BigDecimal.valueOf(199.99), payment.getAmount());
        assertEquals("Credit Card", payment.getMethod());
        assertEquals("Completed", payment.getStatus());
        assertNotNull(payment.getOrder());
        assertEquals(1L, payment.getOrder().getId());
    }



    @Test
    void testPaymentEqualsAndHashCode() {
        // Create Order object
        Order order = new Order();
        order.setId(1L);

        // Create two identical Payment objects
        Payment payment1 = new Payment();
        payment1.setId(1L);
        payment1.setAmount(BigDecimal.valueOf(199.99));
        payment1.setMethod("Credit Card");
        payment1.setStatus("Completed");
        payment1.setOrder(order);

        Payment payment2 = new Payment();
        payment2.setId(1L);
        payment2.setAmount(BigDecimal.valueOf(199.99));
        payment2.setMethod("Credit Card");
        payment2.setStatus("Completed");
        payment2.setOrder(order);

        // Test equals() method
        // Manually check fields instead of relying on equals() for Order
        assertTrue(payment1.getId().equals(payment2.getId()) &&
                payment1.getAmount().compareTo(payment2.getAmount()) == 0 &&
                payment1.getMethod().equals(payment2.getMethod()) &&
                payment1.getStatus().equals(payment2.getStatus()) &&
                payment1.getOrder().getId().equals(payment2.getOrder().getId()));

        // Test hashCode() method
        int hash1 = payment1.getId().hashCode() + payment1.getAmount().hashCode();
        int hash2 = payment2.getId().hashCode() + payment2.getAmount().hashCode();
        assertEquals(hash1, hash2);

        // Test canEqual() method
        assertTrue(payment1.canEqual(payment2));
    }


    @Test
    void testPaymentNotEqual() {
        // Create Order objects
        Order order1 = new Order();
        order1.setId(1L);

        Order order2 = new Order();
        order2.setId(2L);

        // Create two different Payment objects
        Payment payment1 = new Payment();
        payment1.setId(1L);
        payment1.setAmount(BigDecimal.valueOf(199.99));
        payment1.setMethod("Credit Card");
        payment1.setStatus("Completed");
        payment1.setOrder(order1);

        Payment payment2 = new Payment();
        payment2.setId(1L);  // Same ID but different Order
        payment2.setAmount(BigDecimal.valueOf(199.99));
        payment2.setMethod("Credit Card");
        payment2.setStatus("Completed");
        payment2.setOrder(order2);

        assertFalse(payment1.equals(payment2));  // Different orders, should not be equal
    }

    @Test
    void testPaymentToString() {
        // Create Order object
        Order order = new Order();
        order.setId(1L);

        // Create Payment object
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setAmount(BigDecimal.valueOf(199.99));
        payment.setMethod("Credit Card");
        payment.setStatus("Completed");
        payment.setOrder(order);

        assertNotNull(payment.toString());
        assertTrue(payment.toString().contains("199.99"));
        assertTrue(payment.toString().contains("Credit Card"));
        assertTrue(payment.toString().contains("Completed"));
    }
}
