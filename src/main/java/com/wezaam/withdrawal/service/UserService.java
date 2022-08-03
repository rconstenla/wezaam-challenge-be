package com.wezaam.withdrawal.service;

import java.util.List;

import com.wezaam.withdrawal.model.User;

public interface UserService {

	public User findById(Long id);

	public List<User> findAll();

}
