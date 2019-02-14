<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生评价老师</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Js/notify.js"></script>
<!-- <script type="text/javascript" src="http://g.alicdn.com/sj/dpl/1.5.1/js/sui.min.js"></script> -->
<script type="text/javascript" src="http://g.alicdn.com/sj/sui-editor/0.0.2/sui-editor.config.js"></script>
<script type="text/javascript" src="http://g.alicdn.com/sj/sui-editor/0.0.2/editor/js/sui-editor.js"></script>

<link href="http://g.alicdn.com/sj/sui-editor/0.0.2/editor/css/sui-editor.css" rel="stylesheet" type="text/css" />
<style>
*{margin:0;padding:0;outline:0;}
html{font-size:62.5%;}
body{font-size:1.4rem;color:#666;font-family:"Helvetica";}
li{list-style:none;}
h2{font-size:1.7rem;font-weight:normal;color:#333;}
input[type="radio"], input[type="checkbox"]{display:block;float:left;}
input[type="radio"]{border:1px solid #999;border-radius:50%;width:1.2rem;height:1.2rem;margin:6px 10px 0 0;}
.questionnaire{
  margin-left: 30px;
}
.content{
 margin-top: 20px;
}
.content li{width:15%;height:2.2rem;}
.content ul{overflow:hidden;margin-top:0.5rem;line-height:2.4rem;}
.content_seven li{width:100%;}
input[type="checkbox"]{width:1.2rem;height:1.2rem;margin:6px 10px 0 0;}

#login_click{ margin-top:32px; height:40px;}  
#login_click a   
{  
    text-decoration:none;  
    background:#2f435e;  
    color:#f2f2f2;  
      
    padding: 10px 30px 10px 30px;  
    font-size:16px;  
    font-family: 微软雅黑,宋体,Arial,Helvetica,Verdana,sans-serif;  
    font-weight:bold;  
    border-radius:3px;  
      
    -webkit-transition:all linear 0.30s;  
    -moz-transition:all linear 0.30s;  
    transition:all linear 0.30s;  
      
    }  
   #login_click a:hover { background:#385f9e; }  
</style>
</head>
<body>
<div class="demo-content">
<div style="margin:10px 0px 0px 900px;color: red">正在对<%=request.getAttribute("tea_name") %>老师进行评价哦,请如实评价！！</div>
<input type="hidden" value="${tea.tea_id}" id="tea_id"/> 
<input type="hidden" value="${stu_no}" id="stu_no"/> 
<div class="questionnaire">
    <div class="content">
        <h2>1、你认为该教师的责任心强不强?</h2>
        <ul>
            <li><label><input type="radio" name="radio1" value="A"><span>A.责任心强</span></label></li>
            <li><label><input type="radio" name="radio1" value="B"><span>B.责任心一般</span></label></li>
            <li><label><input type="radio" name="radio1" value="C"><span>C.责任心一般</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content">
        <h2>2.该教师是否关爱学生?</h2>
        <ul>
            <li><label><input type="radio" name="radio2" value="A"><span>A.十分关爱</span></label></li>
            <li><label><input type="radio" name="radio2" value="B"><span>B.关爱</span></label></li>
            <li><label><input type="radio" name="radio2" value="C"><span>C.不关爱</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content">
        <h2>3.该教师上课有无不良现象，如吸烟、接打手机、迟到、早退、无故不上课等?</h2>
        <ul>
            <li><label><input type="radio" name="radio3" value="A"><span>A.没有</span></label></li>
            <li><label><input type="radio" name="radio3" value="B"><span>B.有时有</span></label></li>
            <li><label><input type="radio" name="radio3" value="C"><span>C.经常有</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content">
        <h2>4.该教师能否在主动和你交流和指导?</h2>
        <ul>
            <li><label><input type="radio" name="radio4" value="A"><span>A.有</span></label></li>
            <li><label><input type="radio" name="radio4" value="B"><span>B.有时有</span></label></li>
            <li><label><input type="radio" name="radio4" value="C"><span>C.没有</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content">
        <h2>5.该教师能否在课上提供同学之间的交流?</h2>
        <ul>
            <li><label><input type="radio" name="radio5" value="A"><span>A.经常提供这样的机会</span></label></li>
            <li><label><input type="radio" name="radio5" value="B"><span>B.有时提供这样的机会</span></label></li>
            <li><label><input type="radio" name="radio5" value="C"><span>C.从来不提供这样的机会</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content content_seven">
        <h2>6.对该教师的课堂教学感兴趣吗?</h2>
        <ul>
            <li><label><input type="radio" name="radio6" value="A"><span>A.很感谢兴趣</span></label></li>
            <li><label><input type="radio" name="radio6" value="B"><span>B.一般</span></label></li>
            <li><label><input type="radio" name="radio6" value="C"><span>C.不感兴趣</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content content_seven">
        <h2>7.该教师的课程是否丰富且很有吸引力？</h2>
        <ul>
            <li><label><input type="radio" name="checkbox" value="A"><span>A.很有意思</span></label></li>
            <li><label><input type="radio" name="checkbox" value="B"><span>B.一般</span></label></li>
            <li><label><input type="radio" name="checkbox" value="C"><span>C.没有兴趣</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content content_seven">
        <h2>8.该老师是否在每次上课前认真备课并有针对性？</h2>
        <ul>
            <li><label><input type="radio" name="checkbox1" value="A"><span>A.课程组织合理</span></label></li>
            <li><label><input type="radio" name="checkbox1" value="B"><span>B.教案简单，内容一般</span></label></li>
            <li><label><input type="radio" name="checkbox1" value="C"><span>C.照本宣科，没有帮助</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content content_seven">
        <h2>9.该老师的教学表达是否让你清晰明白？</h2>
        <ul>
            <li><label><input type="radio" name="radio8" value="A"/><span>A.很清晰</span></label></li>
            <li><label><input type="radio" name="radio8" value="B"/><span>B.一般清晰</span></label></li>
            <li><label><input type="radio" name="radio8" value="C"/><span>C.不清晰</span></label></li>
        </ul>
    </div>
    <hr/>
     <div class="content content_seven">
        <h2>10.对该老师的教学质量整体满意长度？</h2>
        <ul>
            <li><label><input type="radio" name="radio9" value="A"/><span>A.很满意</span></label></li>
            <li><label><input type="radio" name="radio9" value="B"/><span>B.一般满意</span></label></li>
            <li><label><input type="radio" name="radio9" value="C"/><span>C.非常不满意</span></label></li>
        </ul>
    </div>
<hr/>
    <h3 style="margin: 20px 0px 10px 0px">11.填写对老师意见或建议：</h3>
  <div class="edui-container" style="width: 1024px; z-index: 999;">
	<div class="edui-editor-body">
		<!-- <div class="edui-body-container" contenteditable="true" style="width: 1004px; min-height: 200px; z-index: 999;">
		<p><br></p>
		</div> -->
    <textarea id="simple" style="width: 1024px; height: 200px; display: none;"></textarea>
    </div>
  </div>
  
    <div id="login_click" style="margin-left: 100px">  
        <a id="btlogin" href="#">提  交</a>  
        </div>  
  </div> 
</body>
<script type="text/javascript">
    $(function($){
    	//初始化富文本编辑器
    	var $editor = $('.edui-container').editor();
    	var flag = 1;
/*     	   $(".content input[name^='radio']").click(function(){    //input[name='radio'] 单选按钮只要被点击且不管你点它多少次  他的选中状态都是true        
    	        $(this).parent("li").addClass("checked").siblings("li").removeClass("checked").parents(".content").attr("data-id","checkBox");               

    	        var contentLen = $(".content").length;
    	        var checkLen = $("div[data-id='checkBox']").length;

    	        checked(contentLen,checkLen);
    	    });

    	    $(".content_checkbox").each(function(){
    	        var self = $(this);
    	        $(this).find("input[name^='checkbox']").click(function(){
    	            if(this.checked == true){            
    	                $(this).parent("li").addClass("checked");               
    	            }else{
    	                $(this).parent("li").removeClass("checked");
    	            }

    	            if(self.find("li").hasClass("checked")){
    	                self.attr("data-id","checkBox");
    	            }else{
    	                self.removeAttr("data-id");
    	            }

    	            var contentLen = $(".content").length;
    	            var checkLen = $("div[data-id='checkBox']").length;

    	            checked(contentLen,checkLen);
    	        });

    	        
    	    });

    	function checked(contentLen,checkLen){
    	    if(contentLen == checkLen){
    	       $("#submit").css({"background":"#3b7ded"}).removeAttr("disabled");
    	    }else{
    	       $("#submit").css({"background":"#9f9f9f"}).attr("disabled","disabled");
    	    }
    	} */
    	
    	  $("#btlogin").click(function(){
    		  if(!flag){
    			  $.notify("你已评价过该老师！请返回评价");
    		  }
    			 /*  if(checkChecked() == false){
        			  $(window).scrollTop(0);
        			  return;
        		  } */
        		  var chkVal = [];
        		  $("input[type='radio']:checked").each(function() {
        	            chkVal.push($(this).val());
        	        });
        		  //alert($editor.editor('getContent').length);
        		  if($editor.editor('getContent').length > 850){
        			  //alert($editor.editor('getContent').length);
        			  $.notify("最大输入100个字符");
        			  return false;
        		  }
        		  
        		  debugger
        		 $.ajax({
        				type:"post",
        				dataType:"json",
        				data:{"stu_no":$("#stu_no").val(),"tea_id":$("#tea_id").val(),"answer":chkVal.join(","),"advise": $editor.editor('getContent')},
        				url:"/save/AcessTeacher/Answer",
        				async:true,
        				success:function(data){
        					debugger
        					if(data.status == 0){
        						$.notify("评论失败");
        					}else{
        						$.notify("评论成功！");
        						/* $("#btlogin").attr("disabled",false);
        						$('#btlogin').removeAttr('onclick');//去掉a标签中的onclick事件 */
        						flag = 0;
        						$("#btlogin").unbind("click");
        					}
        				},
        				error:function(error){
        					
        				}, 
        		 })  
    		  
    	  });
    	  
    	//检查是否全部答案写完
    	  var checkChecked = function(){
    		  var check = 1;
    		if(flag){
 	    		debugger
 	    		$("ul").each(function(){
 	    			if($(this).find("input[type='radio']:checked").val()== undefined){
 	    				$.notify("请填写全部答案！");
 	    				 check =0;
 	    		}
 	    		})
    		  if($editor.editor('getContent') == ""){
    			  $.notify("请填写全部答案！");
    			  check =0;
    		  }
    		}else{
    			check = 0;
    		}
    		return check;
    	  }
    });
</script>

</html>


