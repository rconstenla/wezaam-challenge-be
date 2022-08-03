package com.wezaam.withdrawal.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wezaam.withdrawal.enu.WithdrawalStatus;
import com.wezaam.withdrawal.model.WithdrawalScheduled;

public interface WithdrawalScheduledRepository extends JpaRepository<WithdrawalScheduled, Long> {

    List<WithdrawalScheduled> findAllByExecuteAtBeforeAndStatus(Instant date, WithdrawalStatus status);
}
