package com.wezaam.withdrawal.model;

import java.time.Instant;

import javax.persistence.Entity;

@Entity(name = "scheduled_withdrawals")
public class WithdrawalScheduled extends Withdrawal {

	private Instant executeAt;

	public Instant getExecuteAt() {
		return executeAt;
	}

	public void setExecuteAt(Instant executeAt) {
		this.executeAt = executeAt;
	}

}
