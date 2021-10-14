package com.github.hmld.system.user.service;

import java.util.List;
import com.github.hmld.system.user.domain.SysUserPasswordHistory;

/**
 * 用户密码历史
 * @author hmld
 *
 */
public interface ISysUserPasswordHistoryService {
  /**
   * 查询 用户密码历史
   * @param sysUserPasswordHistory
   * @return  用户密码历史结果集
   */
  public List<SysUserPasswordHistory> querySysUserPasswordHistoryList(SysUserPasswordHistory sysUserPasswordHistory);
  
	/**
   * 通过主键查询 用户密码历史
   * @param userHistoryPk 主键
   * @return  用户密码历史 结果集
   */
  public SysUserPasswordHistory querySysUserPasswordHistoryByPK(String userHistoryPk);
  
	/**
   * 保存 用户密码历史
   * @param sysUserPasswordHistory 主键
   * @return 结果
   */
  public int insertSysUserPasswordHistory(SysUserPasswordHistory sysUserPasswordHistory);
  
	/**
   * 修改 用户密码历史
   * @param sysUserPasswordHistory
   * @return 结果
   */
  public int updateSysUserPasswordHistory(SysUserPasswordHistory sysUserPasswordHistory);
  
	/**
   * 删除 用户密码历史
   * @param userHistoryPk 主键
   * @return 结果
   */
  public int deleteSysUserPasswordHistoryByPK(String userHistoryPk);
  
	/**
   * 删除 用户密码历史
   * @param userHistoryPks 主键数组
   * @return 结果
   */
  public int deleteSysUserPasswordHistoryByPKS(String[] userHistoryPks);
}