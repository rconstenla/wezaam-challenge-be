package com.wezaam.withdrawal.rest;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wezaam.withdrawal.business.service.WithdrawalBusinessService;

import io.swagger.annotations.Api;

@Api
@RestController
public class WithdrawalController {

	@Autowired
	private WithdrawalBusinessService withdrawalBusinessService;

	private Log logger = LogFactory.getLog(this.getClass());

	@PostMapping("/create-withdrawals")
	public ResponseEntity<Object> create(HttpServletRequest request) {
		logger.debug("--> create");
		ResponseEntity<Object> ret = withdrawalBusinessService.create(request);
		logger.debug("<-- create");
		return ret;
	}

	@GetMapping("/find-all-withdrawals")
	public ResponseEntity<Object> findAll() {
		logger.debug("--> findAll");
		ResponseEntity<Object> ret = withdrawalBusinessService.findAll();
		logger.debug("<-- findAll");
		return ret;
	}
}
