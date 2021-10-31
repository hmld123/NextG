package com.github.hmld.system.role.domain;

import java.util.Set;
/**
 * 角色 数据模型
 * @author hmld
 *
 */
public class SysRoleModel extends SysRole {
	private Set<String> funcPks;

	public Set<String> getFuncPks() {
		return funcPks;
	}

	public void setFuncPks(Set<String> funcPks) {
		this.funcPks = funcPks;
	}
}
