package org.spring.springboot.domain;

public class Leader {
   private int id;  
   private String lead_name;  //名字
   private String lead_no; //工号
   private String lead_passward; //密码
   private String role_id;    //角色号
   private String sex;     //性别
   private String lead_job;  //岗位
   
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLead_name() {
		return lead_name;
	}
	public void setLead_name(String lead_name) {
		this.lead_name = lead_name;
	}
	public String getLead_no() {
		return lead_no;
	}
	public void setLead_no(String lead_no) {
		this.lead_no = lead_no;
	}
	public String getLead_passward() {
		return lead_passward;
	}
	public void setLead_passward(String lead_passward) {
		this.lead_passward = lead_passward;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLead_job() {
		return lead_job;
	}
	public void setLead_job(String lead_job) {
		this.lead_job = lead_job;
	}
	@Override
	public String toString() {
		return "Leader [id=" + id + ", lead_name=" + lead_name + ", lead_no=" + lead_no + ", lead_passward=" + lead_passward
				+ ", role_id=" + role_id + ", sex=" + sex + ", lead_job=" + lead_job + "]";
	}
}
