package com.github.hmld.system.user.service.impl;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.hmld.common.encrypt.EncryptEngine;
import com.github.hmld.common.pwm.enigine.PassWordEnigine;
import com.github.hmld.common.pwm.enigine.config.PassWordSetting;
import com.github.hmld.common.utils.DateUtils;
import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.system.user.domain.SysUser;
import com.github.hmld.system.user.domain.SysUserPasswordHistory;
import com.github.hmld.system.user.service.ISysUserService;
import com.github.hmld.system.user.mapper.SysUserMapper;
import com.github.hmld.system.user.mapper.SysUserPasswordHistoryMapper;

/**
 * 用户管理
 * @author hmld
 *
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
	private static final Logger LOGGER = LoggerUtil.initLogger(SysUserServiceImpl.class);
	
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserPasswordHistoryMapper passWordMapper;
	/**
	 * 查询用户管理
	 * @param sysUser
	 * @return 用户管理结果集
	 */
	@Override
  public List<SysUser> querySysUserList(SysUser sysUser) {
  	return sysUserMapper.querySysUserList(sysUser);
  }
	
	/**
   * 通过主键查询用户管理
   * @param userPk 主键
   * @return 用户管理 结果集
   */
	@Override
  public SysUser querySysUserByPK(String userPk) {
  	return sysUserMapper.querySysUserByPK(userPk);
  }
  
	/**
   * 保存用户管理
   * @param sysUser 主键
   * @return 结果
   */
	@Override
	@Transactional
  public int insertSysUser(SysUser sysUser) {
		try {
			Timestamp creatTime = DateUtils.getNowTimestamp();
			String salt = StringUtils.getSalt();
			String userPass = genPass();
			String passdata = EncryptEngine.encode(userPass.getBytes(), salt, salt.getBytes());
			sysUser.setUserPk(StringUtils.genPkStr());
			sysUser.setCreateTime(creatTime);
			sysUser.setCreateBy("hmld");
			int insertRow = sysUserMapper.insertSysUser(sysUser);
			// 密码配置
			SysUserPasswordHistory userPassWord = new SysUserPasswordHistory();
			userPassWord.setUserHistoryPk(StringUtils.genPkStr());
			userPassWord.setUserPk(sysUser.getUserPk());
			userPassWord.setUserName(sysUser.getUserName());
			userPassWord.setNickName(sysUser.getNickName());
			userPassWord.setSalt(salt);
			userPassWord.setUserPassword(passdata);
			userPassWord.setCreateTime(creatTime);
			userPassWord.setCreateBy("hmld");
			passWordMapper.insertSysUserPasswordHistory(userPassWord); 
  	return insertRow;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return 0;
		}
  }
	
	/**
   * 修改用户密码
   * @param sysUser
   * @return
   */
	@Override
	@Transactional
  public int updateSysUserPassWord(SysUser sysUser) {
		try {
			SysUserPasswordHistory oldPassWd = passWordMapper.querySysUserPasswordHistoryByUserPK(sysUser.getUserPk());
			String oldPassWdData = EncryptEngine.decode(oldPassWd.getUserPassword().getBytes(), oldPassWd.getSalt(), oldPassWd.getSalt().getBytes());
			String newsalt = StringUtils.getSalt();
			String newpassdata = EncryptEngine.encode(sysUser.getUserPassWord().getUserPassword().getBytes(), newsalt , newsalt.getBytes());
			if (oldPassWdData.equals(sysUser.getUserPassWord().getUserPassword())) {
				LOGGER.error("The password is on used");
				return 0;
			}
			SysUser thuser = sysUserMapper.querySysUserByPK(sysUser.getUserPk());
			Timestamp creatTime = DateUtils.getNowTimestamp();
			SysUserPasswordHistory oldPassWord = new SysUserPasswordHistory();
			oldPassWord.setUserPk(thuser.getUserPk());
			oldPassWord.setUpdateTime(creatTime);
			oldPassWord.setUpdateBy("hmld");
			oldPassWord.setStatus("1");
			passWordMapper.desableOldPassWordByUserPk(oldPassWord);
			SysUserPasswordHistory userPassWord = new SysUserPasswordHistory();
			userPassWord.setUserHistoryPk(StringUtils.genPkStr());
			userPassWord.setUserPk(thuser.getUserPk());
			userPassWord.setUserName(thuser.getUserName());
			userPassWord.setNickName(thuser.getNickName());
			userPassWord.setSalt(newsalt);
			userPassWord.setUserPassword(newpassdata);
			userPassWord.setCreateTime(creatTime);
			userPassWord.setCreateBy("hmld");
			return passWordMapper.insertSysUserPasswordHistory(userPassWord); 
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return 0;
		}
  }
	/**
   * 修改用户管理
   * @param sysUser
   * @return 结果
   */
	@Override
	@Transactional
  public int updateSysUser(SysUser sysUser) {
		sysUser.setCreateTime(DateUtils.getNowTimestamp());
  	return sysUserMapper.updateSysUser(sysUser);
  }
  
	/**
   * 删除用户管理
   * @param userPk 主键
   * @return 结果
   */
	@Override
	@Transactional
  public int deleteSysUserByPK(String userPk) {
  	return sysUserMapper.deleteSysUserByPK(userPk);
  }
  
	/**
   * 删除用户管理
   * @param userPks 主键数组
   * @return 结果
   */
	@Override
	@Transactional
  public int deleteSysUserByPKS(String[] userPks) {
  	return sysUserMapper.deleteSysUserByPKS(userPks);
  }
	
	/**
	 * 自动生成密码
	 * @return 密码
	 */
	private String genPass() {
		PassWordEnigine enigine = new PassWordEnigine();
		PassWordSetting passWordSetting = new PassWordSetting();
		passWordSetting.setPassord_length(8);
		passWordSetting.setHave_chinese(false);
		passWordSetting.setHave_number(true);
		passWordSetting.setHave_special(true);
		return enigine.getPassWord(passWordSetting);
	}
	
}