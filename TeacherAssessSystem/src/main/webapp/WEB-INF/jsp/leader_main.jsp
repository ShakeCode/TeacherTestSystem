<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台主界面</title>

 <link href="${pageContext.request.contextPath }/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	
<div class="header">
<input id="role" type="hidden" value="${role}"/>
    <div class="dl-title">
    	<p style="font-size:28px;">教师评估考核系统<sup>2017</sup></p>
        <!--<img src="/chinapost/Public/assets/img/top.png">-->
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">
    <%!String username =null; %>
    <% if(request.getAttribute("username")!=null){
    	 username = (String)request.getAttribute("username");
    }else if(request.getAttribute("username")==null){
    	username="请编辑你的资料";
    }
    %>
    <%= username %>
    </span><a href="${pageContext.request.contextPath }/logout.action" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">系统管理</div></li>		
            <!-- <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">业务管理</div></li> -->

        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/bui-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/common/main-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/config-min.js"></script>
<script>
    BUI.use('common/main',function(){
        var config = [{id:'1',menu:[{text:'个人管理',items:[{id:'12',text:'评价老师 <span class="badge badge-error">${access_count}</span>',href:'${pageContext.request.contextPath }/leader/Assess/teacher/list.action?lea_id=${user.id}'},{id:'3',text:'修改密码',href:'${pageContext.request.contextPath }/lea_changePassward.action?lead_no=${user.lead_no}'},{id:'7',text:'录入师生信息',href:'${pageContext.request.contextPath }/leaderUpload.action'},{id:'7',text:'下载老师信息模版',href:'${pageContext.request.contextPath }/teacher_download.action'},{id:'7',text:'下载学生信息模版',href:'${pageContext.request.contextPath }/student_download.action'},{id:'7',text:'下载老师评价结果',href:'${pageContext.request.contextPath }/Assess/Teacher/Result/Download.action'}]}]}];
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>
<div style="text-align:center;"></div>
</body>
</html>