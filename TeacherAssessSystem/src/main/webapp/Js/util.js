Util = {
	notifySuccess : function(text) {
		Util.notify(text, "success", "top");
	},
	notifyError : function(text) {
		Util.notify(text, "error", "center");
	},
	notifyInfo : function(text) {
		Util.notify(text, "information", "center");
	},
	notifyBottomRight : function(text) {
		Util.notify(text, "text", "bottomRight", "fadeOut", 999999999);
	},
	ajaxRanderTag : function(params) {
		if (!params.targetId) {
			Util.notifyError("数据加载失败，缺少渲染对象！");
			return;
		}
		var data = params.data;
		if (!data && params.formId) {
			data = $("#" + params.formId).serialize();
		}
		$.ajax({
			type : "POST",
			url : params.url,
			async : true,
			data : data,
			beforeSend : function() {
				Util.loadingPage(1);
			},
			error : function() {
				$("#" + params.targetId).html("数据加载失败,请稍后重试！");
			},
			success : function(data) {
				try {
					$("#" + params.targetId).html(data);
				} catch (e) {
					console.log(data);
					Util.notifyError("页面加载失败！");
				}
				if (params.callback) {
					params.callback();
				}
			},
			complete : function() {
				Util.loadingPage(0);
			}
		});
	},


	setIframeHeight : function(obj) {
		var win = obj;
		if (document.getElementById) {
			if (win && !window.opera) {
				if (win.contentDocument && win.contentDocument.body.offsetHeight)
					win.height = win.contentDocument.body.offsetHeight + 15;
				else if (win.Document && win.Document.body.scrollHeight)
					win.height = win.Document.body.scrollHeight+15;
			}
		}
	},
	json2str : function(o) {
		return JSON.stringify(o);
	},
	str2json : function(str) {
		var jsonObj;
		try {
			jsonObj = eval("(" + str + ")");
		} catch (e) {
			jsonObj = str;
		}
		return jsonObj;
	},
	arrayToJson : function(arrayName,arrayInfo){
		var map = new Map();
		if (arrayInfo){
			$(arrayInfo).each(function(index,value){
				for(var k in value){
					map.put(arrayName + "[" + index + "]." + k ,value[k]);
				}
			})
		}
		return map.data;
	},
	arrayToMap : function(arrayName,arrayInfo){
		var map = new Map();
		if (arrayInfo){
			$(arrayInfo).each(function(index,value){
				for(var k in value){
					map.put(arrayName + "[" + index + "]." + k ,value[k]);
				}
			})
		}
		return map;
	},
	mergeJson : function(json1,json2){
		var map = new Map();
		if (json1){
			for (var key in json1){
				map.put(key,json1[key]);
			}
		}
		if (json2){
			for (var key in json2){
				map.put(key,json2[key]);
			}
		}
		return map.data;
	},
	deleteReturnKey : function(obj) {
		var reg = new RegExp("\r\n", "g");
		var a = obj.replace(reg, "");
		var reg2 = new RegExp("\n", "g");
		var b = obj.replace(reg2, "");
		// for IE9
		if (a.length > b.length) {
			obj = obj.replace(reg2, "");
		}
		// for IE7/8
		else if (a.length < b.length) {
			obj = obj.replace(reg);
		}
		return obj;
	},


	getPageByAjax : function(url, param) {
		return Util.ajax({
			type : "POST",
			dataType : "html",
			url : url,
			data : param,
			async : false
		}).responseText;
	},
	isBlank : function(str) {
		if (parseFloat(str) || parseFloat(str) == 0) {
			return false;
		} else {
			var temp = Util.trimSBCcase(Util.trim(str));
			if (temp.length == 0) {
				return true;
			}
			return false;
		}
	},
	trim : function(str) {
		if (parseFloat(str) || parseFloat(str) == 0) {
			return str;
		} else {
			str = (str || "").replace(/^\s\s*/, '');
			var ws = /\s/, i = str.length;
			while (ws.test(str.charAt(--i)))
				;
			return str.slice(0, i + 1);
		}
	},
	byteLength : function(str) {
		return (str || "").replace(/[^\x00-\xff]/g, "**").length;
	},
	/**
	 * 将某个字符串两边的全角空格过滤掉
	 */
	trimSBCcase : function(str) {
		if (parseFloat(str) || parseFloat(str) == 0) {
			return str;
		} else {
			return (str || "").replace(/(^[\s\u3000]*)|([\s\u3000]*$)/g, "");
		}
	},
	/**
	 * 对传入的字符串进行截取,只适用于GBK编码,如最后一个字符为汉字，正好超出长度，则舍去该汉字 最后显示结果为xxxxxx...
	 */
	adjustStr : function(str, n) {
		if (Util.byteLength(str) < n) {
			return str;
		}
		var destination = [];
		var currentLength = 0;
		for ( var i = 0, j = (str || "").length; i < j; i++) {
			var temp = str.charAt(i);
			currentLength += Util.byteLength(temp);
			if (currentLength <= n) {
				destination[i] = temp
			}
		}
		destination.push('...');
		return destination.join('');
	},
	formatDateStrToMSEC : function(dateStr) {
		if (dateStr) {
			return new Date(dateStr.replace(/-/g, "/")).getTime();
		} else {
			return 0;
		}
	},
	/**
	 * 格式化时间 第一个参数为时间 第二个参数为格式化方式 如 "yyyy-MM-dd HH:mm:ss"
	 */
	formatDate : function(date, str) {
		var df = new SimpleDateFormat();// jsJava1.0需要使用DateFormat对象，不要弄错就是了
		df.applyPattern(str);
		return str = df.format(date);
	},

	closeShortPermit : function() {
		$("#shortPermitionWin").animate({
			height : 'hide'
		}, function() {
			$("#shortPermitionWin_cover").fadeOut(500, function() {
				$("#shortPermitionWin").remove();
			});
		});
	},

	notify : function(text) {
		if (!window.tips){
			window.tips = new showtips();
		}
		tips.show(text);
	},


	isSessionKeyExpire : function(){
		var serverTime = null;
		Util.ajax({
			url : "/ajax!getCurrentDate.action?" + "t_" + new Date().getTime(),
			async : false,
			type : "POST",
			dataType : 'json',
			success : function(json) {
				var status = json.status;
				if (status == 1) {
					serverTime = json.data.serverTime;
				} 
			}
		});
		if(Util.isBlank(serverTime) || typeof(serverTime) == "undefined"){
			serverTime = new Date().getTime();
		}
		return (Number(serverTime) - Number(window.customer.sessionKeyExpireTime)) > 0;
	},


	// 验证非负数
	checkPrice : function(obj) {
		var regExp = /^\d{0,8}\.{0,1}(\d{1,2})?$/;
		if (!regExp.exec(obj.value)) {
			obj.value = '';
		}
	},
	// 验证非负整数
	checkNonzeroInteger : function(obj) {
		var regExp = /^[0-9]\d*$/;
		if (!regExp.exec(obj.value)) {
			obj.value = '';
		}
	},
	// 验证正整数
	checkPositiveInteger : function(obj) {
		var regExp = /^[1-9]\d{0,3}?$/;
		if (!regExp.exec(obj.value)) {
			obj.value = '';
		}
	},
	checkDiscount : function(obj) {
		var regExp = /^[1-9](\.[1-9])?|0\.[1-9]$/;
		if (!regExp.exec(obj.value)) {
			obj.value = '';
		}
	},

	track : function(trackId) {
		if (trackId) {
			Util.ajax({
				url : "/ajax!doTrack.action?isNewVersion=1&trackId=" + trackId + "&t_" + new Date().getTime(),
				async : true,
				type : "POST",
				dataType : 'json'
			});
		}
	},




	toTop : function() {
		document.body.scrollLeft = 0;
		document.body.scrollTop = 0;
	},
	

	setCookie : function(key,value,days)
	{
	    var Days = 90; // 此 cookie 将被保存90 天
	    if(days){
	    	Days = days;
	    }
	    var exp  = new Date();    // new Date("December 31, 9998");
	    exp.setTime(exp.getTime() + Days*24*60*60*1000);
	    document.cookie = encodeURI(key) + "="+ encodeURI(value) + ";expires=" + exp.toGMTString() + "; path=/";

	},
	delCookie : function(key)
	{
	    var exp = new Date();
	    exp.setTime(exp.getTime() - 1);
	    var cval = Util.getCookie(key);
	    if(cval != null) document.cookie= encodeURI(key) + "="+encodeURI(cval)+";expires="+exp.toGMTString();
	},
	getCookie : function(key)
	{
		key = encodeURI(key);
		var value;
		var allcookies = document.cookie;
		var cookie_pos = allcookies.indexOf(key);

		// 如果找到了索引，就代表cookie存在，
		// 反之，就说明不存在。
		if (cookie_pos != -1)
		{
			// 把cookie_pos放在值的开始，只要给值加1即可。
			cookie_pos += key.length + 1;
			var cookie_end = allcookies.indexOf(";", cookie_pos);
	
			if (cookie_end == -1)
			{
				cookie_end = allcookies.length;
			}
			value = decodeURI(allcookies.substring(cookie_pos, cookie_end));
		}
		return value;
	},


	stopDefault : function(e) { 
	     if (e && e.preventDefault) {
	    	 e.preventDefault(); 
	    	 e.stopPropagation();
    	 }else {
    		 window.event.returnValue = false; 
    		 window.event.cancelBubble=true;
    	 }
	    return false; 
	},



	
}
