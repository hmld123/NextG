package com.github.hmld.framework.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hmld.common.core.domain.AjaxResult;
import com.github.hmld.common.utils.ServletUtils;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.framework.security.core.domain.LoginUser;
import com.github.hmld.framework.security.impls.TokenService;
/**
 * 自定义退出登录处理
 * @author hmld
 * 
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
	@Autowired
  private TokenService tokenService;
	
	/**
	 * 自定义 退出登录 实现
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	    throws IOException, ServletException {
		LoginUser loginUser = tokenService.getLoginUser(request);
		if (StringUtils.isNotNull(loginUser)) {
			// 删除用户缓存
			tokenService.delLoginUser(loginUser.getToken());
		}
		String msg = new ObjectMapper().writeValueAsString(AjaxResult.success("退出成功"));
		ServletUtils.renderString(response, msg);
	}

}
