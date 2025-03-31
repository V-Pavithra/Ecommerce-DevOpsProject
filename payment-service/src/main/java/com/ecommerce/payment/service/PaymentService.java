package com.ecommerce.payment.service;

import com.ecommerce.payment.dto.OrderResponse;
import com.ecommerce.payment.model.Payment;
import com.ecommerce.payment.model.PaymentStatus;
import com.ecommerce.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.Random;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RestTemplate restTemplate;
    private final Random random = new Random();
    public Payment processPayment(Long orderId) {

        // Fetch Order Details from Order Service
        String orderServiceUrl = "http://order-service:8083/api/orders/" + orderId;
        OrderResponse order = restTemplate.getForObject(orderServiceUrl, OrderResponse.class);

        if (order == null || order.getTotalPrice() == null) {
            throw new RuntimeException("Order not found or invalid total price!");
        }
        Double amount = order.getTotalPrice();

        // Process Payment (Simulate Success/Failure)
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setStatus(random.nextDouble() < 0.8 ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);

        return paymentRepository.save(payment);
    }

    public Payment getPaymentStatus(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
        .orElseThrow(() -> new RuntimeException("Payment not found for Order ID: "+ orderId));
    }
}
