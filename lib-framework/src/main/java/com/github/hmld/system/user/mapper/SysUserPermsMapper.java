package com.github.hmld.system.user.mapper;

import java.util.List;
import java.util.Set;

import com.github.hmld.system.user.domain.SysUserPerms;

/**
 * 用户权限
 * @author hmld
 *
 */
public interface SysUserPermsMapper {
	/**
	 * 查询用户权限
	 * @param sysUserPerms
	 * @return
	 */
	public List<SysUserPerms> querySysUserPermsList(SysUserPerms sysUserPerms);
	/**
	 * 添加用户权限
	 * @param sysUserPerms
	 * @return
	 */
	public int insertSysUserPerms(SysUserPerms sysUserPerms);
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
	public int updateSysUserPerms(SysUserPerms sysUserPerms);
	/**
	 * 删除用户权限
	 * @param sysUserPerms
	 * @return
	 */
	public int deleteSysUserPerms(SysUserPerms sysUserPerms);
}
