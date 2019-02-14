<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价结果</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/Js/jquery-1.9.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/Js/util.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/Js/notify.js"></script>
<!-- <script type="text/javascript" src="http://g.alicdn.com/sj/dpl/1.5.1/js/sui.min.js"></script> -->
<script type="text/javascript"
	src="http://g.alicdn.com/sj/sui-editor/0.0.2/sui-editor.config.js"></script>
<script type="text/javascript"
	src="http://g.alicdn.com/sj/sui-editor/0.0.2/editor/js/sui-editor.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/Js/bootstrap.js"></script>
<link href="${pageContext.request.contextPath }/Css/bootstrap.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
fieldset {
	padding: .35em .625em .75em;
	margin: 0 2px;
	border: 1px solid silver;
	border-radius: 10px
}

legend {
	
	border: 0px;
	width: auto;
	height:auto;
	margin-bottom: 10px
}

#role {
	margin-left: 30px;
	font-size: 16px
}

#role h4{
	color:red;
}
</style>
</head>
<body>
	<div style="font-size: 16px">
		<table class="table table-condensed">
			<caption>评价结果</caption>
			<thead>
				<tr>
					<th>#</th>
					<th>类别</th>
					<th>评价分数</th>
					<th>等级</th>
				</tr>
			</thead>
			<tbody>
			
			<c:forEach var="item" items="${AllAssessList}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td style="color:#0288d1">${item.assessType}</td>
					<td>${item.avgScore}</td>
					<td>${item.level}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

	<div id="role">
		<h4>《学生评语：》</h4>
		<c:forEach var="item" items="${assessList}" varStatus="status">
			<fieldset id="bigContent"
				style="width: 500px; height: 100px; margin-top: 20px; border-radius: 10px">
				<legend style="color: #0288d1">${item.stuName}:</legend>
				<div id="content">${item.advisement}</div>
			</fieldset>
		</c:forEach>
	</div>
	<hr />

	<div id="role">
		<h4>《同行评价：》</h4>

		<c:forEach var="item" items="${tea_assessList}" varStatus="status">
			<fieldset id="bigContent"
				style="width: 500px; height: 100px; margin-top: 20px; border-radius: 10px">
				<legend style="color: #0288d1">${item.teaName}:</legend>
				<div id="content">${item.advisement}</div>
			</fieldset>
		</c:forEach>
	</div>
	<hr />

	<div id="role">
		<h4>《专家评价：》</h4>
		<c:forEach var="item" items="${lea_assessList}" varStatus="status">
			<fieldset id="bigContent"
				style="width: 500px; height: 100px; margin-top: 20px; border-radius: 10px">
				<legend style="color: #0288d1">${item.leaName}:</legend>
				<div id="content">${item.advisement}</div>
			</fieldset>
		</c:forEach>
	</div>
</body>

<script type="text/javascript">
	$(function($) {

		var limitLen = $("#content").text().length;
		if (limitLen >= 53) {
			$("#bigContent").css("width", "1000px");
		}
	});
</script>
</html>