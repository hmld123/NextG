package com.github.hmld.system.user.mapper;

import java.util.List;

import com.github.hmld.system.user.domain.SysUser;
import com.github.hmld.system.user.domain.SysUserModel;

/**
 * 用户管理
 * @author hmld
 *
 */
public interface SysUserMapper {
	/**
   * 查询用户管理
   * @param sysUser
   * @return 用户管理结果集
   */
	public List<SysUser> querySysUserList(SysUser sysUser);
  
	/**
   * 通过主键查询用户管理
   * @param userPk 主键
   * @return 用户管理 结果集
   */
  public SysUser querySysUserByPK(String userPk);
  /**
   * 通过用户名查询用户
   * @param userPk 主键
   * @return 用户管理 结果集
   */
  public SysUserModel querySysUserByName(String userName);
	/**
   * 保存用户管理
   * @param sysUser 主键
   * @return 结果
   */
  public int insertSysUser(SysUser sysUser);
  
	/**
   * 修改用户管理
   * @param sysUser
   * @return 结果
   */
  public int updateSysUser(SysUser sysUser);
  
	/**
   * 删除用户管理
   * @param userPk 主键
   * @return 结果
   */
  public int deleteSysUserByPK(String userPk);
  
	/**
   * 删除用户管理
   * @param userPks 主键数组
   * @return 结果
   */
  public int deleteSysUserByPKS(String[] userPks);
}