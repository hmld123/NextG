package com.github.hmld.system.user.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.github.hmld.system.user.domain.SysUserPasswordHistory;
import com.github.hmld.system.user.service.ISysUserPasswordHistoryService;
import com.github.hmld.system.user.mapper.SysUserPasswordHistoryMapper;

/**
 * 用户密码历史
 * @author hmld
 *
 */
@Service
public class SysUserPasswordHistoryServiceImpl implements ISysUserPasswordHistoryService {
	@Autowired
	private SysUserPasswordHistoryMapper sysUserPasswordHistoryMapper;
	
	/**
	 * 查询 用户密码历史
	 * @param sysUserPasswordHistory
	 * @return  用户密码历史结果集
	 */
	@Override
  public List<SysUserPasswordHistory> querySysUserPasswordHistoryList(SysUserPasswordHistory sysUserPasswordHistory) {
  	return sysUserPasswordHistoryMapper.querySysUserPasswordHistoryList(sysUserPasswordHistory);
  }
	
	/**
   * 通过主键查询 用户密码历史
   * @param userHistoryPk 主键
   * @return  用户密码历史 结果集
   */
	@Override
  public SysUserPasswordHistory querySysUserPasswordHistoryByPK(String userHistoryPk) {
  	return sysUserPasswordHistoryMapper.querySysUserPasswordHistoryByPK(userHistoryPk);
  }
  
	/**
   * 保存 用户密码历史
   * @param sysUserPasswordHistory 主键
   * @return 结果
   */
	@Override
	@Transactional
  public int insertSysUserPasswordHistory(SysUserPasswordHistory sysUserPasswordHistory) {
  	return sysUserPasswordHistoryMapper.insertSysUserPasswordHistory(sysUserPasswordHistory);
  }
  
	/**
   * 修改 用户密码历史
   * @param sysUserPasswordHistory
   * @return 结果
   */
	@Override
	@Transactional
  public int updateSysUserPasswordHistory(SysUserPasswordHistory sysUserPasswordHistory) {
  	return sysUserPasswordHistoryMapper.updateSysUserPasswordHistory(sysUserPasswordHistory);
  }
  
	/**
   * 删除 用户密码历史
   * @param userHistoryPk 主键
   * @return 结果
   */
	@Override
	@Transactional
  public int deleteSysUserPasswordHistoryByPK(String userHistoryPk) {
  	return sysUserPasswordHistoryMapper.deleteSysUserPasswordHistoryByPK(userHistoryPk);
  }
  
	/**
   * 删除 用户密码历史
   * @param userHistoryPks 主键数组
   * @return 结果
   */
	@Override
	@Transactional
  public int deleteSysUserPasswordHistoryByPKS(String[] userHistoryPks) {
  	return sysUserPasswordHistoryMapper.deleteSysUserPasswordHistoryByPKS(userHistoryPks);
  }
}