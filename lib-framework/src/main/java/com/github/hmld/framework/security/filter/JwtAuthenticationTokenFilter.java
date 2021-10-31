package com.github.hmld.framework.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.framework.security.core.domain.LoginUser;
import com.github.hmld.framework.security.impls.TokenService;
/**
 * 自定义token过滤器
 * @author hmld
 *
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 用于实现自定义token验证
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {
		LoginUser loginUser = tokenService.getLoginUser(request);
		if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
			tokenService.verifyToken(loginUser);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);
	}

}
