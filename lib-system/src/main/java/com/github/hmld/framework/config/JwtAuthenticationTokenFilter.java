package com.github.hmld.framework.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * token 过滤器
 * <p>用于校验token有效性</p>
 * @author hmld
 *
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {
		/**
		 * 1. 先通过 token中的用户名校验是否已经登录了
		 * 2. 校验tocken中的用户信息，通过则设置security的用户信息
		 */
	}

}
