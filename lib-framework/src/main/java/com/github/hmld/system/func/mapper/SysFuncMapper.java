package com.github.hmld.system.func.mapper;

import java.util.List;
import java.sql.Timestamp;
import org.apache.ibatis.annotations.Param;

import com.github.hmld.system.func.domain.SysFunc;
/**
 * 系统功能
 * @author hmld
 *
 */
public interface SysFuncMapper {
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
	public int deleteSysFuncByPk(@Param("delFlag") String delFlag,
			@Param("updateTime") Timestamp updateTime,
			@Param("updateBy") String updateBy,@Param("funcPk") String funcPk);
	/**
	 * 删除 系统功能
	 * @param sysFunc
	 * @return
	 */
	public int deleteSysFuncByPks(@Param("delFlag") String delFlag,
			@Param("updateTime") Timestamp updateTime,
			@Param("updateBy") String updateBy,@Param("funcPks") String[] funcPks);

}
