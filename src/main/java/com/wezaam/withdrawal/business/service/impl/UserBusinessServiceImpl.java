package com.wezaam.withdrawal.business.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dozermapper.core.Mapper;
import com.wezaam.withdrawal.business.service.UserBusinessService;
import com.wezaam.withdrawal.dto.UserDTO;
import com.wezaam.withdrawal.model.User;
import com.wezaam.withdrawal.service.UserService;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private Mapper mapper;

	@Override
	public UserDTO findById(Long id)  {
		logger.debug("--> findById");
		UserDTO ret = null;
		User user = userService.findById(id);
		if (user != null) {
			ret = mapper.map(user, UserDTO.class);
		}
		logger.debug("<-- findById");
		return ret;
	}

	@Override
	public List<UserDTO> findAll() {
		logger.debug("--> findAll");
		List<UserDTO> ret = null;
		List<User> users = userService.findAll();
		if (users != null) {
			ret = users.stream().map(from -> mapper.map(from, UserDTO.class)).collect(Collectors.toList());
		}
		logger.debug("<-- findAll");
		return ret;
	}

	

}
