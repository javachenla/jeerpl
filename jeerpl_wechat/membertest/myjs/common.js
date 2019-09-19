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