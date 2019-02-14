package org.spring.springboot.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.spring.springboot.domain.AssessTeacherResult;
import org.spring.springboot.domain.Leader;
import org.spring.springboot.domain.QuestionAnswer;
import org.spring.springboot.domain.Role;
import org.spring.springboot.domain.Student;
import org.spring.springboot.domain.Teacher;
import org.spring.springboot.service.AssessTeacherResultService;
import org.spring.springboot.service.LeaderService;
import org.spring.springboot.service.QuestionAnswerService;
import org.spring.springboot.service.RoleService;
import org.spring.springboot.service.StudentService;
import org.spring.springboot.service.TeacherService;
import org.spring.springboot.util.AjaxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;

//浏览器访问：http://localhost:8080/user/api/user/findUser?userName=lijun
// swagger 访问： http://localhost:8080/swagger-ui.html

/**
 * @author lijun
 *
 */
/**
 * @author lijun
 *
 */
@Controller
@Api("userController相关api")
public class UserRestController extends BaseController{
//	protected static Logger logger=LoggerFactory.getLogger(UserRestController.class); 
    
	@Autowired
	private RoleService roleService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private LeaderService leaderService;
	
	@Autowired
	private QuestionAnswerService questionAnswerService;
	
	@Autowired
	private AssessTeacherResultService assessTeacherResultService;
	
    @RequestMapping(value="/login")  
    public String login(HttpSession session, String username, String password){
    	return "login1";
    }
    
