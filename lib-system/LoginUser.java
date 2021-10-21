package com.github.hmld.common.core.domain.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LoginUser extends User {

	private static final long serialVersionUID = 1L;

	public LoginUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	private String token;
	
	private Long loginTime;
	
	private Long expireTime;
	
	private String ipAddr;
	
	private String loginLocation;
	
	private String browser;
	
	private String os;
	
	private Set<String> permissions;
	
	private List<SimpleGrantedAuthority> authorities;
	
	
	
}
