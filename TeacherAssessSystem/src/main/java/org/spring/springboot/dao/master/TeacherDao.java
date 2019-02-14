package org.spring.springboot.dao.master;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.Teacher;

/**
 * @author lijun
 *
 */
@Mapper
public interface TeacherDao {

	/**
	 * @param map
	 * @return
	 */
	public Teacher selectTeacher(Map<String,Object> map);
	
	
	/**插入老师
	 * @param map
	 */
	public void insertTeacher(Map<String,Object> map);
	
	/**
	 * 修改密码
	 * @param map
	 */
	public void updateTeaPassward(Map<String,Object> map);
	
	/**
	 * 查找教师信息
	 * @param map
	 * @return
	 */
	public Teacher selectTeaMessage(Map<String,Object> map);
		
	
	/**
	 * 更新教师信息
	 * @param teacher
	 */
	public void updateTeacherMessage(Teacher teacher);

	
	/**
	 * 批量插入老师基本信息
	 * @param list
	 */
	public void batchInsertTea(List<Teacher> list);
	
	/**
	 * 查找需要评价的老师列表
	 * @param stu_no
	 * @return
	 */
	public List<Teacher> selectAccessTeacherList(@Param("stu_no")String stu_no);
		
	/**
	 * 根据工号查询老师
	 * @param tea_no
	 * @return
	 */
	public Teacher selectTeacherByNo(@Param("tea_no")String tea_no);
	
	/**
	 * 查找所有老师
	 * @return
	 */
	public List<Teacher> selectAllTeacher();
	
	/**
	 * <!-- 查找老师同行评价列表 -->
	 * @param map
	 * @return
	 */
	public List<Teacher> selectAccessSameFiledTeacherList(Map<String,Object> map);
	
	/**
	 * <!-- 查找老师同行评价人数 -->
	 * @param map
	 * @return
	 */
	public int selectAccessSameFiledTeacherCount(Map<String,Object> map);
	
	/**
	 * <!-- 查找领导评价老师人数 -->
	 * @param map
	 * @return
	 */
	public int selectLeaderAssessTeacherCount(Map<String,Object> map);
	
	/**
	 * <!-- 查找领导评价老师列表 -->
	 * @param map
	 * @return
	 */
	public List<Teacher>selectLeaderAssessTeacherList(Map<String,Object> map);
}
