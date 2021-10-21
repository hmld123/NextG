package com.github.hmld.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
/**
 * 自定义验证用户名密码组件
 * @author hmld
 *
 */
@Component
public class CustomUserNamePasswordAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private PasswordEncoder passwordEncoder;
	/**
	 * 自定义密码验证
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println(authentication.getName());
		System.out.println(authentication.getCredentials().toString());
		System.out.println(passwordEncoder.encode("123"));
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}


}
