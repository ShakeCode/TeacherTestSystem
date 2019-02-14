<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/notify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/bootstrap.js"></script>
 <link href="${pageContext.request.contextPath }/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/assets/css/main-min.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath }/Css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/Css/style.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/Css/bootstrap-responsive.css" rel="stylesheet" type="text/css" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>评价老师列表</title>
  <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>

<body style="font-weight: bold; font-size: 14px">
<div class="demo-content">
<table class="table table-bordered table-hover definewidth m10">
    <thead>
	 <tr>
       <th>#</th>
       <th>姓名</th>
       <th>工号</th>
       <th>课程</th>
       <th>操作</th>
     </tr>
    </thead>
      <c:forEach var="item"  items="${accessList}" varStatus="status">
          <tr>
            <td>${ status.index + 1}</td>
            <td>${item.tea_name}</td>
            <td>${item.tea_no}</td>
            <td>${item.cour_name}</td>
            <td><a href="/leader/Assess/teacher.action?tea_no=${item.tea_no}&lea_id=${lea_id}">评价</a></td>
          </tr>
      </c:forEach>
</table>
</div>
</body>
</html>