package com.github.hmld.system.user.service;

import java.util.List;
import com.github.hmld.system.user.domain.SysUser;
import com.github.hmld.system.user.domain.SysUserModel;

/**
 * 用户管理
 * @author hmld
 *
 */
public interface ISysUserService {
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
   * 通过主键查询用户管理
   * @param userPk 主键
   * @return 用户管理 结果集
   */
  public SysUserModel querySysUserByName(String username);
  
	/**
   * 保存用户管理
   * @param sysUser 主键
   * @return 结果
   */
  public int insertSysUser(SysUserModel sysUser);
  /**
   * 注册用户
   * @param sysUser
   * @return
   */
  public int registerSysUser(SysUserModel sysUser);
  /**
   * 修改用户密码
   * @param sysUser
   * @return
   */
  public int updateSysUserPassWord(SysUserModel sysUser);
	/**
   * 修改用户管理
   * @param sysUser
   * @return 结果
   */
  public int updateSysUser(SysUserModel sysUser);
  
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