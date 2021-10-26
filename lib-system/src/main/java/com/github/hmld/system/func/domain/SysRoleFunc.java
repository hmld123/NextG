package com.github.hmld.system.func.domain;
/**
 * 角色权限
 * @author hmld
 *
 */
public class SysRoleFunc {
	/** 角色主键*/
	private String rolePk;
	/** 功能主键*/
	private String funcPk;
	/**
	 * 获取 角色主键
	 * @return
	 */
	public String getRolePk() {
		return rolePk;
	}
	/**
	 * 设置 角色主键
	 * @param rolePk
	 */
	public void setRolePk(String rolePk) {
		this.rolePk = rolePk;
	}
	/**
	 * 获取 功能主键
	 * @return
	 */
	public String getFuncPk() {
		return funcPk;
	}
	/**
	 * 设置 功能主键
	 * @param funcPk
	 */
	public void setFuncPk(String funcPk) {
		this.funcPk = funcPk;
	}
	
}
