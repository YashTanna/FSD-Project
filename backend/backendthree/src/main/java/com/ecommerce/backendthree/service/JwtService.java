package com.ecommerce.backendthree.service;

import com.ecommerce.backendthree.entity.User;

public interface JwtService {

	String encode(User user);

	User decode(String token);

}
