package org.spring.springboot.domain;

import java.util.Date;

public class Student {
   private int stu_id;    //主键
   private String stu_name;  //姓名
   private String stu_no;      //学号
   private String passward;  //密码
   private String sex;   //性别
   private int tea_id;   //老师好
   private String role_id;  //角色号
   private int class_id;    //班级号
   private String colle_name;  //学院名称
   private String address;     //地址
   private String major;    //专业
   private Integer age;      // 年龄
   private Date enterTime;    //入学时间
   private String class_name;   //班级名字
   
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getStu_no() {
		return stu_no;
	}
	public void setStu_no(String stu_no) {
		this.stu_no = stu_no;
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
	public int getTea_id() {
		return tea_id;
	}
	public void setTea_id(int tea_id) {
		this.tea_id = tea_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
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
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}
	
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	@Override
	public String toString() {
		return "Student [stu_id=" + stu_id + ", stu_name=" + stu_name + ", stu_no=" + stu_no + ", passward=" + passward
				+ ", sex=" + sex + ", tea_id=" + tea_id + ", role_id=" + role_id + ", class_id=" + class_id + "]";
	}
}
