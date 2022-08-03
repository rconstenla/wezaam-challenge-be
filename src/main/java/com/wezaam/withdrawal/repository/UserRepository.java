package com.wezaam.withdrawal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wezaam.withdrawal.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
