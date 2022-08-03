package com.wezaam.withdrawal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wezaam.withdrawal.model.Withdrawal;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
}
