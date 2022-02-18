package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.Exception.AlreadyExistsException;
import com.learning.Exception.IdNotFoundException;
import com.learning.Exception.InvalidPasswordException;
import com.learning.entity.Register;


public interface UserService {
	public Register addUser(Register register) throws AlreadyExistsException;
	public Register updateUser(int id,Register register) throws IdNotFoundException;
	public Register[] getAllUsers() throws InvalidPasswordException;
	public String deleteUserById(int id) throws IdNotFoundException;
	Optional<List<Register>> getAllUserDetails() throws InvalidPasswordException;
	public Register getUserById(int id) throws IdNotFoundException;
	public String authenticate(String email, String password);
}
