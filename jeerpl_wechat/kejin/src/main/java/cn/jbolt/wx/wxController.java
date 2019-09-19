package cn.jbolt.wx;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Redis;

import cn.jbolt.utils.HttpClientUtils;
import cn.jbolt.wx.model.WxReurnMap;
import net.sf.json.JSONObject;
 
public class wxController extends Controller {

	   public static String TOKEN = "pulian"; // 这里填写自己的 token
	   
	   public static String APPID = "wxe57479974ff166ee"; // 这里填写自己的 token APPID
	   
		public static final String APPSECRET  = "3c3497ddf99eb1316290a49a60061898";    
		
	    public void bind() throws Exception {
	    	
	    	String result="";
	    	if( getRequest().getMethod().equals("GET")) {
	    		result= singGet();
	    	}
	    	if( getRequest().getMethod().equals("POST")) {
	    		result= singPost();
	    	}
			renderJson(result);
	       
	    }

	    public String singGet() {
			String sign=getPara("signature");
	    	String timestamp=getPara("timestamp");
	    	String nonce=getPara("nonce");
	    	String echostr=getPara("echostr");
	        
	        List<String> list = new ArrayList<String>();
	        
	        list.add(nonce);
	        list.add(TOKEN);
	        list.add(timestamp);
	        
	        Collections.sort(list);
	        String hash = getHash(list.get(0)+list.get(1)+list.get(2), "SHA-1");
	        if(sign.equals(hash)){ // 验证下签名是否正确
	            return echostr;
	        }else{
	            return "";
	        }
		}
		
	    public String singPost() {
			  HttpServletRequest request=getRequest();
			  try {
		            
		            String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect ";
		            String redirectUrl="http://www.badqh.cn/jeeplus_union/WxUnion/index.html";
		            url=url.replace("APPID",APPID).replace("REDIRECT_URI", redirectUrl);
		            
		            String resAHref="<a href='"+url+"'>登录</a>";
		            System.out.println(resAHref);
		            Map<String,String> requestMap=parseRequest(request.getInputStream());
		            String responseXml="<xml><ToUserName><![CDATA["+requestMap.get("FromUserName")+"]]></ToUserName><FromUserName><![CDATA["+requestMap.get("ToUserName")+"]]></FromUserName><CreateTime>"+System.currentTimeMillis()/1000+"</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA["+resAHref+"]]></Content></xml>";
		            return responseXml;
		        }catch (Exception e) {
		            e.printStackTrace();
		        }
		        return "";
		}

	    public  String getHash(String source, String hashType) {
	        StringBuilder sb = new StringBuilder();
	        MessageDigest md5;
	        try {
	            md5 = MessageDigest.getInstance(hashType);
	            md5.update(source.getBytes());
	            for (byte b : md5.digest()) {
	                sb.append(String.format("%02x", b));
	            }
	            return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	    public static Map<String, String> parseRequest(InputStream in) throws DocumentException {
	        Map<String,String> map=new HashMap<String, String>();
	           SAXReader reader = new SAXReader();
	            Document document = reader.read(in);
	            Element root = document.getRootElement();
	            Iterator it = root.elementIterator();
	            while (it.hasNext()) {
	                Element element = (Element) it.next();
	                map.put(element.getName(), element.getStringValue());
	            }
	        return map;
	    }
	    
	    //获取用户的openid
	    public  void openid() throws Exception   {
	    	   Map<String ,Object> resultMap = new HashMap<String ,Object>();
		     
     	       String code=getPara("code");
	    	   String reUrl=getPara("reUrl");
	    	   
	    	   String openid="";
	    	
	    	      if(StringUtils.isNotEmpty(code)){
	    	    	  String wxUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		    		   wxUrl=wxUrl.replace("APPID",APPID).replace("SECRET", APPSECRET).replace("CODE", code);
		    		   HttpClientUtils   httpUtils =  new HttpClientUtils();
			            //解析成Json格式
			           JSONObject jsonObject=JSONObject.fromObject(httpUtils.doGet(wxUrl));
			           
		    		   openid = jsonObject.getString("openid");
		    		   resultMap.put("openid", openid);
		    		   
		    		   Redis.use("gc_wx").setex(openid+"_access_token", 7200,jsonObject.getString("access_token"));
		    		   Redis.use("gc_wx").setex(openid+"_refresh_token", 108000,jsonObject.getString("refresh_token"));
		    		   renderJson( new WxReurnMap(1,resultMap));   //操作码2001为跳转reUrl     
			        }else if(StringUtils.isNotEmpty(reUrl)){
			        	   resultMap.put("reUrl", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect".replace("APPID",APPID).replace("REDIRECT_URI",URLEncoder.encode(reUrl,"utf8")));
			    		   renderJson( new WxReurnMap(1,resultMap,2001));   //操作码2001为跳转reUrl
			        } 
	    	      
	    	      
	    }
	    
	    //获取access_token的方法 
	    public  String getAccessToken(String openid) throws Exception   {
	    	
	    	  String openid_access_token= Redis.use("gc_wx").get(openid+"_access_token");
	    	  
	    	  if(StringUtils.isNotEmpty(openid_access_token)) {
	    		  return  openid_access_token;
	    	  }else {
	    		 
	    		  String flushAccessTokenUrl="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	  	    	
		    	  String code=getPara("code");
		    	
		    	  Map<String ,Object> resultMap = new HashMap<String ,Object>();
			     
	  	        
	  	          String refresh_token=  Redis.use("gc_wx").get(openid+"_refresh_token");
	  	        
	  	          flushAccessTokenUrl=flushAccessTokenUrl.replace("APPID",APPID).replace("REFRESH_TOKEN", refresh_token);
	  	          
		  	        HttpClientUtils   httpUtils =  new HttpClientUtils();
		            //解析成Json格式
		           JSONObject jsonObject=JSONObject.fromObject(httpUtils.doGet(flushAccessTokenUrl));
		           openid_access_token=jsonObject.getString("access_token");
				   Redis.use("gc_wx").setex(openid+"_access_token", 7200,openid_access_token);
	    	  }
	    	
	    	  return openid_access_token;
	    }
	    
	    //根据openid获取头像
	    public  void getUserInfo() throws Exception    {

	    	   Map<String ,Object> resultMap = new HashMap<String ,Object>();
	    	   
	    	   String openid=getPara("openid");
	    	   String access_token=getAccessToken(openid);
	    	   
	    	   String wxUrl=" https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN".replace("ACCESS_TOKEN",access_token).replace("OPENID",openid);
	    	   
	    	   HttpClientUtils   httpUtils =  new HttpClientUtils();
	            //解析成Json格式
	           JSONObject jsonObject=JSONObject.fromObject(httpUtils.doGet(wxUrl));
	           resultMap.put("nickname", jsonObject.getString("nickname"));
	           resultMap.put("language", jsonObject.getString("language"));
	           resultMap.put("headimgurl", jsonObject.getString("headimgurl"));
	           resultMap.put("province", jsonObject.getString("province"));
	           resultMap.put("country", jsonObject.getString("country"));
	           resultMap.put("headimgurl", jsonObject.getString("headimgurl"));
   		      
	    		   renderJson( new WxReurnMap(1,resultMap));   
	    	   }
	    }

