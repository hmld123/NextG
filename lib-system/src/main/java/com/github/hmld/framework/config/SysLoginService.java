package com.github.hmld.framework.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.github.hmld.common.core.domain.model.LoginUser;
//import com.github.hmld.common.core.redis.RedisCache;
/**
 * 登录验证服务
 * @author hmld
 *
 */

@Component
public class SysLoginService {
	@Autowired
	private TokenService tokenService;
//	@Autowired
//	private RedisCache redisCache;
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
			e.printStackTrace();
		}
		LoginUser loginUser = (LoginUser)authentication.getPrincipal();
		return tokenService.regisToken(loginUser);
	}
}
