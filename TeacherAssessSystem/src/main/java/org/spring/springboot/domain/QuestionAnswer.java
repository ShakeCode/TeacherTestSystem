package org.spring.springboot.domain;

public class QuestionAnswer {
    private Integer id;

    private Integer teaId;

    private Integer stuId;

    private Integer teaAnId;

    private Integer leaId;

    private String answer;

    private String advisement;
    
    private String stuName;    //评论的学生名字
    
    private  String teaName;   //评论的老师名字
    
    private String leaName;    //评论的领导名字
    
    public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public String getLeaName() {
		return leaName;
	}

	public void setLeaName(String leaName) {
		this.leaName = leaName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getTeaAnId() {
        return teaAnId;
    }

    public void setTeaAnId(Integer teaAnId) {
        this.teaAnId = teaAnId;
    }

    public Integer getLeaId() {
        return leaId;
    }

    public void setLeaId(Integer leaId) {
        this.leaId = leaId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getAdvisement() {
        return advisement;
    }

    public void setAdvisement(String advisement) {
        this.advisement = advisement == null ? null : advisement.trim();
    }
}