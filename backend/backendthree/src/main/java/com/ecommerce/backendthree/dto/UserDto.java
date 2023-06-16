package com.ecommerce.backendthree.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

	private Integer userId;

	@NotBlank(message = "Username is required", groups = { Register.class })
	@Pattern(regexp = "^[A-Za-z0-9_]*$", message = "Username contains invalid characters", groups = { Register.class })
	private String userName;

	@NotBlank(message = "Email is required", groups = { Register.class, Login.class })
	@Email(message = "Email is invalid", groups = { Register.class, Login.class })
	private String email;

	@NotBlank(message = "Password is required", groups = { Register.class, Login.class })
	@Size(min = 4, message = "Password must contain atleast 4 characters", groups = { Register.class, Login.class })
	private String password;

	private String token;

	public interface Login {
	}

	public interface Register {
	}

}
