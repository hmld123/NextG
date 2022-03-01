package com.github.hmld.system.func.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hmld.system.func.domain.SysFunc;
import com.github.hmld.system.func.mapper.SysFuncMapper;
import com.github.hmld.system.func.service.ISysFuncService;
/**
 * 系统功能
 * @author hmld
 *
 */
@Service
public class SysFuncServiceImpl implements ISysFuncService {
	@Autowired
	private SysFuncMapper sysFuncMapper;
	
	/**
	 * 查询 系统功能
	 * @param sysFunc
	 * @return
	 */
	public List<SysFunc> querySysFuncList(SysFunc sysFunc){
		return sysFuncMapper.querySysFuncList(sysFunc);
	}
	
	/**
	 * 查询 系统功能
	 * @param sysFunc
	 * @return
	 */
	public SysFunc querySysFuncByPK(String funcPk){
		return sysFuncMapper.querySysFuncByPK(funcPk);
	}
	/**
	 * 添加 系统功能
	 * @param sysFunc
	 * @return
	 */
	public int insertSysFunc(SysFunc sysFunc){
		sysFunc.setFuncPk(UUID.randomUUID().toString());
		return sysFuncMapper.insertSysFunc(sysFunc);
	}
	/**
	 * 修改 系统功能
	 * @param sysFunc
	 * @return
	 */
	public int updateSysFunc(SysFunc sysFunc){
		return sysFuncMapper.updateSysFunc(sysFunc);
	}
	/**
	 * 删除 系统功能
	 * @param sysFunc
	 * @return
	 */
	public int deleteSysFuncByPk(String delFlag, Timestamp updateTime, String updateBy, String funcPk) {
		return sysFuncMapper.deleteSysFuncByPk(delFlag, updateTime, updateBy, funcPk);
	}
	/**
	 * 删除 系统功能
	 * @param sysFunc
	 * @return
	 */
	public int deleteSysFuncByPks(String delFlag, Timestamp updateTime, String updateBy, String[] funcPks) {
		return sysFuncMapper.deleteSysFuncByPks(delFlag, updateTime, updateBy, funcPks);
	}
}
