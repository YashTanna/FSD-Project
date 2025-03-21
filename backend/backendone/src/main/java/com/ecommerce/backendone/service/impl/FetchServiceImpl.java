package com.ecommerce.backendone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.backendone.exception.NotAuthorizedException;
import com.ecommerce.backendone.payload.User;
import com.ecommerce.backendone.service.FetchService;

@Service
public class FetchServiceImpl implements FetchService {
	@Autowired
	private RestTemplate restTemplate;
    @Value("${backend-three.api.url}")
    private String url;

    @Override
    public User getUser(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<User> response = restTemplate.exchange(url + "/users/validate", HttpMethod.GET, requestEntity, User.class, 0);
        if (!response.getStatusCode().is2xxSuccessful())
            throw new NotAuthorizedException("Invalid jwt");
        return response.getBody();
    }
}
