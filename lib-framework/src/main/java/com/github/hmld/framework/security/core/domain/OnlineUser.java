package com.github.hmld.framework.security.core.domain;

import java.util.Set;

import com.github.hmld.framework.security.core.util.PassWordCoders;
import com.github.hmld.system.user.domain.SysUser;

public class OnlineUser {
	/**
	 * 权限
	 */
	private Set<String> perms;
	/**
	 * 角色
	 */
	private Set<String> roles;
	/**
	 * 介绍
	 */
	private String introduction;
	/**
	 * 主键
	 */
	private String userPk;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户昵称
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	public Set<String> getPerms() {
		return perms;
	}
	public void setPerms(Set<String> perms) {
		this.perms = perms;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public String getUserPk() {
		return userPk;
	}
	public void setUserPk(String userPk) {
		this.userPk = userPk;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public OnlineUser(Set<String> perms, Set<String> roles, SysUser user) {
		this.perms = perms;
		this.roles = roles;
		this.userPk = user.getUserPk();
		this.introduction = user.getIntroduction();
		this.avatar = user.getAvatar();
		this.name = user.getNickName();
		this.userName = user.getUserName();
		this.password = PassWordCoders.decodeUserPassWord(user);
	}
	public OnlineUser() {
	}
	
}
