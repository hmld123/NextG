package com.github.hmld.system.user.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.hmld.system.user.domain.SysUserRole;
import com.github.hmld.system.user.mapper.SysUserRoleMapper;
import com.github.hmld.system.user.service.ISysUserRoleService;

/**
 *  用户权限明细
 * @author hmld
 *
 */
@Service
public class SysUserRoleServiceImpl implements ISysUserRoleService {
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	/**
	 * 通过 用户主键 获取对应的角色编码
	 * @param userPk
	 * @return
	 */
	public Set<String> queryUserRoleByUserID(String userPk){
		return sysUserRoleMapper.queryUserRoleByUserID(userPk);
	}
	/**
	 * 查询 用户权限明细
	 * @param sysUserRole
	 * @return  用户权限明细结果集
	 */
	@Override
  public List<SysUserRole> querySysUserRoleList(SysUserRole sysUserRole) {
  	return sysUserRoleMapper.querySysUserRoleList(sysUserRole);
  }
	
	/**
   * 通过主键查询 用户权限明细
   * @param surPk 主键
   * @return  用户权限明细 结果集
   */
	@Override
  public SysUserRole querySysUserRoleByPK(String surPk) {
  	return sysUserRoleMapper.querySysUserRoleByPK(surPk);
  }
  
	/**
   * 保存 用户权限明细
   * @param sysUserRole 主键
   * @return 结果
   */
	@Override
	@Transactional
  public int insertSysUserRole(SysUserRole sysUserRole) {
  	return sysUserRoleMapper.insertSysUserRole(sysUserRole);
  }
  
	/**
   * 修改 用户权限明细
   * @param sysUserRole
   * @return 结果
   */
	@Override
	@Transactional
  public int updateSysUserRole(SysUserRole sysUserRole) {
  	return sysUserRoleMapper.updateSysUserRole(sysUserRole);
  }
  
	/**
   * 删除 用户权限明细
   * @param surPk 主键
   * @return 结果
   */
	@Override
	@Transactional
  public int deleteSysUserRoleByPK(String surPk) {
  	return sysUserRoleMapper.deleteSysUserRoleByPK(surPk);
  }
  
	/**
   * 删除 用户权限明细
   * @param surPk 主键
   * @return 结果
   */
	@Override
	@Transactional
  public int deleteSysUserRole(SysUserRole sysUserRole) {
		return sysUserRoleMapper.deleteSysUserRole(sysUserRole);
	}
	
	/**
   * 删除 用户权限明细
   * @param surPks 主键数组
   * @return 结果
   */
	@Override
	@Transactional
  public int deleteSysUserRoleByPKS(String[] surPks) {
  	return sysUserRoleMapper.deleteSysUserRoleByPKS(surPks);
  }
}