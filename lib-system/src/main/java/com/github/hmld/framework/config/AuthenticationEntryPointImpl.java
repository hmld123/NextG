package com.github.hmld.framework.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
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
		response.setStatus(200);
		response.setContentType("");
		response.getWriter().print("");
	}

}
