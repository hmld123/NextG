package com.github.hmld.framework.security.core.domain;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.hmld.common.encrypt.EncryptEngine;
import com.github.hmld.system.user.domain.SysUser;

public class LoginUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户 token
	 */
	private String token;
	/**
	 * 登录时间
	 */
	private Long loginTime;
	/**
	 * 过期时间
	 */
	private Long expireTime;
	/**
	 * 登录IP
	 */
	private String ipAddr;
	/**
	 * 登录地点
	 */
	private String loginLocation;
	/**
	 * 浏览器类型
	 */
	private String browser;
	/**
	 * 操作系统
	 */
	private String os;
	/**
	 * 权限列表
	 */
	private Set<String> permissions;
	/**
	 * security 权限
	 */
	private List<SimpleGrantedAuthority> authorities;
	/**
	 * 用户信息
	 */
	private SysUser user;
	/**
	 * 获取用户密码密文
	 */
	@JsonIgnore
	@Override
	public String getPassword() {
		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String password = user.getUserPassWord().getUserPassword();
			String salt = user.getUserPassWord().getSalt();
			String ps = EncryptEngine.decode(password.getBytes(), salt, salt.getBytes());
			String thpassword = passwordEncoder.encode(ps);
			return thpassword;
		} catch (Exception e) {
			e.printStackTrace();
			return user.getUserPassWord().getUserPassword();
		}
	}
	/**
	 * 获取用户 username
	 */
	@Override
	public String getUsername() {
		return user.getUserName();
	}
	/**
	 * 账户是否未过期，过期无法验证
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	/**
	 * 用户是否解锁，锁定的用户无法验证
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	/**
	 * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
	 */
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	/**
	 * 是否可用，禁用的用户不能进行身份验证
	 */
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
	/**
	 * 获取 token
	 * @return
	 */
	public String getToken() {
		return token;
	}
	/**
	 * 设置 token
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 获取登录时间
	 * @return
	 */
	public Long getLoginTime() {
		return loginTime;
	}
	/**
	 * 设置登录时间
	 * @param loginTime
	 */
	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * 获取过期时间
	 * @return
	 */
	public Long getExpireTime() {
		return expireTime;
	}
	/**
	 * 设置过期时间
	 * @param expireTime
	 */
	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
	/**
	 * 过期登录IP
	 * @return
	 */
	public String getIpAddr() {
		return ipAddr;
	}
	/**
	 * 设置登录IP
	 * @param ipAddr
	 */
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	/**
	 * 获取登录地点
	 * @return
	 */
	public String getLoginLocation() {
		return loginLocation;
	}
	/**
	 * 设置登录地点
	 * @param loginLocation
	 */
	public void setLoginLocation(String loginLocation) {
		this.loginLocation = loginLocation;
	}
	/**
	 * 获取浏览器类型
	 * @return
	 */
	public String getBrowser() {
		return browser;
	}
	/**
	 * 设置浏览器类型
	 * @param browser
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	/**
	 * 获取登录操作系统类型
	 * @return
	 */
	public String getOs() {
		return os;
	}
	/**
	 * 设置登录操作系统类型
	 * @param os
	 */
	public void setOs(String os) {
		this.os = os;
	}
	/**
	 * 获取权限列表
	 * @return
	 */
	public Set<String> getPermissions() {
		return permissions;
	}
	/**
	 * 设置权限列表
	 * @param permissions
	 */
	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	/**
	 * 获取 security 权限
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	/**
	 * 设置 security 权限
	 * @param authorities
	 */
	public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	/**
	 * 获取用户信息
	 * @return
	 */
	public SysUser getUser() {
		return user;
	}
	/**
	 * 设置用户信息
	 * @param user
	 */
	public void setUser(SysUser user) {
		this.user = user;
	}

	public LoginUser(SysUser user, Set<String> permissions, List<SimpleGrantedAuthority> authorities) {
		this.permissions = permissions;
		this.authorities = authorities;
		this.user = user;
	}
	
	public LoginUser() {}

}
