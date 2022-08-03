package com.wezaam.withdrawal.business.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

public interface WithdrawalBusinessService {
	/**
	 * create a withdrawal
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEntity<Object> create(HttpServletRequest request);

	/**
	 * 
	 * find all withdrawals
	 * 
	 * @return
	 */

	public ResponseEntity<Object> findAll();

}
