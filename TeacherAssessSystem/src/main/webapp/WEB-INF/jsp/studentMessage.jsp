<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生详情</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/notify.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/assets/css/page-min.css"></script> --%>
  <link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css" rel="stylesheet">
  <link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css" rel="stylesheet">
</head>
<body style="font-size: 15px">

<div class="container"  style="margin-left: 30px">
   <input type="hidden" id="student"  value="${student}"/>
   <input  type="hidden"  id="stuNo"  value="${student.stu_no}" />
    <div class="detail-page">
      <h2>学生信息</h2>
<div class="detail-section">  
  <h3>基本信息</h3>
  <div class="row detail-row">
    <div class="span8">
      <label>姓名：</label><span class="detail-text">${student.stu_name}</span>
    </div>
    <div class="span8">
      <label>学号：</label><span class="detail-text">${student.stu_no}</span>
    </div>
     <div class="span8">
      <label>性别：</label><span class="detail-text">${student.sex}</span>
    </div>
  </div>
  <div class="row detail-row">
    <div class="span8">
      <label>班级：</label><span class="detail-text">${student.class_name}</span>
    </div>
    <div class="span8">
      <label>年龄：</label><span class="detail-text">${student.age}</span>
    </div>
     <div class="span8">
      <label>家庭住址：</label><span class="detail-text">${student.address}</span>
    </div>
  </div>
</div>
</div>

<!-- 详情页 ================================================== -->
  <div class="row" >
    <div class="span24">
      <hr>
        <h3 style="margin-bottom: 20px ">学校信息</h3>   
        <div class="row detail-row">
          <div class="span8">
          <label>入学时间：</label><span class="detail-text">${enterTime}</span>   
          </div>
          <div class="span8">
          <label>专业：</label><span class="detail-text">${student.major}</span>
          </div>
           <div class="span8">
          <label>学院：</label><span class="detail-text">${student.colle_name}</span>
          </div>
         </div>
       </div>
       </div>  
      
       <div class="detail-row">
          <div class="detail-actions">
            <a class="button button-primary" href="${pageContext.request.contextPath}/editStudent?stu_no=${student.stu_no}" id="edit">编辑</a>
          </div>
        </div>
    </div>
</body>

<script type="text/javascript">
      $(function($){
    	  $("#edit1").click(function(){
    		  debugger
    		 $.ajax({
    				type:"post",
    				dataType:"json",
    				data:{"stu_no":$("#stuNo").val()},
    				url:"/editStudent",
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