package com.wezaam.withdrawal.business.service;

import java.util.List;

import com.wezaam.withdrawal.dto.UserDTO;

public interface UserBusinessService {

	/**
	 * find user by id
	 * @return
	 */
	public UserDTO findById(Long id);

	/**
	 * find all users
	 * @return
	 */
	public List<UserDTO> findAll();

}
