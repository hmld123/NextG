package com.github.hmld.system.user.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.github.hmld.common.encrypt.EncryptEngine;
import com.github.hmld.common.enums.UseFlgEmnu;
import com.github.hmld.common.utils.DateUtils;
import com.github.hmld.common.utils.LoggerUtil;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.framework.security.SecurityUtils;
import com.github.hmld.system.user.domain.SysUser;
import com.github.hmld.system.user.domain.SysUserModel;
import com.github.hmld.system.user.domain.SysUserPasswordHistory;
import com.github.hmld.system.user.domain.SysUserPerms;
import com.github.hmld.system.user.domain.SysUserRole;
import com.github.hmld.system.user.service.ISysUserPermsService;
import com.github.hmld.system.user.service.ISysUserRoleService;
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
	@Autowired
	private ISysUserPermsService permsService;
	@Autowired
	private ISysUserRoleService roleService;
	
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
	@Cacheable(cacheNames = "authority", key = "#userPk")
  public SysUser querySysUserByPK(String userPk) {
  	return sysUserMapper.querySysUserByPK(userPk);
  }
  
	/**
   * 通过用户名查询用户
   * @param userPk 主键
   * @return 用户管理 结果集
   */
	@Override
	public SysUserModel querySysUserByName(String username) {
		SysUserModel user = sysUserMapper.querySysUserByName(username);
		if (user!=null) {
			SysUserPasswordHistory oldPassWd = passWordMapper.querySysUserPasswordHistoryByUserPK(user.getUserPk());
			user.setUserPassWord(oldPassWd);
		}
		return user;
	}
	
  /**
   * 注册用户
   * @param sysUser
   * @return
   */
	@Override
	@Transactional
  public int insertSysUser(SysUserModel sysUser) {
		try {
			String salt = StringUtils.getSalt();
			String passdata = EncryptEngine.encode(sysUser.getUserPassWord().getUserPassword().getBytes(), salt, salt.getBytes());
			initUser(sysUser);
			int insertRow = sysUserMapper.insertSysUser(sysUser);
			// 密码配置
			passWordMapper.insertSysUserPasswordHistory(initPassWord(salt, passdata, sysUser)); 
			for (SysUserRole role: sysUser.getRoles()) {
				initRole(role);
				roleService.insertSysUserRole(role);
			}
			for (SysUserPerms permis : sysUser.getPermis()) {
				initPermis(permis);
				permsService.insertSysUserPerms(permis);
			};
  	return insertRow;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return 0;
		}
  }
	
	
	/**
   * 保存用户管理
   * @param sysUser 主键
   * @return 结果
   */
	@Override
	@Transactional
  public int registerSysUser(SysUserModel sysUser) {
		try {
			String salt = StringUtils.getSalt();
			String passdata = EncryptEngine.encode(sysUser.getUserPassWord().getUserPassword().getBytes(), salt, salt.getBytes());
			initRegisterUser(sysUser);
			int insertRow = sysUserMapper.insertSysUser(sysUser);
			// 密码配置
			passWordMapper.insertSysUserPasswordHistory(initRegisterPassWord(salt, passdata, sysUser)); 
			for (SysUserRole role: sysUser.getRoles()) {
				initRole(role);
				roleService.insertSysUserRole(role);
			}
			for (SysUserPerms permis : sysUser.getPermis()) {
				initPermis(permis);
				permsService.insertSysUserPerms(permis);
			};
  	return insertRow;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return 0;
		}
  }
	/**
	 * 初始化用户角色
	 * @param role
	 */
	private void initRole(SysUserRole role) {
		role.setSurPk(StringUtils.genPkStr());
		role.setCreateBy(SecurityUtils.getUserPk());
		role.setCreateTime(DateUtils.getNowTimestamp());
		role.setStatus(UseFlgEmnu.USE_TYPE);
	}
	/**
	 * 初始化用户权限
	 * @param permis
	 */
	private void initPermis(SysUserPerms permis) {
		permis.setSupPk(StringUtils.genPkStr());
		permis.setCreateBy(SecurityUtils.getUserPk());
		permis.setCreateTime(DateUtils.getNowTimestamp());
		permis.setStatus(UseFlgEmnu.USE_TYPE);
	}
	/**
	 * 初始化用户
	 * @param sysUser
	 */
	private void initUser(SysUserModel sysUser) {
		sysUser.setUserPk(StringUtils.genPkStr());
		sysUser.setCreateTime(DateUtils.getNowTimestamp());
		sysUser.setCreateBy(SecurityUtils.getUserPk());
	}

	/**
	 * 初始化用户(注册用)
	 * @param sysUser
	 */
	private void initRegisterUser(SysUserModel sysUser) {
		sysUser.setUserPk(StringUtils.genPkStr());
		sysUser.setCreateTime(DateUtils.getNowTimestamp());
		sysUser.setCreateBy("SYSTEM");
	}
	
	/**
	 * 创建用户密码
	 * @param salt
	 * @param passdata
	 * @param sysUser
	 * @return
	 */
	private SysUserPasswordHistory initPassWord(String salt,String passdata,SysUserModel sysUser){
		SysUserPasswordHistory userPassWord = new SysUserPasswordHistory();
		userPassWord.setUserHistoryPk(StringUtils.genPkStr());
		userPassWord.setUserPk(sysUser.getUserPk());
		userPassWord.setUserName(sysUser.getUserName());
		userPassWord.setNickName(sysUser.getNickName());
		userPassWord.setSalt(salt);
		userPassWord.setUserPassword(passdata);
		userPassWord.setCreateTime(DateUtils.getNowTimestamp());
		userPassWord.setCreateBy(SecurityUtils.getUserPk());
		return userPassWord;
	}
	
	/**
	 * 创建用户密码(注册用)
	 * @param salt
	 * @param passdata
	 * @param sysUser
	 * @return
	 */
	private SysUserPasswordHistory initRegisterPassWord(String salt,String passdata,SysUserModel sysUser){
		SysUserPasswordHistory userPassWord = new SysUserPasswordHistory();
		userPassWord.setUserHistoryPk(StringUtils.genPkStr());
		userPassWord.setUserPk(sysUser.getUserPk());
		userPassWord.setUserName(sysUser.getUserName());
		userPassWord.setNickName(sysUser.getNickName());
		userPassWord.setSalt(salt);
		userPassWord.setUserPassword(passdata);
		userPassWord.setCreateTime(DateUtils.getNowTimestamp());
		userPassWord.setCreateBy("SYSTEM");
		return userPassWord;
	}
	
	/**
	 * 更新用户角色和权限信息
	 * @param sysUser
	 */
	private void updateUserRoleAndPermis(SysUserModel sysUser) {
		Set<String> oldRoles = roleService.queryUserRoleByUserID(sysUser.getUserPk());
		Set<String> odlPerms =	permsService.queryPermsByUserID(sysUser.getUserPk());
		List<SysUserRole> delRoles = new ArrayList<SysUserRole>();
		List<SysUserRole> insertRoles = new ArrayList<SysUserRole>();
		for (SysUserRole role: sysUser.getRoles()) {
			boolean has = false;
			for (String oldRole : oldRoles) {
				if (oldRole.equals(role.getRolePk())) {
					has = true;
				}
			}
			if (has) {
				if(StringUtils.isEmpty(role.getRolePk())) {
					initRole(role);
					insertRoles.add(role);
				}
			}else {
				role.setStatus(UseFlgEmnu.DEL_TYPE);
				role.setUpdateTime(DateUtils.getNowTimestamp());
				role.setUpdateBy(SecurityUtils.getUserPk());
				delRoles.add(role);
			}
		}
		
		List<SysUserPerms> delPerms = new ArrayList<SysUserPerms>();
		List<SysUserPerms> insertPerms = new ArrayList<SysUserPerms>();
		for (SysUserPerms permis : sysUser.getPermis()) {
			boolean has = false;
			for (String odlPerm : odlPerms) {
				if (odlPerm.equals(permis.getFuncPk())) {
					has = true;
				}
			}
			if (has) {
				if(StringUtils.isEmpty(permis.getSupPk())) {
					initPermis(permis);
					insertPerms.add(permis);
				}
			}else {
				permis.setStatus(UseFlgEmnu.DEL_TYPE);
				permis.setUpdateTime(DateUtils.getNowTimestamp());
				permis.setUpdateBy(SecurityUtils.getUserPk());
				delPerms.add(permis);
			}
		}
		for (SysUserPerms sysUserPerms : insertPerms) {
			permsService.insertSysUserPerms(sysUserPerms);
		}
		for (SysUserPerms sysUserPerms : delPerms) {
			permsService.deleteSysUserPerms(sysUserPerms);
		}
		for (SysUserRole sysUserRole : insertRoles) {
			roleService.insertSysUserRole(sysUserRole);
		}
		for (SysUserRole sysUserRole : delRoles) {
			roleService.deleteSysUserRole(sysUserRole);
		}
	}
	
	/**
   * 修改用户密码
   * @param sysUser
   * @return
   */
	@Override
	@Transactional
  public int updateSysUserPassWord(SysUserModel sysUser) {
		int updateRow = 0;
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
			oldPassWord.setUpdateBy(SecurityUtils.getUserPk());
			oldPassWord.setStatus(UseFlgEmnu.DEL_TYPE);
			passWordMapper.desableOldPassWordByUserPk(oldPassWord);
			updateRow = passWordMapper.insertSysUserPasswordHistory(initPassWord(newsalt, newpassdata, sysUser)); 
			return updateRow;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return updateRow;
		}
  }
	/**
   * 修改用户管理
   * @param sysUser
   * @return 结果
   */
	@Override
	@Transactional
  public int updateSysUser(SysUserModel sysUser) {
		sysUser.setUpdateBy(SecurityUtils.getUserPk());
		sysUser.setUpdateTime(DateUtils.getNowTimestamp());
		updateUserRoleAndPermis(sysUser);
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
	

}