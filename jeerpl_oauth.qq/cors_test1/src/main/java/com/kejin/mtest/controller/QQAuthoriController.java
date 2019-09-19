package com.kejin.mtest.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

@Controller
public class QQAuthoriController {
	
	/**
	 * 生成授权链接
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qqAuth")
	public String qqAuth(HttpServletRequest request) {
		try {
			String authorizeURL = new Oauth().getAuthorizeURL(request);
			System.out.println("authorizeURL:"+ authorizeURL);
			return "redirect:" + authorizeURL;
		} catch (Exception e) {
			return "500";
		}
	}
 
	
	@RequestMapping("/loginback")
	public String qqLoginBack(String code, HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,Model model){
		try {
			// 使用授权码获取accessToken
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
			if (accessTokenObj == null) {
				return "500";
			}
			String accessToken = accessTokenObj.getAccessToken();
			if (StringUtils.isEmpty(accessToken)) {
				return "500";
			}
			// 使用accessToken获取用户openid
			OpenID openIDObj = new OpenID(accessToken);
			String openId = openIDObj.getUserOpenID();
			if (StringUtils.isEmpty(openId)) {
				return "500";
			}
			
			/* 1.在此进行业务操作,如果有openid,查询数据库中的信息,返回数据库中的userId
			 * 2.如果没有则添加用户信息到数据库中
			 * */
 	 
			// 页面需要展示 QQ头像
			UserInfo qzoneUserInfo = new UserInfo(accessToken, openId);
			UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
			if (userInfoBean == null) {
				return "500";
			}
			String avatarURL100 = userInfoBean.getAvatar().getAvatarURL100();
			model.addAttribute("avatarURL100", avatarURL100);
			return "index1";

		} catch (Exception e) {
			return "500";
		}

	}
	
}
