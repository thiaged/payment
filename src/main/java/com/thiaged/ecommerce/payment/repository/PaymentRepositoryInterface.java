package com.thiaged.ecommerce.payment.repository;

import com.thiaged.ecommerce.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepositoryInterface extends JpaRepository<PaymentEntity, Long> {
}
