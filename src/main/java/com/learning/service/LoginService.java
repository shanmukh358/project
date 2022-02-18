package com.learning.service;

import com.learning.entity.Login;

public interface LoginService {
	public String addCredentials(Login login);

	public String deleteCredentials(String userName);

	public String changePassword(String userName,String password);
	
}
