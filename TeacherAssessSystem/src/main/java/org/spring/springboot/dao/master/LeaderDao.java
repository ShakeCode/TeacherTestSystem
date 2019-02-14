package org.spring.springboot.dao.master;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.spring.springboot.domain.Leader;

@Mapper
public interface LeaderDao {
  
	/**查找是否存在该领导对象
	 * @param map
	 * @return
	 */
	public Leader selectLeader(Map<String,Object> map);
	
	
	/**插入领导
	 * @param map
	 */
	public void insertLeader(Map<String,Object> map);
	
	/**
	 * 修改密码
	 * @param map
	 */
	public void updateLeaPassward(Map<String,Object> map);
}
