
	var back_url_prefix = "http://www.badqh.cn/gc";
	var img_url_prefix = "http://www.badqh.cn";
	 
	/**
	 * 设置缓存
	 * @param {Object} key  键
	 * @param {Object} value 值
	 */
    function setCache(key,value){
        var curTime = new Date().getTime();
        localStorage.setItem(key,JSON.stringify({data:value,time:curTime}));
        
        console.log(key+"==>"+localStorage.getItem(key));
    }
    
    /**
     * 获取缓存
     * @param {Object} key   键
     * @param {Object} exp   1000*60*60*24  24小时
     */
    function getCache(key,exp){
        var data = localStorage.getItem(key);
		if(!data){
			return null;
		}
        var dataObj = JSON.parse(data);
        if (new Date().getTime() - dataObj.time>exp) {
            localStorage.removeItem(key);
            //alert("信息已过期")
            return null;
        }else{
            return dataObj.data;
        }
    }    
    
    /**
     * 获取永久对象
     * @param {Object} key
     */
    function getCache(key){
        var data = localStorage.getItem(key);
		if(!data){
			return null;
		}
        var dataObj = JSON.parse(data);
        
        return dataObj.data;        
    }    
    
    /**
     * 删除缓存
     * @param {Object} key
     */
    function delCache(key){
      localStorage.removeItem(key);		  
    }    
    
    
    
    //HTML转义
	function HTMLEncode(html) {
		var temp = document.createElement("div");
		(temp.textContent != null) ? (temp.textContent = html) : (temp.innerText = html);
		var output = temp.innerHTML;
		temp = null;
		return output;
	}
	
	//HTML 反转义
	function HTMLDecode(text) { 
		var temp = document.createElement("div"); 
		temp.innerHTML = text; 
		var output = temp.innerText || temp.textContent; 
		temp = null; 
		return output; 
	} 

 
	function getTs(time){
	    var arr = time.split(/[- :]/),
	    _date = new Date(arr[0], arr[1]-1, arr[2], arr[3], arr[4], arr[5]),
	    timeStr = Date.parse(_date)
	    return timeStr
	} 