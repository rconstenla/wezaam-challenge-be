package com.wezaam.withdrawal.business.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.dozermapper.core.Mapper;
import com.wezaam.withdrawal.business.service.UserBusinessService;
import com.wezaam.withdrawal.business.service.WithdrawalBusinessService;
import com.wezaam.withdrawal.dto.UserDTO;
import com.wezaam.withdrawal.dto.WithdrawalDTO;
import com.wezaam.withdrawal.dto.WithdrawalScheduledDTO;
import com.wezaam.withdrawal.enu.WithdrawalStatus;
import com.wezaam.withdrawal.exception.TransactionException;
import com.wezaam.withdrawal.model.Withdrawal;
import com.wezaam.withdrawal.model.WithdrawalScheduled;
import com.wezaam.withdrawal.service.WithdrawalService;

@Service
public class WithdrawalBusinessServiceImpl implements WithdrawalBusinessService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private Mapper mapper;
	
	@Autowired
	private UserBusinessService userBusinessService;

	@Autowired
	private WithdrawalService withdrawalService;

	private static String ASAP = "ASAP";

	@Override
	public ResponseEntity<Object> create(HttpServletRequest request) {
		logger.debug("--> create");
		try {
			WithdrawalDTO withdrawalDTO = createRequest(request);
			UserDTO user = userBusinessService.findById(withdrawalDTO.getUserId());

			if (user == null) {
				throw new TransactionException("User not found", HttpStatus.NOT_FOUND);
			}
			boolean isValidPaymentMethod = !user.getPaymentMethods().stream()
					.filter(p -> p.getId().equals(withdrawalDTO.getPaymentMethodId())).collect(Collectors.toList())
					.isEmpty();

			if (!isValidPaymentMethod) {
				throw new TransactionException("Payment method not found", HttpStatus.NOT_FOUND);
			}
			
			WithdrawalDTO ret; 
			if (withdrawalDTO instanceof WithdrawalScheduledDTO) {
				WithdrawalScheduled withdrawalScheduled = mapper.map((WithdrawalScheduledDTO)withdrawalDTO, WithdrawalScheduled.class);
				withdrawalScheduled = (WithdrawalScheduled) withdrawalService.createScheduled(withdrawalScheduled);
				ret = mapper.map(withdrawalScheduled, WithdrawalScheduledDTO.class);

			}else {
				Withdrawal withdrawal = mapper.map(withdrawalDTO, Withdrawal.class);
				withdrawal = withdrawalService.create(withdrawal);
				ret = mapper.map(withdrawal, WithdrawalScheduledDTO.class);

			}
			
			logger.debug("<-- create");
			return new ResponseEntity<Object>(ret, HttpStatus.OK);
		} catch (TransactionException e) {
			logger.debug("TransactionException", e);
			return new ResponseEntity<Object>(e.getMessage(), e.getHttpStatus());
		} catch (Exception e) {
			logger.debug("Exception", e);
			return new ResponseEntity<Object>("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private WithdrawalDTO createRequest(HttpServletRequest request) throws TransactionException {
		logger.debug("--> createRequest");

		try {
			String executeAt = request.getParameter("executeAt");
			if (!ASAP.equals(executeAt)) {
				WithdrawalScheduledDTO ret = new WithdrawalScheduledDTO();
				ret.setExecuteAt(Instant.parse(executeAt));
				ret.setUserId((Long.parseLong(request.getParameter("userId"))));
				ret.setPaymentMethodId((Long.parseLong(request.getParameter("paymentMethodId"))));
				ret.setAmount((Double.parseDouble(request.getParameter("amount"))));
				ret.setStatus(WithdrawalStatus.PENDING);
				logger.debug("<-- createRequest");
				return ret;
			} else {
				WithdrawalDTO ret = new WithdrawalDTO();
				ret.setUserId((Long.parseLong(request.getParameter("userId"))));
				ret.setPaymentMethodId((Long.parseLong(request.getParameter("paymentMethodId"))));
				ret.setAmount((Double.parseDouble(request.getParameter("amount"))));
				logger.debug("<-- createRequest");
				return ret;
			}

		} catch (Exception e) {
			logger.debug("Exception", e);
			throw new TransactionException("Invalid request", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> findAll() {
		logger.debug("--> findAll");
		List<Withdrawal> withdrawals = withdrawalService.findAll();
		logger.debug("<-- findAll");
		return new ResponseEntity<Object>(withdrawals, HttpStatus.OK);
	}

}
