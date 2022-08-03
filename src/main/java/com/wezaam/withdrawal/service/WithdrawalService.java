package com.wezaam.withdrawal.service;

import java.util.List;

import com.wezaam.withdrawal.exception.TransactionException;
import com.wezaam.withdrawal.model.Withdrawal;
import com.wezaam.withdrawal.model.WithdrawalScheduled;

public interface WithdrawalService {


	public void run();

	public List<Withdrawal> findAll();

	Withdrawal create(Withdrawal withdrawal) throws TransactionException;

	WithdrawalScheduled createScheduled(WithdrawalScheduled withdrawal);

	
}
