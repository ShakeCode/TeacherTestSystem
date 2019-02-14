package org.spring.springboot.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.dao.master.TeacherDao;
import org.spring.springboot.domain.Student;
import org.spring.springboot.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
	private TeacherDao teacherDao;
	
	public Teacher selectTeacher(Map<String,Object> map){
		return teacherDao.selectTeacher(map);
	}
	
	public void insertTeacher(Map<String,Object> map){
		teacherDao.insertTeacher(map);
	}
	
	public void updateTeaPassward(Map<String,Object> map){
		teacherDao.updateTeaPassward(map);
	}
	
	public Teacher selectTeaMessage(Map<String,Object> map){
		return  teacherDao.selectTeaMessage(map);
	}
	
	public void updateTeacherMessage(Teacher teacher){
		teacherDao.updateTeacherMessage(teacher);
	}
	
	/**
	 * 根据工号查询老师
	 * @param tea_no
	 * @return
	 */
	public Teacher selectTeacherByNo(String tea_no){
		return teacherDao.selectTeacherByNo(tea_no);
	}
	
	/**
	 * 批量插入老师基本信息
	 * @param list
	 */
	public void batchInsertTea(List<Teacher> list){
		teacherDao.batchInsertTea(list);
	}
	
	/**
	 * 查找需要评价的老师列表
	 * @param stu_no
	 * @return
	 */
	public List<Teacher> selectAccessTeacherList(String stu_no){
		return teacherDao.selectAccessTeacherList(stu_no);
	}
	
	/**
	 * <!-- 查找老师同行评价列表 -->
	 * @param map
	 * @return
	 */
	public List<Teacher> selectAccessSameFiledTeacherList(Map<String,Object> map){
		return teacherDao.selectAccessSameFiledTeacherList(map);
	}
	
	/**
	 * <!-- 查找老师同行评价人数 -->
	 * @param map
	 * @return
	 */
	public int selectAccessSameFiledTeacherCount(Map<String,Object> map){
		return teacherDao.selectAccessSameFiledTeacherCount(map);
	}
	
	/**
	 * <!-- 查找领导评价老师人数 -->
	 * @param map
	 * @return
	 */
	public int selectLeaderAssessTeacherCount(Map<String,Object> map){
		return teacherDao.selectLeaderAssessTeacherCount(map);
	}
	
	/**
	 * <!-- 查找领导评价老师列表 -->
	 * @param map
	 * @return
	 */
	public List<Teacher>selectLeaderAssessTeacherList(Map<String,Object> map){
		return teacherDao.selectLeaderAssessTeacherList(map);
	}

	/**
	 * 
	 * 查找所有老师
	 * @return
	 */
	public List<Teacher> selectAllTeacher(){
		return teacherDao.selectAllTeacher();
	}
}
