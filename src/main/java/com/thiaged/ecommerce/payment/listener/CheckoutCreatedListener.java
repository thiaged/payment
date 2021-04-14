package com.thiaged.ecommerce.payment.listener;

import com.thiaged.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.thiaged.ecommerce.checkout.event.PaymentCreatedEvent;
import com.thiaged.ecommerce.payment.entity.PaymentEntity;
import com.thiaged.ecommerce.payment.service.PaymentServiceInterface;
import com.thiaged.ecommerce.payment.streaming.CheckoutProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckoutCreatedListener {

    private final CheckoutProcessor checkoutProcessor;

    private final PaymentServiceInterface paymentService;

    @StreamListener(CheckoutProcessor.INPUT)
    public void handler(CheckoutCreatedEvent checkoutCreatedEvent) {
        log.info("checkoutCreatedEvent={}", checkoutCreatedEvent);
        //processa pagamento
        //salva dados de pagamento
        final PaymentEntity paymentEntity = paymentService.create(checkoutCreatedEvent).orElseThrow();
        //enviar evento de pagamento processado
        final PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent.newBuilder()
                .setCheckoutCode(checkoutCreatedEvent.getCheckoutCode())
                .setPaymentCode(paymentEntity.getCode())
                .build();
        checkoutProcessor.output().send(MessageBuilder.withPayload(paymentCreatedEvent).build());
    }
}