    @RequestMapping(value="/main")  
    public String loginToMain( String username, String userNo,String role){
    	request.setAttribute("username", username);
    	request.setAttribute("role", role);
    	Student student = null;
    	Teacher teacher = null;
    	Leader leader = null;
    	Map<String,Object> map = new HashMap<String,Object>();
    	Map<String,Object> map1 = new HashMap<String,Object>();
    	Map<String,Object> map2 = new HashMap<String,Object>();
    	if("2".equals(role)){
    		map.put("stu_no", userNo);
			map.put("role_id", role);
			student = studentService.selectStudent(map);
			request.setAttribute("user", student);
			int count = studentService.selectAccessTeacherCount(student.getStu_no());
    		request.setAttribute("access_count",count);
    		assessAllTeacher();  //计算评分
			return "student_main";
    	}else if("3".equals(role)){
    		map1.put("tea_no", userNo);
			map1.put("role_id", role);
			teacher = teacherService.selectTeacher(map1);
			request.setAttribute("user", teacher);
			
			Map<String,Object> map9 = new HashMap<String,Object>();
			map9.put("tea_no", teacher.getTea_no());
			map9.put("cour_id", teacher.getClass_id());
			map9.put("tea_id", teacher.getTea_id());
			int count = teacherService.selectAccessSameFiledTeacherCount(map9);
			request.setAttribute("access_count",count);

			assessAllTeacher();  //计算评分
    		return "teacher_main";
    	}else if("4".equals(role)){
    		map2.put("lead_no", userNo);
			map2.put("role_id", role);
			leader = leaderService.selectLeader(map2);
			request.setAttribute("user", leader);
			Map<String,Object> map10 = new HashMap<String,Object>();
			map10.put("lea_id",leader.getId());
			int count = teacherService.selectLeaderAssessTeacherCount(map10);
			request.setAttribute("access_count",count);
			
			assessAllTeacher();  //计算评分
    		return "leader_main";
    	}else{
    		return null;
    	}
    	
    	
    } 
    
	
	/**
	 * 对所有老师进行评分，并插入数据库
	 */
	public void assessAllTeacher(){
		try {
			assessTeacherResultService.deleteAllResult();  //删除所有结果
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		List<Teacher>  teacherList = teacherService.selectAllTeacher();
		try {
			for(int i=0;i<teacherList.size();++i){
				Teacher teaTemp = teacherList.get(i);
				if(questionAnswerService.
						isExistInAssessQuestion(teaTemp.getTea_id()).size()>0){
					//开始计算各个评价指标
					int  tea_id = teaTemp.getTea_id();
					//学生评分
					List<QuestionAnswer> stuList = questionAnswerService.selectAssessListBySameFiledStudent(tea_id);
					int stuCount = questionAnswerService.selectAssessCountBySameFiledStudent(tea_id);
					Map<String,Object> stuMap = calculateTypeAssessScore(stuList,stuCount);
					//同行评分
					List<QuestionAnswer> teaList = questionAnswerService.selectAssessListBySameFiledTeacher(tea_id);
					int teaCount = questionAnswerService.selectAssessCountBySameFiledTeacher(tea_id);
					Map<String,Object> teaMap = calculateTypeAssessScore(teaList,teaCount);
					//领导评分
					List<QuestionAnswer> leadList = questionAnswerService.selectAssessListByLeader(tea_id);
					int leaCount = questionAnswerService.selectCountAssessByLeader(tea_id);
					Map<String,Object> leaMap = calculateTypeAssessScore(leadList,leaCount);
					
					//所有评价人数,答案
					int allAssessCount = questionAnswerService.selectAsscessAllPeopleAnswerCount(tea_id);
					List<QuestionAnswer> allAssessList = questionAnswerService.selectAsscessAllPeopleAnswerList(tea_id);
					Map<String,Object> allAssessMap = calculateTypeAssessScore(allAssessList,allAssessCount);
					
					//插入评分表
					AssessTeacherResult stuAssess = new AssessTeacherResult();
					AssessTeacherResult teaSameFiledAssess = new AssessTeacherResult();
					AssessTeacherResult leaderAssess = new AssessTeacherResult();
					AssessTeacherResult TotallAssess = new AssessTeacherResult();
					if(stuList.size()>0 && null !=stuMap){
						//插入学生评价
						stuAssess.setColleaName(teaTemp.getColle_name());
						stuAssess.setTeaName(teaTemp.getTea_name());
						stuAssess.setTeaNo(teaTemp.getTea_no());
						stuAssess.setAssessType("学生");
						stuAssess.setAvgScore((String)stuMap.get("avgScore"));
						stuAssess.setLevel(calcuteLevel((String)stuMap.get("avgScore")));
						stuAssess.setTotallScore((String)stuMap.get("totallScore"));
						stuAssess.setAccessCount((Integer)stuMap.get("assessCount"));
						assessTeacherResultService.insertSelective(stuAssess);
						
					}
					if(teaList.size()>0 && null !=teaMap){
						//插入同行评价
						teaSameFiledAssess.setColleaName(teaTemp.getColle_name());
						teaSameFiledAssess.setTeaName(teaTemp.getTea_name());
						teaSameFiledAssess.setTeaNo(teaTemp.getTea_no());
						teaSameFiledAssess.setAssessType("老师");
						teaSameFiledAssess.setAvgScore((String)teaMap.get("avgScore"));
						teaSameFiledAssess.setLevel(calcuteLevel((String)teaMap.get("avgScore")));
						teaSameFiledAssess.setTotallScore((String)teaMap.get("totallScore"));
						teaSameFiledAssess.setAccessCount((Integer)teaMap.get("assessCount"));
						assessTeacherResultService.insertSelective(teaSameFiledAssess);
							
					}
					
					if(leadList.size()>0 && null != leaMap){
						//插入领导评价
						leaderAssess.setColleaName(teaTemp.getColle_name());
						leaderAssess.setTeaName(teaTemp.getTea_name());
						leaderAssess.setTeaNo(teaTemp.getTea_no());
						leaderAssess.setAssessType("领导");
						leaderAssess.setAvgScore((String)leaMap.get("avgScore"));
						leaderAssess.setLevel(calcuteLevel((String)leaMap.get("avgScore")));
						leaderAssess.setTotallScore((String)leaMap.get("totallScore"));
						leaderAssess.setAccessCount((Integer)leaMap.get("assessCount"));
						assessTeacherResultService.insertSelective(leaderAssess);
							
					}
					if(allAssessList.size()>0 && null != allAssessMap){
						//插入总的评价记录
						TotallAssess.setColleaName(teaTemp.getColle_name());
						TotallAssess.setTeaName(teaTemp.getTea_name());
						TotallAssess.setTeaNo(teaTemp.getTea_no());
						TotallAssess.setAssessType("总评");
						TotallAssess.setAvgScore((String)allAssessMap.get("avgScore"));
						TotallAssess.setLevel(calcuteLevel((String)allAssessMap.get("avgScore")));
						TotallAssess.setTotallLevel(calcuteLevel((String)allAssessMap.get("avgScore")));
						TotallAssess.setTotallScore((String)allAssessMap.get("totallScore"));
						TotallAssess.setTotallAccessCount((Integer)allAssessMap.get("assessCount"));
						TotallAssess.setAccessCount((Integer)allAssessMap.get("assessCount"));
						assessTeacherResultService.insertSelective(TotallAssess);	
					}
					
				}else{
					String s = teaTemp.getTea_name()+"该老师未被评价";
					System.out.println("-----------------报错！！！！----------------"+s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * 计算每一类群体的评价分数，平均分，人数
	 * @param stuList
	 * @param count
	 * @return
	 */
	public Map<String,Object> calculateTypeAssessScore(List<QuestionAnswer> stuList,int count){
		int totallScore =0;
		Map<String,Object> scoreTypeMap = new HashMap<String,Object>();
		for(QuestionAnswer q : stuList){
			String[] answerArray = q.getAnswer().split(",");
			//遍历数组
			for(String s :answerArray){
				if("A".equals(s)){
					totallScore +=10;
				}else if("B".equals(s)){
					totallScore +=5;
				}else if("C".equals(s)){
					totallScore -=10;
				}else{
					totallScore +=0;
				}
			}
		}
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
		scoreTypeMap.put("totallScore",String.valueOf(totallScore)); //总分
		scoreTypeMap.put("assessCount", count);//评价人数
		scoreTypeMap.put("avgScore", df.format(((float)totallScore/count)));//计算平均分，返回String类型
		return scoreTypeMap;
	}
	
	/**
	 * 按照平均分计算等级
	 * @param avgscore
	 * @return
	 */
	public String calcuteLevel(String avgScore){
		int len = avgScore.length();
		int endIndex = avgScore.indexOf(".")> 0 ? avgScore.indexOf(".") : 0 ;
		int avgscore = Integer.parseInt(avgScore.substring(0,endIndex > 0 ? endIndex : len ));
		if(avgscore >= 60 && avgscore<= 80){
			return "C";
		}else if(avgscore > 0 && avgscore<60){
			return "D";
		}else if(avgscore > 0 && avgscore >80 && avgscore<90){
			return "B";
		}else if(avgscore > 0 && avgscore>90){
			return "A";
		}else{
			return "未知等级";
		}
	}
    
    @RequestMapping(value="/loginInto")  
    public String loginInto( String username, String passward,String role){
    	Role roleObject = null;
    	Student student = null;
    	Teacher teacher = null;
    	Leader leader = null;
    	
    	List list1 = new ArrayList();
    	List list2 = new ArrayList();
    	List list3 = new ArrayList();
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	Map<String,Object> map1 = new HashMap<String,Object>();
    	Map<String,Object> map2 = new HashMap<String,Object>();
		try {
			roleObject = roleService.selectRoleName(role);
			if(null != roleObject){
				//学生登录
				if("2".equals(roleObject.getRole_id())){
					map.put("stu_no", username);
					map.put("passward", passward);
					map.put("role_id", role);
					student = studentService.selectStudent(map);
					if(student != null){
						list1.add(student);
						AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS,list1);
					}else{
						AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
					}
					//老师登录
				}else if("3".equals(roleObject.getRole_id())){
					map1.put("tea_no", username);
					map1.put("passward", passward);
					map1.put("role_id", role);
					teacher = teacherService.selectTeacher(map1);
					if(teacher != null){
						list2.add(teacher);
						request.setAttribute("username", 12);
						AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS,list2);
					}else{
						AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
					}
					//领导登陆
				}else if("4".equals(roleObject.getRole_id())){
					map2.put("lead_no", username);
					map2.put("lead_passward", passward);
					map2.put("role_id", role);
					
					leader = leaderService.selectLeader(map2);
					if(leader != null){
						list3.add(leader);
						AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS,list3);
					}else{
						AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
					}
				}
			}else{
				AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//    	return "redirect:/items/queryItems.action";
    			
    	return "teacher_main";
    }
    
	// 退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {

		// 清除session
		session.invalidate();

		// 重定向到商品列表页面
		//return "redirect:/items/queryItems.action";
		return "login1";
	}
    
	
	/**
	 * 用户注册
	 * @param username
	 * @param role
	 * @param passwd
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String  regisiter(String username , String role, String passwd){
		Map<String,Object> map = new HashMap<String,Object>();
    	Map<String,Object> map1 = new HashMap<String,Object>();
    	Map<String,Object> map2 = new HashMap<String,Object>();
		
		try {
			//学生注册
			if("2".equals(role)){
				map.put("stu_no", username);
				map.put("passward", passwd);
				map.put("role_id", role);	
				
				studentService.insertStudent(map);
				AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS);
				//老师注册
			}else if("3".equals(role)){
				map1.put("tea_no", username);
				map1.put("passward", passwd);
				map1.put("role_id", role);
				teacherService.insertTeacher(map1);
				AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS);
				//领导注册
			}else if("4".equals(role)){
				map2.put("lead_no", username);
				map2.put("lead_passward", passwd);
				map2.put("role_id", role);
				
				leaderService.insertLeader(map2);
				AjaxUtil.makeAjaxResponse(response,"success",AjaxUtil.SUCCESS);
			}else{
				AjaxUtil.makeAjaxResponse(response,"error",AjaxUtil.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
   
	/**
	 * 检查是否该角色的的学号/工号是否已经被注册
	 * @param username
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/checkRegistered",method=RequestMethod.POST)
	public String checkRegistered(String username , String role){
		Map<String,Object> map = new HashMap<String,Object>();
    	Map<String,Object> map1 = new HashMap<String,Object>();
    	Map<String,Object> map2 = new HashMap<String,Object>();
		
		try {
			//学生注册
			if("2".equals(role)){
				map.put("stu_no", username);
				map.put("role_id", role);	
			Student student = studentService.selectStudent(map);
			if(student != null){
				//已被注册
				AjaxUtil.makeAjaxResponse(response,"已被注册",AjaxUtil.FAIL);
			}else{
				AjaxUtil.makeAjaxResponse(response,"未被注册",AjaxUtil.SUCCESS);
			}
				//老师注册
			}else if("3".equals(role)){
				map1.put("tea_no", username);
				map1.put("role_id", role);
			Teacher teacher = teacherService.selectTeacher(map1);
			if(teacher != null){
				//已被注册
				AjaxUtil.makeAjaxResponse(response,"已被注册",AjaxUtil.FAIL);
			}else{
				AjaxUtil.makeAjaxResponse(response,"未被注册",AjaxUtil.SUCCESS);
			}
				//领导注册
			}else if("4".equals(role)){
				map2.put("lead_no", username);
				map2.put("role_id", role);
				Leader leader = leaderService.selectLeader(map2);
				if(leader != null){
					//已被注册
					AjaxUtil.makeAjaxResponse(response,"已被注册",AjaxUtil.FAIL);
				}else{
					AjaxUtil.makeAjaxResponse(response,"未被注册",AjaxUtil.SUCCESS);
				}
			}else{
				AjaxUtil.makeAjaxResponse(response,"未知异常",AjaxUtil.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		return null;
	}
}
