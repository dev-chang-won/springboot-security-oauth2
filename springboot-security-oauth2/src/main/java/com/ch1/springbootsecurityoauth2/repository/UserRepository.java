package com.ch1.springbootsecurityoauth2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ch1.springbootsecurityoauth2.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsername(String username);

}
