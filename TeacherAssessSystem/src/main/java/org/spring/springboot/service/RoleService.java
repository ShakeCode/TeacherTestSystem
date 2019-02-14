package org.spring.springboot.service;

import org.spring.springboot.dao.master.RoleDao;
import org.spring.springboot.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
   
	@Autowired
	private RoleDao roleDao;
	
	public Role selectRoleName( String role_id){
		return roleDao.selectRoleName(role_id);
	}
}
