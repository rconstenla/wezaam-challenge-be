package com.wezaam.withdrawal.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wezaam.withdrawal.model.User;
import com.wezaam.withdrawal.repository.UserRepository;
import com.wezaam.withdrawal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserRepository userRepository;

	public User findById(Long id) {
		logger.debug("--> findById");
		User ret = null;
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			ret = optional.get();
		}
		logger.debug("<-- findById");
		return ret;
	}

	public List<User> findAll() {
		logger.debug("--> findAll");
		List<User> ret = userRepository.findAll();
		logger.debug("<-- findAll");
		return ret;
	}

}
