package com.wezaam.withdrawal.rest;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wezaam.withdrawal.business.service.UserBusinessService;
import com.wezaam.withdrawal.dto.UserDTO;

import io.swagger.annotations.Api;

@Api
@RestController
public class UserController {

	@Autowired
	private UserBusinessService userBusinessService;

	private Log logger = LogFactory.getLog(this.getClass());

	@GetMapping("/find-all-users")
	public List<UserDTO> findAll() {
		logger.debug("--> findAll");
		List<UserDTO> ret = userBusinessService.findAll();
		logger.debug("<-- findAll");
		return ret;
	}

	@GetMapping("/find-user-by-id/{id}")
	public UserDTO findById(@PathVariable Long id) {
		logger.debug("--> findById");
		UserDTO ret = userBusinessService.findById(id);
		logger.debug("<-- findById");
		return ret;
	}
}
