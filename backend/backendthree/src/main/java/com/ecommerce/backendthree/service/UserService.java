package com.ecommerce.backendthree.service;

import com.ecommerce.backendthree.entity.User;

public interface UserService {

	User loginUser(User user);

	User registerUser(User user);
	
	User validateUser(String token);

}
