package com.wezaam.withdrawal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.wezaam.withdrawal.model.Withdrawal;
import com.wezaam.withdrawal.model.WithdrawalScheduled;
import com.wezaam.withdrawal.repository.WithdrawalRepository;
import com.wezaam.withdrawal.service.EventsService;

@Service
public class EventsServiceImpl implements EventsService {

	@Autowired
	private WithdrawalRepository withdrawalRepository;
	
    @Async
    public void send(Withdrawal withdrawal) {
        // build and send an event in message queue async
    	
    	// if send OK 
    	withdrawal.setNotyfy(true);
		withdrawalRepository.save(withdrawal);
    }

    @Async
    public void send(WithdrawalScheduled withdrawal) {
        // build and send an event in message queue async
    	
    	// if send OK 
    	withdrawal.setNotyfy(true);
		withdrawalRepository.save(withdrawal);
    }
}
