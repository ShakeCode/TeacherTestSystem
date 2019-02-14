package org.spring.springboot.domain;

public class AssessTeacherResult {
    private Integer id;

    private String assessType;

    private String level;

    private String avgScore;

    private String teaName;

    private String teaNo;

    private String colleaName;

    private String totallScore;

    private Integer accessCount;

    private Integer totallAccessCount;

    private String totallLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssessType() {
        return assessType;
    }

    public void setAssessType(String assessType) {
        this.assessType = assessType == null ? null : assessType.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(String avgScore) {
        this.avgScore = avgScore == null ? null : avgScore.trim();
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName == null ? null : teaName.trim();
    }

    public String getTeaNo() {
        return teaNo;
    }

    public void setTeaNo(String teaNo) {
        this.teaNo = teaNo == null ? null : teaNo.trim();
    }

    public String getColleaName() {
        return colleaName;
    }

    public void setColleaName(String colleaName) {
        this.colleaName = colleaName == null ? null : colleaName.trim();
    }

    public String getTotallScore() {
        return totallScore;
    }

    public void setTotallScore(String totallScore) {
        this.totallScore = totallScore == null ? null : totallScore.trim();
    }

    public Integer getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Integer accessCount) {
        this.accessCount = accessCount;
    }

    public Integer getTotallAccessCount() {
        return totallAccessCount;
    }

    public void setTotallAccessCount(Integer totallAccessCount) {
        this.totallAccessCount = totallAccessCount;
    }

    public String getTotallLevel() {
        return totallLevel;
    }

    public void setTotallLevel(String totallLevel) {
        this.totallLevel = totallLevel == null ? null : totallLevel.trim();
    }
}