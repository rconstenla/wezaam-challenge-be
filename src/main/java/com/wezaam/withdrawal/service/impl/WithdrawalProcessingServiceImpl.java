package com.wezaam.withdrawal.service.impl;

import org.springframework.stereotype.Component;

import com.wezaam.withdrawal.exception.TransactionException;
import com.wezaam.withdrawal.model.PaymentMethod;
import com.wezaam.withdrawal.service.WithdrawalProcessingService;

@Component
public class WithdrawalProcessingServiceImpl implements WithdrawalProcessingService{

    public Long sendToProcessing(Double amount, PaymentMethod paymentMethod) throws TransactionException {
        // call a payment provider
        // in case a transaction can be process
        // it generates a transactionId and process the transaction async
        // otherwise it throws TransactionException
        return System.nanoTime();
    }
}
