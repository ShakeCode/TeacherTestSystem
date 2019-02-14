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
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/ajaxfileupload.js"></script>
  <link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css" rel="stylesheet">
  <link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css" rel="stylesheet">
</head>
<body>
  <div class="demo-content">
     <div class="row">
      <div class="span20 doc-content">
         <div class="form-horizontal well">
         	<!-- <form action="/UploadFile.action" method="POST" enctype="multipart/form-data">
			    <p>上传文件：</><br/>
			    <input type="file" name="file" id="file" />
			    <input type="submit" id ="upload" value = "上传" style="margin-left: 10px"/>
          </form> -->
          <div>  
           <div id="loading" style="display: none;">正在上传.....</div>
		    用户信息：
		    <br /> 姓名：
		    <input id="name" name="name" type="text">
		    <br /> 附件：
		    <input id="fileToUpload" name="file" type="file" class="input" style="background-color: #0288d1">
		    <br />
		    <input type="button" id="upload"  value="上传" style="background-color: #0288d1">
		    <br />
   		  </div> 
    <div id="result"></div>
          </div>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
 $(function ($){
	 $("#upload").click(function(){
	       $('#loading').show();
           $.ajaxFileUpload({
               url : '/uploader.action',
               secureuri : false,
               fileElementId : 'fileToUpload',
               dataType : 'json',//此时指定的是后台需要返回json字符串,前端自己解析,可以注释掉.后台直接返回map
               data : {
                   name : $('#name').val()
               },
               success : function(data) {
               	if(data.status == 1){
               		 $.notify("上传成功");
                        $('#loading').hide();
               	}else if(data.status == 0){
               		 $.notify(data.message);
               		$('#loading').hide();
               	}
               	
               },
               error : function(data) {
                   $('#loading').hide();
                   //这里处理的是网络异常，返回参数解析异常，DOM操作异常  
                   $.notify("上传发生异常");
               }
           })
	 })
}); 
  
</script>
</html>