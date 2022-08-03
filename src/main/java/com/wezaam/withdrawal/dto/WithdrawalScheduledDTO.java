package com.wezaam.withdrawal.dto;

import java.time.Instant;

public class WithdrawalScheduledDTO extends WithdrawalDTO {

	private Instant executeAt;

	public Instant getExecuteAt() {
		return executeAt;
	}

	public void setExecuteAt(Instant executeAt) {
		this.executeAt = executeAt;
	}

}
