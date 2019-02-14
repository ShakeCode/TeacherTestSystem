<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>老师详情</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/notify.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/assets/css/page-min.css"></script> --%>
  <link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css" rel="stylesheet">
  <link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css" rel="stylesheet">
</head>
<body style="font-size: 15px">

<div class="container"  style="margin-left: 30px">
   <input type="hidden" id="teacher"  value="${teacher}"/>
   <input  type="hidden"  id="teaNo"  value="${teacher.tea_no}" />
    <div class="detail-page">
      <h1 style="margin-top: 30px">个人信息</h1>
      <hr/>
<div class="detail-section">  
  <h3>基本信息</h3>
  <div class="row detail-row">
    <div class="span8">
      <label>姓名：</label><span class="detail-text">${teacher.tea_name}</span>
    </div>
    <div class="span8">
      <label>工号：</label><span class="detail-text">${teacher.tea_no}</span>
    </div>
     <div class="span8">
      <label>性别：</label><span class="detail-text">${teacher.sex}</span>
    </div>
  </div>
  <div class="row detail-row">
    <div class="span8">
      <label>所教班级：</label><span class="detail-text">${teacher.className}</span>
    </div>
    <div class="span8">
      <label>年龄：</label><span class="detail-text">${teacher.age}</span>
    </div>
     <div class="span8">
      <label>家庭住址：</label><span class="detail-text">${teacher.address}</span>
    </div>
  </div>
  
   <div class="row detail-row">
   <div class="span8">
      <label>出生日期：</label><span class="detail-text"><fmt:formatDate  value="${teacher.birthday}"/></span>
    </div>
    
    <div class="span8">
      <label>身份证号：</label><span class="detail-text">${teacher.idNo}</span>
    </div>
    </div>
</div>
</div>

<!-- 详情页 ================================================== -->
  <div class="row" >
    <div class="span24">
      <hr>
        <h3 style="margin-bottom: 20px ">其他信息</h3>   
        <div class="row detail-row">
          <div class="span8">
          <label>入学时间：</label><span class="detail-text"><fmt:formatDate  value="${teacher.workTime}"/></span>   
          </div>
          <div class="span8">
          <label>专业：</label><span class="detail-text">${teacher.major}</span>
          </div>
           <div class="span8">
          <label>所属学院：</label><span class="detail-text">${teacher.colle_name}</span>
          </div>
         </div>
         
         <div class="row detail-row">
          <div class="span8">
          <label>毕业学校：</label><span class="detail-text">${teacher.old_colle}</span>
          </div>
         </div>
       </div>
       </div>  
      
      <hr/>
       <div class="detail-row">
          <div class="detail-actions">
            <a class="button button-primary" href="${pageContext.request.contextPath}/editTeacher?tea_no=${teacher.tea_no}" id="edit">编辑</a>
          </div>
        </div>
    </div>
</body>

<script type="text/javascript">
      $(function($){
    	  $("#edit1").click(function(){
    		  debugger
    		 $.ajax({
    				type:"get",
    				dataType:"json",
    				data:{"tea_no":$("#teaNo").val()},
    				url:"/editTeacher",
    				async:true,
    				success:function(data){
    					debugger
    					if(data.status == 0){
    						flag = 0;
    						//return flag;
    					}else{
    						flag=1;
    						//return flag;
    					}
    				},
    				error:function(error){
    					
    				}, 
    		 }) 
    	  });
      });
</script>
</html>