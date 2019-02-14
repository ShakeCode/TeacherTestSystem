package org.spring.springboot.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.dao.master.QuestionAnswerDao;
import org.spring.springboot.domain.QuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionAnswerService {
	
	@Autowired
	private QuestionAnswerDao questionAnswerDao;
	
	public int insertSelective(QuestionAnswer questionAnswer){
		return questionAnswerDao.insertSelective(questionAnswer);
	}
	
	/**
	 *  <!-- 查找学生评价的列表 -->
	 * @param tea_id
	 * @return
	 */
	public List<QuestionAnswer> selectByTeaId(Integer tea_id){
		return questionAnswerDao.selectByTeaId(tea_id);
	}
	
	/**
	 *  <!-- 查找同行评价的老师列表 -->
	 * @param tea_id
	 * @return
	 */
	public List<QuestionAnswer> selectSameFiledTeacherInfoList(Integer tea_id){
		return questionAnswerDao.selectSameFiledTeacherInfoList(tea_id);
	}
	
	/**
	 * <!-- 查找领导评价的列表 -->
	 * @param tea_id
	 * @return
	 */
	public List<QuestionAnswer> selectLeaderAccessInfoList(Integer tea_id){
		return questionAnswerDao.selectLeaderAccessInfoList(tea_id);
	}
	
	/**
	 * <!-- 计算评价总分-->
	 * @param tea_id
	 * @return
	 */
	public  List<QuestionAnswer> selectAsscessAllPeopleAnswerList(Integer tea_id){
		return questionAnswerDao.selectAsscessAllPeopleAnswerList(tea_id);
	}
	
	/**
	 * <!-- 计算评价总人数 -->
	 * @param tea_id
	 * @return
	 */
	public int selectAsscessAllPeopleAnswerCount(Integer tea_id){
		return questionAnswerDao.selectAsscessAllPeopleAnswerCount(tea_id);
	}
	
	/**
	 * <!-- 计算专家评价总人数 -->
	 * @param tea_id
	 * @return
	 */
	public int selectCountAssessByLeader(@Param("tea_id") Integer tea_id){
		return questionAnswerDao.selectCountAssessByLeader(tea_id);
	}
	
	/**
	 * <!-- 计算同行评价总人数 -->
	 * @param tea_id
	 * @return
	 */
	public int selectAssessCountBySameFiledTeacher(@Param("tea_id") Integer tea_id){
		return questionAnswerDao.selectAssessCountBySameFiledTeacher(tea_id);
	}
	
	/**
	 * <!-- 计算学生评价总人数 -->
	 * @param tea_id
	 * @return
	 */
	public int selectAssessCountBySameFiledStudent(@Param("tea_id") Integer tea_id){
		return questionAnswerDao.selectAssessCountBySameFiledStudent(tea_id);
	}
	
	
	/**
	 * <!-- 计算专家评价总分-->
	 * @param tea_id
	 * @return
	 */
	public  List<QuestionAnswer> selectAssessListByLeader(@Param("tea_id") Integer tea_id){
		return questionAnswerDao.selectAssessListByLeader(tea_id);
	}
	
	/**
	 * <!-- 计算同行评价总分-->
	 * @param tea_id
	 * @return
	 */
	public  List<QuestionAnswer> selectAssessListBySameFiledTeacher(@Param("tea_id") Integer tea_id){
		return questionAnswerDao.selectAssessListBySameFiledTeacher(tea_id);
	}
	
	/**
	 * <!-- 计算学生评价总分-->
	 * @param tea_id
	 * @return
	 */
	public  List<QuestionAnswer> selectAssessListBySameFiledStudent(@Param("tea_id") Integer tea_id){
		return questionAnswerDao.selectAssessListBySameFiledStudent(tea_id);
	}
	
	/**
	 * 查找某个老师是否被评价
	 * @param tea_id
	 * @return
	 */
	public List<QuestionAnswer> isExistInAssessQuestion(Integer tea_id){
		return questionAnswerDao.isExistInAssessQuestion(tea_id);
	}
}
