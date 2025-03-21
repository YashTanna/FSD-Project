package com.ecommerce.backendthree.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.backendthree.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
}
