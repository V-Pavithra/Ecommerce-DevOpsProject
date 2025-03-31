package com.ecommerce.payment.controller;

import com.ecommerce.payment.model.Payment;
import com.ecommerce.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public Payment processPayment(@RequestParam Long orderId) {
        return paymentService.processPayment(orderId);
    }

    @GetMapping("/{orderId}")
    public Payment getPaymentStatus(@PathVariable Long orderId){
        return paymentService.getPaymentStatus(orderId);
    }
}
