package com.wezaam.withdrawal.service;

import com.wezaam.withdrawal.model.Withdrawal;
import com.wezaam.withdrawal.model.WithdrawalScheduled;

public interface EventsService {

	public void send(Withdrawal withdrawal);

	public void send(WithdrawalScheduled withdrawal);

}
