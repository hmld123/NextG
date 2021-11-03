package com.github.hmld.system.user.service;

import java.util.List;
import java.util.Set;

import com.github.hmld.system.user.domain.SysUserRole;

/**
 *  用户权限明细
 * @author hmld
 *
 */
public interface ISysUserRoleService {
	/**
	 * 通过 用户主键 获取对应的角色编码
	 * @param userPk
	 * @return
	 */
	public Set<String> queryUserRoleByUserID(String userPk);
  /**
   * 查询 用户权限明细
   * @param sysUserRole
   * @return  用户权限明细结果集
   */
  public List<SysUserRole> querySysUserRoleList(SysUserRole sysUserRole);
  
	/**
   * 通过主键查询 用户权限明细
   * @param surPk 主键
   * @return  用户权限明细 结果集
   */
  public SysUserRole querySysUserRoleByPK(String surPk);
  
	/**
   * 保存 用户权限明细
   * @param sysUserRole 主键
   * @return 结果
   */
  public int insertSysUserRole(SysUserRole sysUserRole);
  
	/**
   * 修改 用户权限明细
   * @param sysUserRole
   * @return 结果
   */
  public int updateSysUserRole(SysUserRole sysUserRole);
  
	/**
   * 删除 用户权限明细
   * @param surPk 主键
   * @return 结果
   */
  public int deleteSysUserRoleByPK(String surPk);
  
	/**
   * 删除 用户权限明细
   * @param surPks 主键数组
   * @return 结果
   */
  public int deleteSysUserRoleByPKS(String[] surPks);
  
  /**
   * 删除 用户权限明细
   * @param sysUserRole
   * @return
   */
  public int deleteSysUserRole(SysUserRole sysUserRole) ;
}