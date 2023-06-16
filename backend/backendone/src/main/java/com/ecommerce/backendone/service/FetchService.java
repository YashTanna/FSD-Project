package com.ecommerce.backendone.service;

import com.ecommerce.backendone.payload.User;

public interface FetchService {
	
	User getUser(String token);
}
