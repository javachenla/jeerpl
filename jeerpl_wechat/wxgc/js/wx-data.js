var wxdata = {
	redirect_uri: window.location.href,
	openid_url :function(){
		   return "http://www.badqh.cn/kejin/wxController/openid";
	},
	// 获取openid
	get_openid : function(){
		//若openid未过期,返回缓存的openid
//		if(localStorage.getItem("openid")!=""){
//			return localStorage.getItem("openid");
//		}

		var code = $.getUrlParam('code'); 
		alert("12332145");
		$.ajax({
			type : "GET",
			url : wxdata.openid_url(),
			data:{code:code,reUrl:wxdata.redirect_uri},
			async : false,
			success : function(rtnData) { 
				//放到缓存
			    console.log(rtnData);
                if(rtnData.optionCode==2001){
                  //跳转页面
                  window.location.href=rtnData.data.reUrl;                    
	             }else{
	            	 var opid=rtnData.data.openid+"";
	            	 debugger;
	            	 localStorage.setItem("openid",opid);
	             }
			
			},
			error : function(msg){
				alert("get openid  error!!! ");
			}
		}); 
	}
	//其他
}
