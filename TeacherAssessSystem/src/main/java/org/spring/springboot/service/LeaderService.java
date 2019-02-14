package org.spring.springboot.service;

import java.util.Map;

import org.spring.springboot.dao.master.LeaderDao;
import org.spring.springboot.domain.Leader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaderService {
   @Autowired
	private LeaderDao leaderdao;
	
	public Leader selectLeader(Map<String,Object> map){
		return leaderdao.selectLeader(map);
	}
	
	public void insertLeader(Map<String,Object> map){
		leaderdao.insertLeader(map);
	}
	
	public void updateLeaPassward(Map<String,Object> map){
		leaderdao.updateLeaPassward(map);
	}
}
