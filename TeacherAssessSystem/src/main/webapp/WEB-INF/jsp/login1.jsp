<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>教师评估考核系统--登录界面</title> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/notify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/util.js"></script>
<link href="${pageContext.request.contextPath }/Css/login2.css" rel="stylesheet" type="text/css" />
</head>

<body>
<!-- 浏览器登录地址：http://localhost:8080/springmvc_information_system/items/queryItems -->
<h1>教师评估考核系统<sup>2017</sup></h1>

<div class="login" style="margin-top:50px;">
    
    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
			<a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a><div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>    
  
    
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 300px;">    

            <!--登录-->
            <div class="web_login" id="web_login">
               
               
               <div class="login-box">
    
            <%-- action="${pageContext.request.contextPath }/loginInto.action"  --%>
			<div class="login_form">
				<form name="loginform" action="/loginInto" accept-charset="utf-8" id="login_form" class="loginForm" method="post">
				<input type="hidden" name="did" value="0"/>
               
               <input type="hidden" name="to" value="log"/>
               
                <div class="uinArea" id="uinArea">
               <label for="user"  class="input-tips">角色：</label>
                    <div class="inputOuter3">
	                    <label><input name="role" type="radio" value="2" checked="checked"/>学生 </label> 
						<label><input name="role" type="radio" value="3" />老师 </label> 
						<label><input name="role" type="radio" value="4" />领导 </label> 
						</div>
               </div>
               
                <div class="uinArea" id="uinArea">
                <label class="input-tips" for="u">帐号：</label>
                <div class="inputOuter" id="uArea">
                    
                    <input type="text" id="username" name="username" class="inputstyle"/>
                </div>
                </div>
                
                <div class="pwdArea" id="pwdArea">
              		 <label class="input-tips" for="p">密码：</label> 
	               <div class="inputOuter" id="pArea">
	                    <input type="password" id="passward" name="p" class="inputstyle"/>
	               </div>
                </div>
               
                <div style="padding-left:50px;margin-top:20px;">
                <input type="button" id="login" value="登 录" style="width:150px;" class="button_blue" onkeydown="if(event.keyCode == 13){submit()}"/>
                </div>
              </form>
           </div>
           
            	</div>
               
            </div>
            <!--登录end-->
  </div>

  <!--注册-->
    <div class="qlogin" id="qlogin" style="display: none; ">
   
    <div class="web_login">
    <form name="form2" id="regUser" accept-charset="utf-8"  action="" method="post">
	      <input type="hidden" name="to" value="reg"/>
 		       <input type="hidden" name="did" value="0"/>
        <ul class="reg_form" id="reg-ul">
        		<div id="userCue" class="cue" style="color:#0288d1">请使用学号、工号注册</div>
                <li>
                    <label for="user"  class="input-tips2">用户名：</label>
                    <div class="inputOuter2">
                        <input type="text" id="user" name="username" maxlength="16" class="inputstyle2"/>
                    </div>
                </li>
                
                <li>
                    <label for="user"  class="input-tips2">角色：</label>
                    <div class="inputOuter1" style="margin-bottom:-100px">
	                    <label><input name="role1" type="radio" value="2" />学生 </label> 
						<label><input name="role1" type="radio" value="3" />老师 </label> 
						<label><input name="role1" type="radio" value="4" />领导 </label> 
						</div>
                </li>
                
                <li>
                <label for="passwd" class="input-tips2">密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="passwd"  name="passwd" maxlength="16" class="inputstyle2"/>
                    </div>
                    
                </li>
                <li>
                <label for="passwd2" class="input-tips2">确认密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="passwd2" name="passwd2" maxlength="16" class="inputstyle2" />
                    </div>
                    
                </li>
                
                <li>
                    <div class="inputArea">
                        <input type="button" id="reg"  style="margin-top:10px;margin-left:85px;" class="button_blue" value="注册" /> 
                    </div>
                    
                </li><div class="cl"></div>
            </ul></form>
    </div>
   
    
    </div>
    <!--注册end-->
</div>
<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
	<script type="text/javascript">
	/*  document.onkeydown = function(e){
		   if(!e){
		    e = window.event;
		   }
		   if((e.keyCode || e.which) == 13){
			   submit();
		   }
		  } */
	 function submit(){
		debugger
		if($("#username").val().trim()== null){
			$.notify({"cont":"用户名不能为空！"});
    		return;
    	}
    	if($("#passward").val().trim()==null){
    		$.notify({"cont":"密码不能为空！"});
    		return;
    	}
    	
     	$.ajax({
    		type: "post",
    		dataType:"json",
    	    url: "/loginInto",
    		data:{"username":$("#username").val(),"passward":$("#passward").val(),"role":$("input[name='role']:checked").val()},	
    	    success: function(data){
    	    	debugger
    	    	if(data.status){
    	    		if(data.data[0].role_id == 3){  //老师
    	    			window.location.href="/main?username="+data.data[0].tea_name+"&role="+data.data[0].role_id;
    	    		}else if(data.data[0].role_id == 2){  //学生
    	    			window.location.href="/main?username="+data.data[0].stu_name+"&role="+data.data[0].role_id;
    	    		}else if(data.data[0].role_id == 4){  //领导
    	    			window.location.href="/main?username="+data.data[0].lead_name+"&role="+data.data[0].role_id;
    	    		}
    	    		
    	    	}else{
    	    		$.notify({"cont":"用户名或者密码错误！"});
    	    	}
    	    },
    	    error:function(data){
    	    	$.notify({"cont":"登录失败！"});
    	    },
    	}); 
	}
	$("#login").click(function(){
	 	debugger;
    	if(Util.isBlank($("#username").val())){
    		$.notify("用户名不能为空！");
    		return false;
    	}
    	if(Util.isBlank($("#passward").val())){
    		$.notify("密码不能为空！");
    		return false;
    	}
    	
     	$.ajax({
    		type: "post",
    		dataType:"json",
    	    url: "/loginInto",
    		data:{"username":$("#username").val(),"passward":$("#passward").val(),"role":$("input[name='role']:checked").val()},	
    	    success: function(data){
    	    	//alert(data.data[0].role_id);
    	    	if(data.status){
    	    		if(data.data[0].role_id == 3){  //老师
    	    			window.location.href="/main?username="+data.data[0].tea_name+"&role="+data.data[0].role_id+"&userNo="+data.data[0].tea_no;
    	    		}else if(data.data[0].role_id == 2){  //学生
    	    			window.location.href="/main?username="+data.data[0].stu_name+"&role="+data.data[0].role_id+"&userNo="+data.data[0].stu_no;
    	    		}else if(data.data[0].role_id == 4){  //领导
    	    			window.location.href="/main?username="+data.data[0].lead_name+"&role="+data.data[0].role_id+"&userNo="+data.data[0].lead_no;
    	    		}
    	    		
    	    	}else{
    	    		$.notify("用户名或者密码错误！");
    	    	}
    	    },
    	    error:function(data){
    	    	$.notify("登录失败！");
    	    },
    	}); 
	});
	
	/* $("#reg").click(function(){
		alert("fds");
	}) */
	



	   
	</script>
</body>

</html>