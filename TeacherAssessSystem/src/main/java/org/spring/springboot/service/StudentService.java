package org.spring.springboot.service;

import java.util.List;
import java.util.Map;

import org.spring.springboot.dao.master.StudentDao;
import org.spring.springboot.domain.Student;
import org.spring.springboot.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentdao;
		
	public Student selectStudent(Map<String,Object> map){
		return studentdao.selectStudent(map);
	}
	
	public void insertStudent(Map<String,Object> map){
		studentdao.insertStudent(map);
	}
	
	public void updateStuPassward(Map<String,Object> map){
		studentdao.updateStuPassward(map);
	}
	
	public Student selectStudentMessage(Map<String,Object> map){
		return studentdao.selectStudentMessage(map);
	}
	
	public void updateStudentMessage(Student student){
		studentdao.updateStudentMessage(student);
	}
	
	/**
	 * 根据学号查询学生
	 * @param tea_no
	 * @return
	 */
	public Student selectStudentByNo(String stu_no){
		return studentdao.selectStudentByNo(stu_no);
	}
	
	/**
	 * 查找学生属性
	 * @return
	 */
	public List<String> findAllStudent(){
		return studentdao.findAllStudent();
	}
	
	/**
	 * 批量插入学生信息
	 * @param list
	 */
	public void batchInsertStu(List<Student> list){
		studentdao.batchInsertStu(list);
	}
	
	
	/**
	 * <!-- 查询需评价老师的人数 -->
	 * @param map
	 * @return
	 */
	public int selectAccessTeacherCount(String stu_no){
		return  studentdao.selectAccessTeacherCount(stu_no);
	}
}
