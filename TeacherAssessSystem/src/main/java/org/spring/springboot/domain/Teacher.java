package org.spring.springboot.domain;

import java.util.Date;

public class Teacher {
   private int tea_id;    //主键
   private String tea_name; //名字
   private String tea_no;  //教师号
   private int cour_id;  // 课程号
   private String passward;  // 密码
   private 	String sex;   //性别
   private String role_id;   //角色号
   
   private int leader_id;   //领导号
   private String  colle_name;  //学院
   private String address;          //住址
   private String old_colle;    //毕业学校
   private String  major;   //专业
   private Date birthday;    //出生日期
   private  int age;    //年龄
   private Date  workTime;    //教书开始时间
   private String  idNo;     //身份证号
   
   private int class_id;    //班级号
   
   private String className;   //所教班级
   
   private String cour_name;  // 课程
   
	public String getCour_name() {
	return cour_name;
}
public void setCour_name(String cour_name) {
	this.cour_name = cour_name;
}
	public int getClass_id() {
	return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getColle_name() {
		return colle_name;
	}
	public void setColle_name(String colle_name) {
		this.colle_name = colle_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOld_colle() {
		return old_colle;
	}
	public void setOld_colle(String old_colle) {
		this.old_colle = old_colle;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public int getLeader_id() {
		return leader_id;
	}
	public void setLeader_id(int leader_id) {
		this.leader_id = leader_id;
	}
	public int getTea_id() {
		return tea_id;
	}
	public void setTea_id(int tea_id) {
		this.tea_id = tea_id;
	}
	public String getTea_name() {
		return tea_name;
	}
	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
	}
	public String getTea_no() {
		return tea_no;
	}
	public void setTea_no(String tea_no) {
		this.tea_no = tea_no;
	}
	public int getCour_id() {
		return cour_id;
	}
	public void setCour_id(int cour_id) {
		this.cour_id = cour_id;
	}
	public String getPassward() {
		return passward;
	}
	public void setPassward(String passward) {
		this.passward = passward;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	@Override
	public String toString() {
		return "Teacher [tea_id=" + tea_id + ", tea_name=" + tea_name + ", tea_no=" + tea_no + ", cour_id=" + cour_id
				+ ", passward=" + passward + ", sex=" + sex + ", role_id=" + role_id + ", leader_id=" + leader_id
				+ ", colle_name=" + colle_name + ", address=" + address + ", old_colle=" + old_colle + ", major="
				+ major + ", birthday=" + birthday + ", age=" + age + ", workTime=" + workTime + ", idNo=" + idNo
				+ ", class_id=" + class_id + ", className=" + className + "]";
	}
}
