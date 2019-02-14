package org.spring.springboot.service;

import java.util.List;

import org.spring.springboot.dao.master.AssessTeacherResultDao;
import org.spring.springboot.domain.AssessTeacherResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessTeacherResultService {

	@Autowired
	private AssessTeacherResultDao assessTeacherResultDao;
	
	public int insertSelective(AssessTeacherResult record){
		return assessTeacherResultDao.insertSelective(record);
	}
	
	 /**
     * 根据老师工号查找评分结果
     * @param id
     * @return
     */
    public List<AssessTeacherResult>selectByTeaNo(String tea_no){
    	return assessTeacherResultDao.selectByTeaNo(tea_no);
    }
    
    /**
     * <!-- 查找所有老师的总评结果 -->
     * @return
     */
    public List<AssessTeacherResult> selectAllAssessResult(){
    	return assessTeacherResultDao.selectAllAssessResult();
    }
    
    /**
     * 删除所有查询结果
     */
    public void deleteAllResult(){
    	assessTeacherResultDao.deleteAllResult();	
    }
    
    
}
