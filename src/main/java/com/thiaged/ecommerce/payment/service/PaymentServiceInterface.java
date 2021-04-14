package com.thiaged.ecommerce.payment.service;

import com.thiaged.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.thiaged.ecommerce.payment.entity.PaymentEntity;

import java.util.Optional;

public interface PaymentServiceInterface {

    Optional<PaymentEntity> create(CheckoutCreatedEvent checkoutCreatedEvent);
}
