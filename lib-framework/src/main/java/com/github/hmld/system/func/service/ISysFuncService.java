package com.github.hmld.system.func.service;

import java.sql.Timestamp;
import java.util.List;

import com.github.hmld.system.func.domain.SysFunc;
/**
 * 系统功能
 * @author hmld
 *
 */
public interface ISysFuncService {
	/**
	 * 查询 系统功能
	 * @param sysFunc
	 * @return
	 */
	public List<SysFunc> querySysFuncList(SysFunc sysFunc);
	
	/**
	 * 查询 系统功能
	 * @param sysFunc
	 * @return
	 */
	public SysFunc querySysFuncByPK(String funcPk);
	/**
	 * 添加 系统功能
	 * @param sysFunc
	 * @return
	 */
	public int insertSysFunc(SysFunc sysFunc);
	/**
	 * 修改 系统功能
	 * @param sysFunc
	 * @return
	 */
	public int updateSysFunc(SysFunc sysFunc);
	/**
	 * 删除 系统功能
	 * @param sysFunc
	 * @return
	 */
	public int deleteSysFuncByPk(String delFlag, Timestamp updateTime, String updateBy, String funcPk);
	/**
	 * 删除 系统功能
	 * @param sysFunc
	 * @return
	 */
	public int deleteSysFuncByPks(String delFlag, Timestamp updateTime, String updateBy, String[] funcPks);

}
