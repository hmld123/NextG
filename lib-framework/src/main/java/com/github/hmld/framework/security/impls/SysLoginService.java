package com.github.hmld.framework.security.impls;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.github.hmld.common.constant.HttpStatus;
import com.github.hmld.common.exception.CustomException;
import com.github.hmld.framework.security.core.domain.LoginUser;

/**
 * 登录验证服务
 * @author hmld
 *
 */

@Component
public class SysLoginService {
	@Autowired
	private TokenService tokenService;
	@Resource
  private AuthenticationManager authenticationManager;
	
	/**
	 * 登录验证
	 * @param username
	 * @param password
	 * @return
	 */
	public String login(String username,String password) {
		//用户验证
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			if (e instanceof BadCredentialsException) {
				throw new CustomException("用户不存在/密码错误",HttpStatus.UNAUTHORIZED);
			}else {
				throw new CustomException(e.getMessage(),HttpStatus.UNAUTHORIZED);
			}
		}
		LoginUser loginUser = (LoginUser)authentication.getPrincipal();
		return tokenService.regisToken(loginUser);
	}
}
