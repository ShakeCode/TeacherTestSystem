package org.spring.springboot.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.spring.springboot.domain.AssessTeacherResult;

@Mapper
public interface AssessTeacherResultDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AssessTeacherResult record);

    int insertSelective(AssessTeacherResult record);

    /**
     * <!-- 查找所有老师的总评结果 -->
     * @return
     */
    List<AssessTeacherResult> selectAllAssessResult();
    /**
     * 删除所有查询结果
     */
    public void deleteAllResult();
    
    /**
     * 根据老师工号查找评分结果
     * @param id
     * @return
     */
    List<AssessTeacherResult>selectByTeaNo(String tea_no);

    int updateByPrimaryKeySelective(AssessTeacherResult record);

    int updateByPrimaryKey(AssessTeacherResult record);
}