package com.learning.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import lombok.Data;

@Data
public class SignupRequest {

	  @NotBlank
	  
	  private String username;
        
		
	  @NotBlank
	  @Email
	  private String email;
	  
	  @NotBlank
	  private String address;
	  
	  @NotBlank
	  
	  private String password;
	  
	  

	  private Set<String> role;

	 
}
