package com.learning.service;

import com.learning.entity.Role;

public interface RoleService {
  public String addRole(Role role);
	
	public void deleteRole(int roleId);
}
