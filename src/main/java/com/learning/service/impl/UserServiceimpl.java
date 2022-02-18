package com.learning.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.Exception.AlreadyExistsException;
import com.learning.Exception.IdNotFoundException;
import com.learning.Exception.InvalidPasswordException;
import com.learning.entity.Login;
import com.learning.entity.Register;
import com.learning.repo.LoginRepository;
import com.learning.repo.UserRepository;
import com.learning.service.UserService;


@Service
public class UserServiceimpl implements UserService {
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private LoginServiceimpl service;
   @Autowired
   private LoginRepository loginRepository;
   public UserServiceimpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	
	
	//UserRepository userRepository ;
   @Override
	public String authenticate(String email, String password) {
		// TODO Auto-generated method stub
		boolean status = userRepository.existsByEmailAndPassword(email, password) ;
		if(status) {
			return "success";
		}
	
		return "fail"; 

	}
	
	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = AlreadyExistsException.class)
	public Register addUser(Register register) throws AlreadyExistsException{
		// TODO Auto-generated method stub
		
		boolean status=userRepository.existsByEmailAndPassword(register.getEmail(),register.getPassword() );
		System.out.println("status"+status);
		if(status) {
			throw new AlreadyExistsException("record already exists");
		}
		Register register2=userRepository.save(register);
		
		System.out.println(register2);
		if(register2==null) {
			System.out.println("fail");
		}
	    Login login = new Login(register2.getEmail(),register2.getPassword(),register2);
	    String result=service.addCredentials(login);
		System.out.println(result);
			//addCredential LoginService
			
		return register2;
		
	}
        

	@Override
	public Register updateUser(int id,Register register) throws IdNotFoundException {
		// TODO Auto-generated method stub
		if(!this.userRepository.existsById(id))
			throw new IdNotFoundException("invalid id");
		
		return userRepository.save(register);
		
	}

	@Override
	public Register getUserById(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
	  Optional<Register> optional=userRepository.findById(id);
	  if(optional==null) {
		  throw new IdNotFoundException("id does not exists");
	  }else {
		  return optional.get();
	  }
	}

	@Override
	public Register[] getAllUsers() throws InvalidPasswordException {
		// TODO Auto-generated method stub
		List<Register> list=userRepository.findAll();
		Register[] array=new Register[list.size()];
		return list.toArray(array);
	}

	@Override
	public String deleteUserById(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Register optional;
		try {
			 optional=this.getUserById(id);
			if(optional==null) {
				throw new IdNotFoundException("record not found");
			}else {
				userRepository.deleteById(id);
				return "deleted";
			}
			
		} catch (IdNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
		
		
	}

	@Override
	public Optional<List<Register>> getAllUserDetails() throws InvalidPasswordException {
		// TODO Auto-generated method stub
		return Optional.ofNullable(userRepository.findAll());
	}
	
}
