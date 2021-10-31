package com.github.hmld.system.user.service;

import java.util.List;
import java.util.Set;

import com.github.hmld.system.user.domain.SysUserPerms;

/**
 * 用户权限
 * @author hmld
 *
 */
public interface ISysUserPermsService {
	/**
	 * 查询用户权限
	 * @param sysUserPerms
	 * @return
	 */
	public List<SysUserPerms> querySysUserPermsList(SysUserPerms userPerms);
	/**
	 * 添加用户权限
	 * @param sysUserPerms
	 * @return
	 */
	public int insertSysUserPerms(SysUserPerms userPerms);
	/**
	 * 获取用户权限
	 * @param userPk
	 * @return
	 */
	public Set<String> queryPermsByUserID(String userPk);
	/**
	 * 更新用户权限
	 * @param sysUserPerms
	 * @return
	 */
	public int updateSysUserPerms(SysUserPerms userPerms);
	/**
	 * 删除用户权限
	 * @param sysUserPerms
	 * @return
	 */
	public int deleteSysUserPerms(SysUserPerms userPerms);
}
