package com.github.hmld.system.user.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hmld.system.user.domain.SysUserPerms;
import com.github.hmld.system.user.mapper.SysUserPermsMapper;
import com.github.hmld.system.user.service.ISysUserPermsService;
/**
 * 用户权限
 * @author hmld
 *
 */
@Service
public class SysUserPermsServiceImpl implements ISysUserPermsService {
	@Autowired
	private SysUserPermsMapper sysUserPermsMapper;
	
	/**
	 * 查询用户权限
	 * @param sysUserPerms
	 * @return
	 */
	public List<SysUserPerms> querySysUserPermsList(SysUserPerms userPerms){
		return sysUserPermsMapper.querySysUserPermsList(userPerms);
	}
	/**
	 * 添加用户权限
	 * @param sysUserPerms
	 * @return
	 */
	public int insertSysUserPerms(SysUserPerms userPerms){
		return sysUserPermsMapper.insertSysUserPerms(userPerms);
	}
	/**
	 * 获取用户权限
	 * @param userPk
	 * @return
	 */
	public Set<String> queryPermsByUserID(String userPk){
		return sysUserPermsMapper.queryPermsByUserID(userPk);
	}
	/**
	 * 更新用户权限
	 * @param sysUserPerms
	 * @return
	 */
	public int updateSysUserPerms(SysUserPerms userPerms){
		return sysUserPermsMapper.updateSysUserPerms(userPerms);
	}
	/**
	 * 删除用户权限
	 * @param sysUserPerms
	 * @return
	 */
	public int deleteSysUserPerms(SysUserPerms userPerms){
		return sysUserPermsMapper.deleteSysUserPerms(userPerms);
	}
}
