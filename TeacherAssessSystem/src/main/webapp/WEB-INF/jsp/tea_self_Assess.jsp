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
<input type="hidden" value="${tea_no}" id="tea_no"/> 
<div class="questionnaire">
    <div class="content">
        <h2>1、教学内容符合科学性与理论性?</h2>
        <ul>
            <li><label><input type="radio" name="radio1" value="A"><span>A.很符合</span></label></li>
            <li><label><input type="radio" name="radio1" value="B"><span>B.一般符合</span></label></li>
            <li><label><input type="radio" name="radio1" value="C"><span>C.稍微符合</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content">
        <h2>2.课堂上是否做到加强培养学生发现、分析、解决问题的能力方面?</h2>
        <ul>
            <li><label><input type="radio" name="radio2" value="A"><span>A.是</span></label></li>
            <li><label><input type="radio" name="radio2" value="B"><span>B.一般</span></label></li>
            <li><label><input type="radio" name="radio2" value="C"><span>C.没有</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content">
        <h2>3.课堂上是否培养学生创造性思维的能力?</h2>
        <ul>
            <li><label><input type="radio" name="radio3" value="A"><span>A.有</span></label></li>
            <li><label><input type="radio" name="radio3" value="B"><span>B.有时有</span></label></li>
            <li><label><input type="radio" name="radio3" value="C"><span>C.没有</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content">
        <h2>4.是否培养了学生主动学习的能力?</h2>
        <ul>
            <li><label><input type="radio" name="radio4" value="A"><span>A.有</span></label></li>
            <li><label><input type="radio" name="radio4" value="B"><span>B.有时有</span></label></li>
            <li><label><input type="radio" name="radio4" value="C"><span>C.没有</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content">
        <h2>5.能否在课上提供同学之间的交流与讨论的机会?</h2>
        <ul>
            <li><label><input type="radio" name="radio5" value="A"><span>A.经常提供这样的机会</span></label></li>
            <li><label><input type="radio" name="radio5" value="B"><span>B.有时提供这样的机会</span></label></li>
            <li><label><input type="radio" name="radio5" value="C"><span>C.从来不提供这样的机会</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content content_seven">
        <h2>6.课程是否把理论与实践相结合？</h2>
        <ul>
            <li><label><input type="radio" name="radio6" value="A"><span>A.有</span></label></li>
            <li><label><input type="radio" name="radio6" value="B"><span>B.一般</span></label></li>
            <li><label><input type="radio" name="radio6" value="C"><span>C.没有</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content content_seven">
        <h2>7.课程内容是否丰富且很有吸引力？</h2>
        <ul>
            <li><label><input type="radio" name="radio7" value="A"><span>A.是</span></label></li>
            <li><label><input type="radio" name="radio7" value="B"><span>B.一般</span></label></li>
            <li><label><input type="radio" name="radio7" value="C"><span>C.没有吸引力</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content content_seven">
        <h2>8.课程的备课情况如何？</h2>
        <ul>
            <li><label><input type="radio" name="radio8" value="A"><span>A.课程备课充分</span></label></li>
            <li><label><input type="radio" name="radio8" value="B"><span>B.备课内容一般</span></label></li>
            <li><label><input type="radio" name="radio8" value="C"><span>C.备课内容很差</span></label></li>
        </ul>
    </div>
    <hr/>
    <div class="content content_seven">
        <h2>9.教学表达方式清晰程度？</h2>
        <ul>
            <li><label><input type="radio" name="radio9" value="A"/><span>A.很清晰</span></label></li>
            <li><label><input type="radio" name="radio9" value="B"/><span>B.一般清晰</span></label></li>
            <li><label><input type="radio" name="radio9" value="C"/><span>C.不清晰</span></label></li>
        </ul>
    </div>
    <hr/>
     <div class="content content_seven">
        <h2>10.教学质量整体满意长度？</h2>
        <ul>
            <li><label><input type="radio" name="radio10" value="A"/><span>A.很满意</span></label></li>
            <li><label><input type="radio" name="radio10" value="B"/><span>B.一般满意</span></label></li>
            <li><label><input type="radio" name="radio10" value="C"/><span>C.非常不满意</span></label></li>
        </ul>
    </div>
<hr/>
  
    <div id="login_click" style="margin-left: 100px">  
        <a id="btlogin" href="#">提  交</a>  
        </div>  
  </div> 
</body>
<script type="text/javascript">
    $(function($){
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
    		  debugger
    		  if(checkChecked()== false){
    			  return;
    		  }
    			  if(flag == false){
    				 // alert("你已经评论过了,勿重复操作");
    				  $.notify("你已经评论过了,勿重复操作");
        			  $(window).scrollTop(0);
        			  return;
        		  }
        		  var chkVal = [];
        		  $("input[type='radio']:checked").each(function() {
        	            chkVal.push($(this).val());
        	        });
        		  debugger
        		 $.ajax({
        				type:"post",
        				dataType:"json",
        				data:{"tea_no":$("#tea_no").val(),"answer":chkVal.join(",")},
        				url:"/teacher/self/Assess/save",
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
    		if(flag){
    			var check = 1;
        		debugger
        		$("ul").each(function(){
        			if($(this).find("input[type='radio']:checked").val()== undefined){
        				$.notify("请填写全部答案！");
        				 check =0;
        		}
        		})
        		return check;
    		}
    	  }
    });
</script>

</html>


