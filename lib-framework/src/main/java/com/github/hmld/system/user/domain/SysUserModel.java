package com.github.hmld.system.user.domain;

import java.util.Set;
/**
 * 用户 数据模型
 * @author hmld
 *
 */
public class SysUserModel extends SysUser{
	private Set<SysUserPerms> permis;
	private Set<SysUserRole> roles;
	public Set<SysUserPerms> getPermis() {
		return permis;
	}
	public void setPermis(Set<SysUserPerms> permis) {
		this.permis = permis;
	}
	public Set<SysUserRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<SysUserRole> roles) {
		this.roles = roles;
	}
	
}
