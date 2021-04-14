package com.thiaged.ecommerce.payment.service;

import com.thiaged.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.thiaged.ecommerce.payment.entity.PaymentEntity;
import com.thiaged.ecommerce.payment.repository.PaymentRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentServiceInterface {

    private final PaymentRepositoryInterface paymentRepository;
    @Override
    public Optional<PaymentEntity> create(CheckoutCreatedEvent checkoutCreatedEvent) {
        final PaymentEntity paymentEntity = PaymentEntity.builder()
                .checkoutCode(checkoutCreatedEvent.getCheckoutCode().toString())
                .code(UUID.randomUUID().toString())
                .build();
        paymentRepository.save(paymentEntity);

        return Optional.of(paymentEntity);
    }
}
