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
<input id="attribute_userNo" type="hidden" value="${user.tea_no}"/>
    <div class="dl-title">
    	<p style="font-size:28px;">教师评估考核系统<sup>2017</sup></p>
        <!--<img src="/chinapost/Public/assets/img/top.png">-->
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">
 
    <%= request.getAttribute("username") %>
    </span><a href="${pageContext.request.contextPath }/logout.action" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">系统管理</div></li>		
<!--             <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">业务管理</div></li> -->
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
        var config = [{id:'1',menu:[{text:'个人管理',items:[{id:'12',text:'个人资料',href:'${pageContext.request.contextPath }/tea_message.action?tea_no=${user.tea_no}'},{id:'3',text:'修改密码',href:'${pageContext.request.contextPath }/tea_changePassward.action?tea_no=${user.tea_no}'},{id:'4',text:'自我评价',href:'${pageContext.request.contextPath }/teacher/self/Assess.action?tea_no=${user.tea_no}'},{id:'6',text:'同行评价 <span class="badge badge-error">${access_count}</span>',href:'${pageContext.request.contextPath }/access/sameFiled/Teacher/list.action?tea_no=${user.tea_no}'},{id:'7',text:'查看评价',href:'${pageContext.request.contextPath }/select/Assess/list.action?tea_no=${user.tea_no}'}]}]}];
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>
<div style="text-align:center;"></div>
</body>

</html>