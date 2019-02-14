package org.spring.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.springboot.domain.Leader;
import org.spring.springboot.domain.QuestionAnswer;
import org.spring.springboot.domain.Teacher;
import org.spring.springboot.service.LeaderService;
import org.spring.springboot.service.QuestionAnswerService;
import org.spring.springboot.service.TeacherService;
import org.spring.springboot.util.AjaxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;

/**
 * @author lijun
 *
 */
@Controller
@Api("LeaderController相关api")
public class LeaderController extends BaseController{

	@Autowired
	private QuestionAnswerService questionAnswerService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private LeaderService leaderService;
	
	@RequestMapping(value="/leader_acessTeacher") 
	public String acessTeacher(String stu_id){
		
		return "lea_accessTeacher";
	}
	
	@RequestMapping(value="/lea_changePassward") 
	public String changePassward(String lead_no){
		request.setAttribute("lead_no", lead_no);
		
		return "lea_change_passward";
	}
	
	
	/**
	 * 验证密码输入正确
	 * @param stu_no
	 * @param passward
	 * @return
	 */
	@RequestMapping(value="/check_LeaPassward") 
	public String checkTeaPassward(String lead_no,String passward){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lead_no", lead_no);
		map.put("lead_passward", passward);
		map.put("role_id", "4");
		Leader leader = null;
		try {
			leader = leaderService.selectLeader(map);
			if(leader != null){
				AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS);
			}else{
				AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/lea_changePass") 
	public String changePass(String lead_no,String newPassward){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lead_no", lead_no);
		map.put("newPassward", newPassward);
		
		try {
			leaderService.updateLeaPassward(map);
			AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS);
		} catch (Exception e) {
			AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转到老师评价列表页面
	 * @param tea_no
	 * @return
	 */
	@RequestMapping(value="/leader/Assess/teacher/list") 
	public String AssessTeacherList(String lea_id){
		List<Teacher> accessList = new ArrayList<Teacher>();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map.put("lea_id", lea_id);
			accessList = teacherService.selectLeaderAssessTeacherList(map);
			request.setAttribute("accessList", accessList);
			request.setAttribute("lea_id",lea_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "lea_assess_teacher_list";
	}
	
	/**
	 * 转到老师评价页面
	 * @param tea_no
	 * @return
	 */
	@RequestMapping(value="/leader/Assess/teacher") 
	public String AssessTeacher(String tea_no,String lea_id){
		Teacher teacher = teacherService.selectTeacherByNo(tea_no);
		request.setAttribute("tea_name", teacher.getTea_name());
		request.setAttribute("tea_no", tea_no);
		request.setAttribute("lea_id", lea_id);
		return "lea_accessTeacher";
	}
	
	/**
	 * 保存评价老师信息
	 * @param stu_no
	 * @param tea_no
	 * @param answer
	 * @return
	 */
	@RequestMapping(value="/save/Leader/Acess/Teacher/Answer",method=RequestMethod.POST)
	public String saveLeaderAcessTeacherAnswer(String tea_no,String lea_id,String answer,String advise){
			QuestionAnswer questionAnswer = new QuestionAnswer();
			Teacher assessTeacher = teacherService.selectTeacherByNo(tea_no); //被评价老师
			try {
				questionAnswer.setAdvisement(advise);
				questionAnswer.setLeaId(Integer.valueOf(lea_id));
				questionAnswer.setTeaId(assessTeacher.getTea_id());
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
