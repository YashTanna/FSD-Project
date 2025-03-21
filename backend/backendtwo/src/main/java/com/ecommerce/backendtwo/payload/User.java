package com.ecommerce.backendtwo.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	private Integer userId;
	private String userName;
	private String email;
	private String password;
	private String token;

}
