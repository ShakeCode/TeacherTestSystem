package org.spring.springboot.dao.master;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.Student;

@Mapper
public interface StudentDao {
 
	/**查找是否存在该学生对象
	 * @param map
	 * @return
	 */
	public Student selectStudent(Map<String,Object> map);
		
	
	/**插入学生
	 * @param map
	 */
	public void insertStudent(Map<String,Object> map);
	
	/**
	 * 修改密码
	 * @param map
	 */
	public void updateStuPassward(Map<String,Object> map);
	
	/**
	 * 查找学生信息
	 * @param map
	 * @return
	 */
	public Student selectStudentMessage(Map<String,Object> map);
	
	/**
	 * 更新学生信息
	 * @param student
	 */
	public void updateStudentMessage(Student student);
	
	/**
	 * 查找学生属性
	 * @return
	 */
	public List<String> findAllStudent();
	
	/**
	 * 批量插入学生信息
	 * @param list
	 */
	public void batchInsertStu(List<Student> list);
	
	/**<!-- 查询需评价老师的人数 -->
	 * @param map
	 * @return
	 */
	public int selectAccessTeacherCount(@Param("stu_no")String stu_no);
	
	/**
	 * 根据学号查询学生
	 * @param stu_no
	 * @return
	 */
	public Student selectStudentByNo(@Param("stu_no")String stu_no);
}
