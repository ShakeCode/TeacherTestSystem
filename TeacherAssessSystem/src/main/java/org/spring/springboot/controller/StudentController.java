package org.spring.springboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.springboot.domain.QuestionAnswer;
import org.spring.springboot.domain.Student;
import org.spring.springboot.domain.Teacher;
import org.spring.springboot.service.QuestionAnswerService;
import org.spring.springboot.service.StudentService;
import org.spring.springboot.service.TeacherService;
import org.spring.springboot.util.AjaxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;

/**
 * @author lijun
 *
 */
@Controller
@Api("StudentController相关api")
public class StudentController extends BaseController{
   
	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private QuestionAnswerService questionAnswerService;
	
	
	@RequestMapping(value="/stu_changePassward") 
	public String changePassward(String stu_no){
		request.setAttribute("stu_no", stu_no);
		
		return "stu_change_passward";
	}
	
	/**
	 * 验证密码输入正确
	 * @param stu_no
	 * @param passward
	 * @return
	 */
	@RequestMapping(value="/check_stuPassward") 
	public String checkStuPassward(String stu_no,String passward){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("stu_no", stu_no);
		map.put("passward", passward);
		map.put("role_id", "2");
		Student student = null;
		try {
			student = studentService.selectStudent(map);
			if(student != null){
				AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS);
			}else{
				AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/stu_changePass") 
	public String changePass(String stu_no,String newPassward){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("stu_no", stu_no);
		map.put("newPassward", newPassward);
		
		try {
			studentService.updateStuPassward(map);
			AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS);
		} catch (Exception e) {
			AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
			e.printStackTrace();
		}
		/*request.setAttribute("stu_no", stu_no);
		return "stu_change_passward";*/
		return null;
	}
	
	@RequestMapping(value="/stu_message") 
	public String studentMessage(String stu_no){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("stu_no", stu_no);
		Student student = null;
		
		try {
			student = studentService.selectStudentMessage(map);
			Date date = new Date();
			SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
			String enterTime = formate.format(student.getEnterTime()!= null ?student.getEnterTime():new Date() );
			request.setAttribute("student",student);
			request.setAttribute("enterTime",enterTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "studentMessage";
	}
	
	//编辑学生信息
	@RequestMapping(value="/editStudent",method=RequestMethod.GET) 
	public String editStudent(String stu_no){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("stu_no", stu_no);
		Student student = null;
		
		try {
			student = studentService.selectStudentMessage(map);
			Date date = new Date();
			SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
			String enterTime = formate.format(student.getEnterTime()!= null ? student.getEnterTime() : new Date());
			request.setAttribute("enterTime",enterTime);
			request.setAttribute("student",student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "student_modify_message";
	}

	@PostMapping(value="/updateStudent") 
	public String updateStudent(String enterTime){
		Student student = new Student();  
		ServletRequestDataBinder binder =   
		    new ServletRequestDataBinder(student);  
		binder.bind(request);  
		try {
			Date enterDate = new SimpleDateFormat("yyyy-MM-dd").parse(enterTime);
			student.setEnterTime(enterDate);
			//执行更新
			studentService.updateStudentMessage(student);
//			AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS);
		} catch (ParseException e) {
			e.printStackTrace();
			return "error";
//			AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
		}
		
//		return "stu_change_passward";
		return "success";
	}
	
 
	@GetMapping(value="/access/Teacher/list") 
	public String accessTeacherList(String stu_no){
		List<Teacher> accessList = new ArrayList<Teacher>();
		
		try {
			accessList = teacherService.selectAccessTeacherList(stu_no);
			request.setAttribute("accessList", accessList);
			request.setAttribute("stu_no",stu_no);
		} catch (Exception e){
			e.printStackTrace();
		}
		return "accsessTeacher_table";
	}
	
	/**
	 * 评价老师
	 * @param stu_no
	 * @param tea_no
	 * @return
	 */
	@RequestMapping(value="/acessTeacher") 
	public String acessTeacher(String stu_no,String tea_no){
		Teacher teacher = teacherService.selectTeacherByNo(tea_no);
		request.setAttribute("tea_name", teacher.getTea_name());
		request.setAttribute("tea", teacher);
		request.setAttribute("stu_no", stu_no);
		return "stu_accessTeacher";
	}
	
	/**
	 * 保存评价老师信息
	 * @param stu_no
	 * @param tea_no
	 * @param answer
	 * @return
	 */
	@RequestMapping(value="/save/AcessTeacher/Answer",method=RequestMethod.POST)
	public String saveAcessTeacherAnswer(String stu_no,String tea_id,String answer,String advise){
		Student student = studentService.selectStudentByNo(stu_no);
		QuestionAnswer questionAnswer = new QuestionAnswer();
		try {
			questionAnswer.setStuId(student.getStu_id());
			questionAnswer.setTeaId(Integer.valueOf(tea_id));
			questionAnswer.setAdvisement(advise);
			questionAnswer.setAnswer(answer);
			questionAnswerService.insertSelective(questionAnswer);
			AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
		}
		return "success";
	}
	
}
