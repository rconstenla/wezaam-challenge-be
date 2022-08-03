package com.wezaam.withdrawal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wezaam.withdrawal.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
