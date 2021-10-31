package com.github.hmld.system.func.controller;

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
import com.github.hmld.common.enums.DelFlgEmnu;
import com.github.hmld.common.exception.EnityRequiredException;
import com.github.hmld.common.logger.Log;
import com.github.hmld.common.logger.OperationType;
import com.github.hmld.common.logger.OperationUnit;
import com.github.hmld.common.utils.DateUtils;
import com.github.hmld.framework.security.SecurityUtils;
import com.github.hmld.system.func.domain.SysFunc;
import com.github.hmld.system.func.service.ISysFuncService;
/**
 * 功能注册
 * @author hmld
 * <p>
 * 功能注册：
 * </p>
 */
@Controller
@RequestMapping("/system/func")
public class SysFuncController {
	@Autowired
	private ISysFuncService sysFuncService;
	
	/**
	 * 查询 系统功能
	 * @param sysFunc
	 * @return
	 */
  @Log(level = 0,detail = "getSysFuncs()",operationType = OperationType.SELECT,operationUnit = OperationUnit.DATABASE)
	@GetMapping
	@ResponseBody
	public AjaxResult getSysFuncs(@RequestBody SysFunc sysFunc) {
		return AjaxResult.success(sysFuncService.querySysFuncList(sysFunc));
	}
	
	/**
	 * 查询 系统功能
	 * @param sysFunc
	 * @return
	 */
  @Log(level = 0,detail = "getSysFunc()",operationType = OperationType.SELECT,operationUnit = OperationUnit.DATABASE)
	@GetMapping("/{funcPk}")
	public AjaxResult getSysFunc(@PathVariable String funcPk) {
		return AjaxResult.success(sysFuncService.querySysFuncByPK(funcPk));
	}
	
	/**
	 * 添加 系统功能
	 * @param sysFunc
	 * @return
	 */
  @Log(level = 0,detail = "saveSysFunc()",operationType = OperationType.INSERT,operationUnit = OperationUnit.DATABASE)
	@PostMapping
	@ResponseBody
	public AjaxResult saveSysFunc(@RequestBody SysFunc sysFunc) {
		try {
			sysFunc.setCreateBy(SecurityUtils.getUserPk());
			sysFunc.setCreateTime(DateUtils.getNowTimestamp());
			SysFunc.retryEnity(sysFunc);
			return AjaxResult.success(sysFuncService.insertSysFunc(sysFunc));
		} catch (EnityRequiredException e) {
			e.printStackTrace();
			return AjaxResult.error(e.getMessage());
		}
	}
	
	/**
	 * 修改 系统功能
	 * @param sysFunc
	 * @return
	 */
  @Log(level = 0,detail = "updateSysFunc()",operationType = OperationType.UPDATE,operationUnit = OperationUnit.DATABASE)
	@PutMapping
	@ResponseBody
	public AjaxResult updateSysFunc(@RequestBody SysFunc sysFunc) {
		sysFunc.setUpdateBy(SecurityUtils.getUserPk());
		sysFunc.setUpdateTime(DateUtils.getNowTimestamp());
		return AjaxResult.success(sysFuncService.updateSysFunc(sysFunc));
	}
	
	/**
	 * 删除 系统功能
	 * @param sysFunc
	 * @return
	 */
  @Log(level = 0,detail = "deleteSysFunc()",operationType = OperationType.DELETE,operationUnit = OperationUnit.DATABASE)
	@DeleteMapping("/{funcPks}")
	public AjaxResult deleteSysFunc(@RequestBody String[] funcPks) {
		return AjaxResult.success(sysFuncService.deleteSysFuncByPks(DelFlgEmnu.DEL_TYPE, DateUtils.getNowTimestamp(), SecurityUtils.getUserPk(), funcPks));
	}
}
