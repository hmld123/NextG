package com.github.hmld.system.role.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.github.hmld.system.role.domain.SysRole;
import com.github.hmld.system.role.domain.SysRoleModel;
import com.github.hmld.system.role.service.ISysRoleService;
import com.github.hmld.system.role.mapper.SysRoleMapper;

/**
 * 权限设置
 * @author hmld
 *
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	/**
	 * 查询 权限设置
	 * @param sysRole
	 * @return  权限设置结果集
	 */
	@Override
  public List<SysRole> querySysRoleList(SysRole sysRole) {
  	return sysRoleMapper.querySysRoleList(sysRole);
  }
	
	/**
   * 通过主键查询 权限设置
   * @param rolePk 主键
   * @return  权限设置 结果集
   */
	@Override
  public SysRoleModel querySysRoleByPK(String rolePk) {
		SysRoleModel model = (SysRoleModel) sysRoleMapper.querySysRoleByPK(rolePk);
		model.setFuncPks(sysRoleMapper.queryFuncsByRolePK(rolePk));
  	return model;
  }
  
	/**
   * 保存 权限设置
   * @param sysRole 主键
   * @return 结果
   */
	@Override
	@Transactional
  public int insertSysRole(SysRoleModel sysRole) {
		int insRows = sysRoleMapper.insertSysRole(sysRole);
		sysRoleMapper.insertRoleFuncs(sysRole);
		return insRows;
  }
  
	/**
   * 修改 权限设置
   * @param sysRole
   * @return 结果
   */
	@Override
	@Transactional
  public int updateSysRole(SysRoleModel sysRole) {
		sysRoleMapper.deleteRoleFuncs(sysRole.getRolePk());
		int updateRows = sysRoleMapper.updateSysRole(sysRole);
		sysRoleMapper.insertRoleFuncs(sysRole);
  	return updateRows;
  }
  
	/**
   * 删除 权限设置
   * @param rolePk 主键
   * @return 结果
   */
	@Override
	@Transactional
  public int deleteSysRoleByPK(String rolePk) {
  	return sysRoleMapper.deleteSysRoleByPK(rolePk);
  }
  
	/**
   * 删除 权限设置
   * @param rolePks 主键数组
   * @return 结果
   */
	@Override
	@Transactional
  public int deleteSysRoleByPKS(String[] rolePks) {
  	return sysRoleMapper.deleteSysRoleByPKS(rolePks);
  }
}