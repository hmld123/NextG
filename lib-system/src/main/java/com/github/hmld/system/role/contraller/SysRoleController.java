package com.github.hmld.system.role.contraller;

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

import com.github.hmld.system.role.domain.SysRole;
import com.github.hmld.system.role.service.ISysRoleService;
/**
 * 权限设置
 * @author hmld
 *
 */
 @Controller
 @RequestMapping("/system/role")
 public class SysRoleController {
 	
 	@Autowired
 	private ISysRoleService sysRoleService;
 	
	/**
   * 查询 权限设置
   * @param sysRole
   * @return  权限设置结果集
   */
  @Log(level = 0,detail = "getSysRoles()",operationType = OperationType.SELECT,operationUnit = OperationUnit.DATABASE)
  @GetMapping("/sysRoles")
  @ResponseBody
  public AjaxResult getSysRoles(@RequestBody SysRole sysRole) {
  	return AjaxResult.success(sysRoleService.querySysRoleList(sysRole));
  }
  
	/**
   * 通过主键查询 权限设置
   * @param rolePk 主键
   * @return  权限设置 结果集
   */
  @Log(level = 0,detail = "getSysRole()",operationType = OperationType.SELECT,operationUnit = OperationUnit.DATABASE)
  @GetMapping("/sysRole/{rolePk}")
  @ResponseBody
  public AjaxResult getSysRole(@PathVariable String rolePk) {
  	return AjaxResult.success(sysRoleService.querySysRoleByPK(rolePk));
  }
  
	/**
   * 保存 权限设置
   * @param sysRole 主键
   * @return 结果
   */
  @Log(level = 0,detail = "saveSysRole()",operationType = OperationType.INSERT,operationUnit = OperationUnit.DATABASE)
  @PostMapping("/sysRole")
  @ResponseBody
  public AjaxResult saveSysRole(@RequestBody SysRole sysRole){
  	return AjaxResult.success(sysRoleService.insertSysRole(sysRole));
  }
  
	/**
   * 修改 权限设置
   * @param sysRole
   * @return 结果
   */
  @Log(level = 0,detail = "updateSysRole()",operationType = OperationType.UPDATE,operationUnit = OperationUnit.DATABASE)
  @PutMapping("/sysRole")
  @ResponseBody
  public AjaxResult updateSysRole(@RequestBody SysRole sysRole){
  	return AjaxResult.success(sysRoleService.updateSysRole(sysRole));
  }
	/**
   * 删除 权限设置
   * @param rolePks 主键数组
   * @return 结果
   */
  @Log(level = 0,detail = "deleteSysRole()",operationType = OperationType.DELETE,operationUnit = OperationUnit.DATABASE)
  @DeleteMapping("/sysRole/{rolePks}")
  @ResponseBody
  public AjaxResult deleteSysRole(@PathVariable String[] rolePks) {
  	return AjaxResult.success(sysRoleService.deleteSysRoleByPKS(rolePks));
  }
 }