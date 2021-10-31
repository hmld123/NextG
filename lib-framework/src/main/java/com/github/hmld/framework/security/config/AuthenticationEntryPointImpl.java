package com.github.hmld.framework.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hmld.common.constant.HttpStatus;
import com.github.hmld.common.core.domain.AjaxResult;
import com.github.hmld.common.utils.ServletUtils;
import com.github.hmld.common.utils.StringUtils;
/**
 * 认证失败处理类 返回未授权
 * @author hmld
 *
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
	
	/**
	 * 将认证的报错信息返回到 response 中并显示到浏览器页面上
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
	    throws IOException, ServletException { 
		AjaxResult msg = AjaxResult.error(HttpStatus.UNAUTHORIZED, StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI()));
		ServletUtils.renderString(response, new ObjectMapper().writeValueAsString(msg));
	}

}
