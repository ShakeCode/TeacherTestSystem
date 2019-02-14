package org.spring.springboot.dao.master;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.Role;

@Mapper
public interface RoleDao {
 
	/** 获取登录用户的角色名
	 * @param role_id
	 * @return
	 */
	public Role selectRoleName(@Param("role_id") String role_id);
		
}
