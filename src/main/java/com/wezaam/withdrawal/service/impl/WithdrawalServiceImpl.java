package com.wezaam.withdrawal.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.wezaam.withdrawal.enu.WithdrawalStatus;
import com.wezaam.withdrawal.exception.TransactionException;
import com.wezaam.withdrawal.model.PaymentMethod;
import com.wezaam.withdrawal.model.Withdrawal;
import com.wezaam.withdrawal.model.WithdrawalScheduled;
import com.wezaam.withdrawal.repository.PaymentMethodRepository;
import com.wezaam.withdrawal.repository.WithdrawalRepository;
import com.wezaam.withdrawal.repository.WithdrawalScheduledRepository;
import com.wezaam.withdrawal.service.WithdrawalService;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {


	@Autowired
	private WithdrawalRepository withdrawalRepository;
	@Autowired
	private WithdrawalScheduledRepository withdrawalScheduledRepository;
	@Autowired
	private WithdrawalProcessingServiceImpl withdrawalProcessingService;
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;
	@Autowired
	private EventsServiceImpl eventsService;

	private Log logger = LogFactory.getLog(this.getClass());

	@Scheduled(fixedDelay = 5000)
	public void run() {
		logger.debug("--> run");
		withdrawalScheduledRepository.findAll();
		withdrawalScheduledRepository.findAllByExecuteAtBeforeAndStatus(Instant.now(), WithdrawalStatus.PENDING)
				.forEach(this::processScheduled);
		logger.debug("<-- run");
	}

	private void processScheduled(WithdrawalScheduled withdrawal) {
		logger.debug("--> processScheduled");
		this.create(withdrawal);
		logger.debug("<-- processScheduled");
	}

	@Override
	public Withdrawal create(Withdrawal withdrawal) {
		logger.debug("--> create");

		try {
			PaymentMethod paymentMethod = paymentMethodRepository.findById(withdrawal.getPaymentMethodId()).get();
			var transactionId = withdrawalProcessingService.sendToProcessing(withdrawal.getAmount(), paymentMethod);
			withdrawal.setStatus(WithdrawalStatus.PROCESSING);
			withdrawal.setTransactionId(transactionId);
			withdrawal = withdrawalRepository.save(withdrawal);
		} catch (TransactionException e) {
			withdrawal.setStatus(WithdrawalStatus.FAILED);
			withdrawalRepository.save(withdrawal);
		} catch (Exception e) {
			withdrawal.setStatus(WithdrawalStatus.INTERNAL_ERROR);
			withdrawalRepository.save(withdrawal);
		}
		eventsService.send(withdrawal);
		logger.debug("<-- create");
		return withdrawal;
	}

	@Override
	public WithdrawalScheduled createScheduled(WithdrawalScheduled withdrawalScheduled) {
		logger.debug("--> create");
		WithdrawalScheduled ret = withdrawalScheduledRepository.save(withdrawalScheduled);
		logger.debug("<-- create");
		return ret;
	}

	@Override
	public List<Withdrawal> findAll() {
		logger.debug("--> findAll");
		List<Withdrawal> ret = new ArrayList<>();
		ret.addAll(withdrawalRepository.findAll());
		ret.addAll(withdrawalScheduledRepository.findAll());
		logger.debug("<-- findAll");
		return ret;
	}

}
