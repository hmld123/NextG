package com.github.hmld.system.user.domain;

import java.sql.Timestamp;
/**
 * 用户权限
 * @author hmld
 *
 */
public class SysUserPerms {
	/** 主键*/
	private String supPk;
	/** 用户主键*/
	private String userPk;
	/** 功能主键*/
	private String funcPk;
	/** 状态（0正常 1停用）*/
	private String status;
	/** 删除标志（0代表存在 2代表删除）*/
	private String delFlag;
	/** 更新时间*/
	private Timestamp updateTime;
	/** 更新人*/
	private String updateBy;
	/** 创建时间*/
	private Timestamp createTime;
	/** 创建人*/
	private String createBy;
	/**
	 * 获取主键
	 * @return
	 */
	public String getSupPk() {
		return supPk;
	}
	/**
	 * 设置主键
	 * @param supPk
	 */
	public void setSupPk(String supPk) {
		this.supPk = supPk;
	}
	/**
	 * 获取 用户主键
	 * @return
	 */
	public String getUserPk() {
		return userPk;
	}
	/**
	 * 设置 用户主键
	 * @param userPk
	 */
	public void setUserPk(String userPk) {
		this.userPk = userPk;
	}
	/**
	 * 获取 功能主键
	 * @return
	 */
	public String getFuncPk() {
		return funcPk;
	}
	/**
	 * 设置 功能主键
	 * @param funcPk
	 */
	public void setFuncPk(String funcPk) {
		this.funcPk = funcPk;
	}
	/**
	 * 获取 状态
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置 状态
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取 删除标志
	 * @return
	 */
	public String getDelFlag() {
		return delFlag;
	}
	/**
	 * 删除 删除标志
	 * @param delFlag
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取 更新时间
	 * @return
	 */
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置 更新时间
	 * @param updateTime
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取 更新人
	 * @return
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置 更新人
	 * @param updateBy
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取 创建时间
	 * @return
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}
	/**
	 * 设置 创建时间
	 * @param createTime
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取 创建人
	 * @return
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置 创建人
	 * @param createBy
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
}
