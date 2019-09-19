package com.kejin.mytest.wechat.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kejin.mytest.bean.ResultData;
import com.kejin.mytest.util.WxUtil;
import com.kejin.mytest.wechat.config.WxMpProperties;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@RestController
@RequestMapping("/mywechat")
public class MyWechatController {

    @Autowired
    private WxMpProperties properties;
    

    @Autowired
    private WxMpService wxService;

    @ResponseBody
    @RequestMapping(value="/getWxUserInfo",method = RequestMethod.GET)
    public ResultData openid(@RequestParam(value="code",required=false) String code,
    		@RequestParam(value="reUrl",required=false) String reUrl) throws Exception {
    	
    	    String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+properties.getAppId();;

	    	if(StringUtils.isNoneBlank(code)){
    		ResultData resData=new ResultData();
    		
    		WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
            try {
                wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
            } catch (WxErrorException e) {
                throw new Exception(e.getError().getErrorMsg());
            }
             
            String openId = wxMpOAuth2AccessToken.getOpenId();
     
            WxMpUser wxMapUser=wxService.getUserService().userInfo(openId);
            
       	    resData.setResultSuccess(wxMapUser);
       	    return resData;
    	}else if(StringUtils.isNoneBlank(reUrl)){
    		String reUrll=url+"&redirect_uri="+URLEncoder.encode(reUrl,"utf8")+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
			return new ResultData(201,"",reUrll);	
    	}else{
    		 ResultData resData=new ResultData();
    		 resData.setResultError("出错了");
    		 return resData;
    	}
    	
    }
    
    @ResponseBody
    @RequestMapping(value="/sign",method = RequestMethod.GET)
    public ResultData sign(@RequestParam(value="url") String url,
    		@RequestParam(value="t") String t) throws Exception {
    	
    	Long timestamp = System.currentTimeMillis() / 1000;
    	String nonceStr =WxUtil.randomStr(10);
    	//获取access_token
    	//根据access_token 获取js-ticket  
    	String jsapi_ticket= wxService.getJsapiTicket();
    	/*if(StringUtils.isBlank(jsapi_ticket)){
    		return new WxRtnMap(2,"获取js-tikect出错");
    	}*/
    	System.out.println("jsapi_ticket is ==>"+jsapi_ticket);
    	
        Map<String ,Object> rsMap = new HashMap<String ,Object>();
        rsMap.put("appid", properties.getAppId());
        rsMap.put("timestamp", timestamp);
        rsMap.put("nonceStr", nonceStr);
        rsMap.put("signature", WxUtil.getSign(jsapi_ticket,nonceStr,timestamp,url));
        
        ResultData resData=new ResultData();
        resData.setResultSuccess(rsMap);
    
        return resData;
    }
    
    @ResponseBody
    @RequestMapping(value="/saveImg",method = RequestMethod.POST)
    public ResultData saveImg(@RequestParam(value="serverId",required=true) String serverId) throws Exception {
    	System.out.println("serverId==>"+serverId);
    	File file = wxService.getMaterialService().mediaDownload(serverId);
    	String fileName="imgact/"+WxUtil.randomStr(6)+".jpg";
    	WxUtil.saveImageToDisk(file,"d:/"+fileName);
     
    	Map<String ,Object> rsMap = new HashMap<String ,Object>();
    	rsMap.put("imgUrl", fileName);
    	rsMap.put("servId", serverId);
    	
        ResultData resData=new ResultData();
        resData.setResultSuccess(rsMap);
        return resData;
        
    }  
    
	
    

}
