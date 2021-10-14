package com.github.hmld.system.role.mapper;

import java.util.List;

import com.github.hmld.system.role.domain.SysUserRole;

/**
 *  用户权限明细
 * @author hmld
 *
 */
public interface SysUserRoleMapper {
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
}