package com.ecommerce.payment_service.service;

import com.ecommerce.payment_service.model.Payment;
import com.ecommerce.payment_service.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Save Payment
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Get Payment By ID
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    // Get All Payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Update Payment
    public Payment updatePayment(Long id, Payment paymentDetails) {

        Payment payment = paymentRepository.findById(id).orElse(null);

        if(payment != null) {
            payment.setOrderId(paymentDetails.getOrderId());
            payment.setAmount(paymentDetails.getAmount());
            payment.setPaymentMethod(paymentDetails.getPaymentMethod());
            payment.setPaymentStatus(paymentDetails.getPaymentStatus());

            return paymentRepository.save(payment);
        }

        return null;
    }

    // Delete Payment
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}