package com.github.hmld.system.user.contraller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.hmld.common.core.domain.AjaxResult;
import com.github.hmld.common.logger.Log;
import com.github.hmld.common.logger.OperationType;
import com.github.hmld.common.logger.OperationUnit;

import com.github.hmld.system.user.domain.SysUser;
import com.github.hmld.system.user.domain.SysUserModel;
import com.github.hmld.system.user.service.ISysUserService;
/**
 * 用户管理
 * @author hmld
 *
 */
 @Controller
 @RequestMapping("/system/user")
 public class SysUserController {
 	
 	@Autowired
 	private ISysUserService sysUserService;
 	
	/**
   * 查询用户管理
   * @param sysUser
   * @return 用户管理结果集
   */
  @Log(level = 0,detail = "getSysUsers()",operationType = OperationType.SELECT,operationUnit = OperationUnit.DATABASE)
  @GetMapping
  @ResponseBody
  public AjaxResult getSysUsers(@RequestBody SysUser sysUser) {
  	return AjaxResult.success(sysUserService.querySysUserList(sysUser));
  }
  
	/**
   * 通过主键查询用户管理
   * @param userPk 主键
   * @return 用户管理 结果集
   */
  @Log(level = 0,detail = "getSysUser()",operationType = OperationType.SELECT,operationUnit = OperationUnit.DATABASE)
  @GetMapping("/{userPk}")
  @ResponseBody
  public AjaxResult getSysUser(@PathVariable String userPk) {
  	return AjaxResult.success(sysUserService.querySysUserByPK(userPk));
  }
  
	/**
   * 保存用户管理
   * @param sysUser 主键
   * @return 结果
   */
  @Log(level = 0,detail = "saveSysUser()",operationType = OperationType.INSERT,operationUnit = OperationUnit.DATABASE)
  @PostMapping
  @ResponseBody
  public AjaxResult saveSysUser(@RequestBody SysUserModel sysUser){
  	return AjaxResult.success(sysUserService.insertSysUser(sysUser));
  }
  
  /**
   * 修改用户密码
   * @param sysUser 主键
   * @return 结果
   */
  @Log(level = 0,detail = "updateSysUserPassWord()",operationType = OperationType.UPDATE,operationUnit = OperationUnit.DATABASE)
  @PostMapping("/password")
  @ResponseBody
  public AjaxResult updateSysUserPassWord(@RequestBody SysUserModel sysUser){
  	return AjaxResult.success(sysUserService.updateSysUserPassWord(sysUser));
  }
  
	/**
   * 修改用户管理
   * @param sysUser
   * @return 结果
   */
  @Log(level = 0,detail = "updateSysUser()",operationType = OperationType.UPDATE,operationUnit = OperationUnit.DATABASE)
  @PutMapping
  @ResponseBody
  public AjaxResult updateSysUser(@RequestBody SysUserModel sysUser){
  	return AjaxResult.success(sysUserService.updateSysUser(sysUser));
  }
	/**
   * 删除用户管理
   * @param userPks 主键数组
   * @return 结果
   */
  @Log(level = 0,detail = "deleteSysUser()",operationType = OperationType.DELETE,operationUnit = OperationUnit.DATABASE)
  @DeleteMapping("/{userPks}")
  @ResponseBody
  public AjaxResult deleteSysUser(@PathVariable String[] userPks) {
  	return AjaxResult.success(sysUserService.deleteSysUserByPKS(userPks));
  }
 }