package org.spring.springboot.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.springboot.domain.AssessTeacherResult;
import org.spring.springboot.domain.QuestionAnswer;
import org.spring.springboot.domain.Teacher;
import org.spring.springboot.service.AssessTeacherResultService;
import org.spring.springboot.service.QuestionAnswerService;
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
@Api("TeacherController相关api")
public class TeacherController extends BaseController{
	@Autowired
	private QuestionAnswerService questionAnswerService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private AssessTeacherResultService assessTeacherResultService;
	
	@RequestMapping(value="/tea_acessTeacher") 
	public String acessTeacher(String stu_id){
		
		return "tea_accessTeacher";
	}
	
	@RequestMapping(value="/tea_changePassward") 
	public String changePassward(String tea_no){
		request.setAttribute("tea_no", tea_no);
		return "tea_change_passward";
	}
	
	/**
	 * 验证密码输入正确
	 * @param stu_no
	 * @param passward
	 * @return
	 */
	@RequestMapping(value="/check_teaPassward") 
	public String checkTeaPassward(String tea_no,String passward){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tea_no", tea_no);
		map.put("passward", passward);
		map.put("role_id", "3");
		Teacher teacher = null;
		try {
			teacher = teacherService.selectTeacher(map);
			if(teacher != null){
				AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS);
			}else{
				AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/tea_changePass") 
	public String changePass(String tea_no,String newPassward){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tea_no", tea_no);
		map.put("newPassward", newPassward);
		
		try {
			teacherService.updateTeaPassward(map);
			AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS);
		} catch (Exception e) {
			AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/tea_message",method=RequestMethod.GET) 
	public String tea_message(String tea_no){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tea_no", tea_no);
		Teacher teacher = null;
		
		try {
			teacher = teacherService.selectTeaMessage(map);
			request.setAttribute("teacher",teacher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "teachertMessage";
	}
	
	//编辑教师信息
	@RequestMapping(value="/editTeacher",method=RequestMethod.GET) 
	public String editStudent(String tea_no){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tea_no", tea_no);
		Teacher teacher = null;
		
		try {
			teacher = teacherService.selectTeaMessage(map);
			request.setAttribute("teacher",teacher);
			Date date = new Date();
			SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
			String workTime = formate.format(teacher.getWorkTime()!= null ? teacher.getWorkTime():new Date());
			request.setAttribute("workTime",workTime);
			String birthday = formate.format(teacher.getBirthday()!= null?teacher.getBirthday():new Date());
			request.setAttribute("birthday",birthday);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "teacher_modify_message";
	}
	
	@PostMapping(value="/updateTeacher") 
	public String updateTeacher(String birthday,String workTime){
		Teacher teacher = new Teacher();  
		ServletRequestDataBinder binder =   
		    new ServletRequestDataBinder(teacher );  
		binder.bind(request);  
		try {
			SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = dateFormate.parse(birthday);
			Date workDate = dateFormate.parse(workTime);
			teacher.setBirthday(birthDate);
			teacher.setWorkTime(workDate);
			//执行更新
			teacherService.updateTeacherMessage(teacher);
//			AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS);
		} catch (ParseException e) {
			e.printStackTrace();
			return "error";
//			AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
		}
		
//		return "stu_change_passward";
		return "success";
	}
	
	/**
	 * 查看评价结果
	 * @param tea_no
	 * @return
	 */
	@RequestMapping(value="/select/Assess/list") 
	public String selectAssessList(String tea_no){
		List<QuestionAnswer> list = new ArrayList<QuestionAnswer>();
		List<QuestionAnswer> list1 = new ArrayList<QuestionAnswer>();
		List<QuestionAnswer> list2 = new ArrayList<QuestionAnswer>();
		
		List<AssessTeacherResult> AssessList = new ArrayList<AssessTeacherResult>();
		try {
			Teacher teacher = teacherService.selectTeacherByNo(tea_no);
			list = questionAnswerService.selectByTeaId(teacher.getTea_id());
			list1 = questionAnswerService.selectSameFiledTeacherInfoList(teacher.getTea_id());
			list2 = questionAnswerService.selectLeaderAccessInfoList(teacher.getTea_id());
			
			AssessList = assessTeacherResultService.selectByTeaNo(tea_no);
			request.setAttribute("assessList", list);
			request.setAttribute("tea_assessList", list1);
			request.setAttribute("lea_assessList", list2);
			request.setAttribute("AllAssessList", AssessList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "assess_list";
	}

	/**
	 * 转到老师自评页面
	 * @param tea_no
	 * @return
	 */
	@RequestMapping(value="/teacher/self/Assess") 
	public String selfAssess(String tea_no){
		request.setAttribute("tea_no", tea_no);
		return "tea_self_Assess";
	}
	
	@PostMapping(value="/teacher/self/Assess/save") 
	public String saveSelfAssess(String tea_no,String answer){
		try {
			Teacher teacher = teacherService.selectTeacherByNo(tea_no);
			QuestionAnswer questionAnswer = new QuestionAnswer();
			questionAnswer.setAnswer(answer);
			questionAnswer.setTeaId(teacher.getTea_id());
			questionAnswerService.insertSelective(questionAnswer);
			AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
		}
		return null;
	}
	

	@GetMapping(value="/access/sameFiled/Teacher/list") 
	public String accessTeacherList(String tea_no){
		List<Teacher> accessList = new ArrayList<Teacher>();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Teacher teacher = teacherService.selectTeacherByNo(tea_no);
			map.put("tea_no", teacher.getTea_no());
			map.put("cour_id", teacher.getClass_id());
			map.put("tea_id", teacher.getTea_id());
			accessList = teacherService.selectAccessSameFiledTeacherList(map);
			request.setAttribute("accessList", accessList);
			request.setAttribute("tea_no",tea_no);
		} catch (Exception e){
			e.printStackTrace();
		}
		return "tea_sameFiled_assess_table";
	}
	
	
	/**
	 * 评价同行老师
	 * @param accsess_tea_no  被评价老师工号
	 * @param tea_no    评价老师的工号
	 * @return
	 */
	@RequestMapping(value="/acess/sameFiled/Teacher") 
	public String acessTeacher(String accsess_tea_no,String tea_no){
		Teacher teacher = teacherService.selectTeacherByNo(accsess_tea_no);
		request.setAttribute("tea_name", teacher.getTea_name());
		request.setAttribute("tea", teacher);
		request.setAttribute("tea_no", tea_no);
		return "tea_access_sameFiled_Teacher";
	}
	
	/**
	 * 保存评价同价老师信息
	 * @param stu_no
	 * @param tea_no
	 * @param answer
	 * @return
	 */
	@RequestMapping(value="/save/AcessSameFieled/Teacher/Answer",method=RequestMethod.POST)
	public String saveAcessTeacherAnswer(String tea_no,String access_tea_no,String answer,String advise){
		QuestionAnswer questionAnswer = new QuestionAnswer();
		Teacher assessTeacher = teacherService.selectTeacherByNo(tea_no); //评价老师
		Teacher assessDesTeacher = teacherService.selectTeacherByNo(access_tea_no); //被评价的老师
		try {
			questionAnswer.setTeaId(assessDesTeacher.getTea_id());
			questionAnswer.setTeaAnId(assessTeacher.getTea_id());
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
