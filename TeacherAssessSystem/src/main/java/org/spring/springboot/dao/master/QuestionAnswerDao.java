package org.spring.springboot.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.QuestionAnswer;

@Mapper
public interface QuestionAnswerDao {
 
	public int deleteByPrimaryKey(Integer id);

	public int insertSelective(QuestionAnswer questionAnswer);

	/**
	 * 查找某个老师是否被评价
	 * @param tea_id
	 * @return
	 */
	public List<QuestionAnswer> isExistInAssessQuestion(@Param("tea_id") Integer tea_id);
	/**
	 * <!-- 查找学生评价的列表 -->
	 * @param tea_id
	 * @return
	 */
	public List<QuestionAnswer> selectByTeaId(Integer tea_id);
	
	/**
	 *  <!-- 查找同行评价的老师列表 -->
	 * @param tea_id
	 * @return
	 */
	public List<QuestionAnswer> selectSameFiledTeacherInfoList(Integer tea_id);
	
	/**
	 * <!-- 查找领导评价的列表 -->
	 * @param tea_id
	 * @return
	 */
	public List<QuestionAnswer> selectLeaderAccessInfoList(Integer tea_id);

	public int updateByPrimaryKeySelective(QuestionAnswer questionAnswer);

	public int updateByPrimaryKey(QuestionAnswer questionAnswer);
	
	/**
	 * <!-- 计算评价总分-->
	 * @param tea_id
	 * @return
	 */
	public  List<QuestionAnswer> selectAsscessAllPeopleAnswerList(@Param("tea_id") Integer tea_id);
	
	/**
	 * <!-- 计算评价总人数 -->
	 * @param tea_id
	 * @return
	 */
	public int selectAsscessAllPeopleAnswerCount(@Param("tea_id") Integer tea_id);
	
	/**
	 * <!-- 计算专家评价总人数 -->
	 * @param tea_id
	 * @return
	 */
	public int selectCountAssessByLeader(@Param("tea_id") Integer tea_id);
	
	/**
	 * <!-- 计算同行评价总人数 -->
	 * @param tea_id
	 * @return
	 */
	public int selectAssessCountBySameFiledTeacher(@Param("tea_id") Integer tea_id);
	
	/**
	 * <!-- 计算学生评价总人数 -->
	 * @param tea_id
	 * @return
	 */
	public int selectAssessCountBySameFiledStudent(@Param("tea_id") Integer tea_id);
	
	
	/**
	 * <!-- 计算专家评价总分-->
	 * @param tea_id
	 * @return
	 */
	public  List<QuestionAnswer> selectAssessListByLeader(@Param("tea_id") Integer tea_id);
	
	/**
	 * <!-- 计算同行评价总分-->
	 * @param tea_id
	 * @return
	 */
	public  List<QuestionAnswer> selectAssessListBySameFiledTeacher(@Param("tea_id") Integer tea_id);
	
	/**
	 * <!-- 计算学生评价总分-->
	 * @param tea_id
	 * @return
	 */
	public  List<QuestionAnswer> selectAssessListBySameFiledStudent(@Param("tea_id") Integer tea_id);
	
	
	
	
}