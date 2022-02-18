package com.learning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.entity.Role;
import com.learning.repo.RoleRepository;
import com.learning.service.RoleService;


@Service
public class RoleServiceimpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addRole(Role role) {
		// TODO Auto-generated method stub
		Role role2=roleRepository.save(role);
		if(role2!=null) {
			return "role added";
		}
		return "fail";
	}

	@Override
	public void deleteRole(int roleId) {
		// TODO Auto-generated method stub
		
	}

}
