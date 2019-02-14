<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改资料</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/notify.js"></script>
 <link href="${pageContext.request.contextPath }/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body style="font-size: 12px">

<div class="demo-content" style="margin-left: 30px;">
 <input  type="hidden"  id="teaNo"  value="${teacher.tea_no}" />
    <div class="row">
      <div class="span24">
        <form id="J_Form" class="form-horizontal" action="/updateTeacher" method="post">
          <h2 style="margin-top: 20px;">教师信息：</h2>
 
            <div class="control-group">
              <label class="control-label"><s>*</s>姓名：</label>
              <div class="controls">
               <input type="text" name="tea_name" id="teaName" data-rules="{required:true}" value="${teacher.tea_name}"> 
              </div>
            </div>
            
               <input type="hidden" name="tea_no" id="tea_no" data-rules="{required:true}" value="${teacher.tea_no}"> 
             
             <div class="control-group">
              <label class="control-label"><s>*</s>性别：</label>
              <div class="controls">
               <input type="text" name="sex" id="Sex" data-rules="{required:true}" value="${teacher.sex}"> 
              </div>
            </div>
            
             <div class="control-group">
              <label class="control-label"><s>*</s>所教班级：</label>
              <div class="controls">
               <input type="text" disabled="disabled"  name="teacher.className" id="teaClass" data-rules="{required:true}" value="${teacher.className}"> 
              </div>
            </div>
            
             <div class="control-group">
              <label class="control-label"><s>*</s>年龄：</label>
              <div class="controls">
               <input type="text" name="age" id="teaAge" data-rules="{required:true}" value="${teacher.age}"> 
              </div>
            </div>
            
               <div class="control-group">
              <label class="control-label"><s>*</s>出生日期：</label>
              <div class="controls">
                <input type="text" id="birthday" name="birthday" class="calendar" value="${birthday}" data-rules="{required:true}"/>
              </div>
            </div> 
            
             <div class="control-group">
              <label class="control-label"><s>*</s>身份证号：</label>
              <div class="controls">
               <input type="text" name="idNo" id="idNo" data-rules="{required:true}" value="${teacher.idNo}"> 
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label"><s>*</s>毕业学校：</label>
              <div class="controls">
               <input type="text" name="old_colle" id="old_colle" data-rules="{required:true}" value="${teacher.old_colle}"> 
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label"><s>*</s>教学时间：</label>
              <div class="controls">
                <input type="text" id="workTime" name="workTime" class="calendar" value="${workTime}" data-rules="{required:true}"/>
              </div>
            </div>
            
             <div class="control-group">
              <label class="control-label"><s>*</s>家庭住址：</label>
              <div class="controls">
               <input type="text" name="address" id="teaAddress" value="${teacher.address}"  data-rules="{required:true}" style="width:250px;"/> 
              </div>
            </div>
            
             <div class="control-group">
              <label class="control-label"><s>*</s>专业：</label>
              <div class="controls">
               <input type="text" name="major" id="teaMajor" data-rules="{required:true}" value="${teacher.major}"> 
              </div>
            </div>
           
             <div class="control-group">
              <label class="control-label"><s>*</s>学院名称：</label>
              <div class="controls">
               <input type="text" name="colle_name" id="teaColl" value="${teacher.colle_name}" data-rules="{required:true}"/> 
              </div>
            </div> 
 
            <hr>
            <div class="form-actions span5 offset3">
              <button id="btnSearch" value="确定" class="button button-primary">确定</button> 
            </div>
            
           
        </form> 
      </div>
    </div>  
    <script src="http://g.tbcdn.cn/fi/bui/jquery-1.8.1.min.js"></script>
    <script src="http://g.tbcdn.cn/fi/bui/seed-min.js?t=201212261326"></script>
    <script type="text/javascript">
      BUI.use('bui/form',function  (Form) {
        new Form.Form({
          srcNode : '#J_Form'
        }).render();
      });
      
      $("#btnSearch1").click(function(){
    	  debugger
    		$.ajax({
				type:"post",
				dataType:"json",
				data:$("#J_Form").seralize(),
				url:"/updateStudent",
				//contentType:'application/x-www-form-urlencoded; charset=UTF-8',
				success:function(data){
					if(data.status == 1){
						 $.notify("更新成功！！！");
					window.location.href="/stu_message.action?stu_no="+$("#stuNo").val();					
					}else{//显示正确图标
						 $.notify("更新失败！！");	
					}
				},
				error:function(error){
					
				},
		 
	 });
      });
    </script>
<!-- script end -->
  </div>

</body>
</html>