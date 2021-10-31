package com.github.hmld.system.role.service;

import java.util.List;
import com.github.hmld.system.role.domain.SysRole;
import com.github.hmld.system.role.domain.SysRoleModel;

/**
 * 权限设置
 * @author hmld
 *
 */
public interface ISysRoleService {
  /**
   * 查询 权限设置
   * @param sysRole
   * @return  权限设置结果集
   */
  public List<SysRole> querySysRoleList(SysRole sysRole);
  
	/**
   * 通过主键查询 权限设置
   * @param rolePk 主键
   * @return  权限设置 结果集
   */
  public SysRoleModel querySysRoleByPK(String rolePk);
  
	/**
   * 保存 权限设置
   * @param sysRole 主键
   * @return 结果
   */
  public int insertSysRole(SysRoleModel sysRole);
  
	/**
   * 修改 权限设置
   * @param sysRole
   * @return 结果
   */
  public int updateSysRole(SysRoleModel sysRole);
  
	/**
   * 删除 权限设置
   * @param rolePk 主键
   * @return 结果
   */
  public int deleteSysRoleByPK(String rolePk);
  
	/**
   * 删除 权限设置
   * @param rolePks 主键数组
   * @return 结果
   */
  public int deleteSysRoleByPKS(String[] rolePks);
}