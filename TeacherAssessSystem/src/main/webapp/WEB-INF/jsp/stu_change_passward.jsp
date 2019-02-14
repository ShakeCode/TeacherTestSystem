<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/notify.js"></script>
  <link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css" rel="stylesheet">
  <link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css" rel="stylesheet">
</head>
<body>

  <div class="demo-content">
        <input id="stuNo" type="hidden" value="${stu_no}"/>
    <div class="row">
      <div class="span20 doc-content">
         <div class="form-horizontal well">
          <div class="control-group">
            <label class="control-label">请输入原密码：</label>
            <div class="controls"><input class="input-normal control-text" id="oldPassward" type="text"></div>
           <span id="oldPassSpan" style="margin-left: 5px"></span> 
          </div>
          
           <div class="control-group">
            <label class="control-label">请输入新密码：</label>
            <div class="controls"><input class="input-normal control-text" id="newPassward1" type="password"></div>
          <span id="oldPassSpan1" style="margin-left: 5px"></span> 
          </div>
          
           <div class="control-group">
            <label class="control-label">请再次输入新密码：</label>
            <div class="controls"><input class="input-normal control-text" id="newPassward2" type="password"></div>
          <span id="oldPassSpan2" style="margin-left: 5px"></span> 
          </div>
          
            <div class="row">
              <div class="form-actions offset3">
                <button id="submit" type="submit" class="button button-primary">保存</button>
                <button type="reset" class="button">重置</button>
              </div>
            </div>
          </div>
      </div>
    </div>
  </div>
</body>

<script type="text/javascript">
  $(function($){
	  var flag =0;
	 //alert("学号："+$("#stuNo").val());
	 
	 $("#oldPassward").blur(function(){
			$.ajax({
				type:"post",
				dataType:"json",
				data:{"stu_no":$("#stuNo").val().trim(),"passward":$("#oldPassward").val().trim()},
				url:"/check_stuPassward",
				success:function(data){
					if(data.status == 0){
						$("#oldPassSpan").html("<i class='icon icon-remove icon-green' style='margin-top:5px;'></i>");
						return false;
						
					}else{//显示正确图标
						$("#oldPassSpan").html("<i class='icon icon-ok icon-green' style='margin-top:5px;'></i>");
					    flag =1;
					}
				},
				error:function(error){
					
				},
		 
	 });
	 });		
			
	$("#newPassward1").blur(function(){
		if(!Util.isBlank($("#newPassward1").val()) && $("#newPassward1").val().trim().length>0){
			$("#oldPassSpan1").html("<i class='icon icon-ok icon-green' style='margin-top:5px;'></i>");
	 };
	});
	
	
	
	$("#newPassward2").blur(function(){
		if(!Util.isBlank($("#newPassward2").val()) && $("#newPassward2").val().trim().length>0){
			$("#oldPassSpan2").html("<i class='icon icon-ok icon-green' style='margin-top:5px;'></i>");
	 };
		if($("#newPassward1").val() != $("#newPassward2").val() && $("#newPassward1").val().trim().length>0){
			$("#oldPassSpan2").html("<i class='icon icon-remove icon-green' style='margin-top:5px;'></i>");
	 }else{
		 $("#oldPassSpan2").html("<i class='icon icon-ok icon-green' style='margin-top:5px;'></i>");
	 }
	});
			
	var  checkPass =function(){
		debugger
		
		$.ajax({
			type:"post",
			dataType:"json",
			data:{"stu_no":$("#stuNo").val().trim(),"passward":$("#oldPassward").val().trim()},
			url:"/check_stuPassward",
			async:false,
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
 });
	}
	 $("#submit").click(function(){
		//alert(checkPass());
		 //checkPass();
		// alert(flag?"密码正确":"密码错误");
		 if(flag){
			 debugger
			 if(Util.isBlank($("#oldPassward").val())){
				 $.notify("密码不能为空！");
				 return false;
			 };
				if(Util.isBlank($("#newPassward1").val())){
					$.notify("请输入新密码！");
				 return false;
			 };
				if(Util.isBlank($("#newPassward2").val())){
					$.notify("请确认新密码！");
				 return false;
			 };
				if($("#newPassward1").val() != $("#newPassward2").val().trim() ){
					$.notify("两次密码不一致！");
				 return false;
			 };
			 $.ajax({
					type:"post",
					dataType:"json",
					data:{"stu_no":$("#stuNo").val(),"newPassward":$("#newPassward1").val()},
					url:"/stu_changePass",
					async:true,
					success:function(data){
						if(data.status == 0){
							$.notify("密码修改失败！");
							return false;
						}else{
							$.notify("密码修改成功！");
							$("#oldPassward").val("");
							$("#newPassward1").val("");
							$("#newPassward2").val("");
							
							$("#oldPassSpan").remove();
							$("#oldPassSpan1").remove();
							$("#oldPassSpan2").remove();
						 	/* window.location.href="/stu_changePassward";  */
						}
					},
					error:function(error){
						
					},
		 }); 
		 }else{
			 $.notify("密码不正确！！");
			 return false;
		 }
	
	 });
  });
</script>
</html>