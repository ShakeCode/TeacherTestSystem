<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>领导导入文件</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/Css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/Css/fileinput.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/Css/theme.min.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/Js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/notify.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/Js/sortable.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/Js/fileinput.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/Js/zh.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/Js/theme.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/Js/bootstrap.min.js"></script>
</head>
<body>
<form enctype="multipart/form-data" style="margin:10px 0px 0px 10px">
			<div class="form-group">
					<input  id="uploadfile"  class="file"  multiple="multiple" type="file" />
			</div>
		<!-- <div class="form-group">
		       <input type="file" name="uploadfile" id="uploadfile" multiple="multiple" class="file file-loading" />
        </div> -->
</form>
</body>

<script type="text/javascript">
$("#uploadfile").fileinput({
    language: 'zh', //设置语言
    uploadUrl: "/file_upload", //上传的地址
    allowedFileExtensions: ['jpg', 'png', 'gif','xls','xlsx'],//接收的文件后缀
    uploadAsync: true, //默认异步上传
/*     // showUpload: true, //是否显示上传按钮
    
    //showRemove : true, //显示移除按钮
    showPreview : true, //是否显示预览
    showCaption: false,//是否显示标题
    browseClass: "btn btn-primary", //按钮样式     */
    dropZoneEnabled: false,//是否显示拖拽区域  
    maxFileCount: 1, //表示允许同时上传的最大文件个数
    enctype: 'multipart/form-data',
    validateInitialCount:true,
	overwriteInitial: false,
	maxFileSize: 100000,    //按照kb计算
	maxFilesNum: 10,
	//allowedFileTypes: ['image', 'video', 'flash'],
	slugCallback: function(filename) {
		return filename.replace('(', '_').replace(']', '_');
	}
});
		
//异步上传返回结果处理
$("#uploadfile").on("fileuploaded", function (event, data, previewId, index) {
        var response = data.response;
        $.notify("上传成功");
    });
</script>
</html>