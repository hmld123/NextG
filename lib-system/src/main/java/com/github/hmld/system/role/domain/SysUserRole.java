package com.github.hmld.system.role.domain;
import java.sql.Timestamp;
/**
 * 用户权限明细
 * @author hmld
 *
 */
public class SysUserRole {
	/** 用户权限主键*/
	private String surPk;
	/** 用户主键*/
	private String userPk;
	/** 角色主键*/
	private String rolePk;
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
	 * 设置 用户权限主键
	 * @param surPk
	 */
	public void setSurPk (String surPk) {
	  this.surPk = surPk;
	}
	/** 
	 * 获取 用户权限主键
	 * @param surPk
	 */
	public String getSurPk () {
	  return surPk;
	}
	/** 
	 * 设置 用户主键
	 * @param userPk
	 */
	public void setUserPk (String userPk) {
	  this.userPk = userPk;
	}
	/** 
	 * 获取 用户主键
	 * @param userPk
	 */
	public String getUserPk () {
	  return userPk;
	}
	/** 
	 * 设置 角色主键
	 * @param rolePk
	 */
	public void setRolePk (String rolePk) {
	  this.rolePk = rolePk;
	}
	/** 
	 * 获取 角色主键
	 * @param rolePk
	 */
	public String getRolePk () {
	  return rolePk;
	}
	/** 
	 * 设置 状态（0正常 1停用）
	 * @param status
	 */
	public void setStatus (String status) {
	  this.status = status;
	}
	/** 
	 * 获取 状态（0正常 1停用）
	 * @param status
	 */
	public String getStatus () {
	  return status;
	}
	/** 
	 * 设置 删除标志（0代表存在 2代表删除）
	 * @param delFlag
	 */
	public void setDelFlag (String delFlag) {
	  this.delFlag = delFlag;
	}
	/** 
	 * 获取 删除标志（0代表存在 2代表删除）
	 * @param delFlag
	 */
	public String getDelFlag () {
	  return delFlag;
	}
	/** 
	 * 设置 更新时间
	 * @param updateTime
	 */
	public void setUpdateTime (Timestamp updateTime) {
	  this.updateTime = updateTime;
	}
	/** 
	 * 获取 更新时间
	 * @param updateTime
	 */
	public Timestamp getUpdateTime () {
	  return updateTime;
	}
	/** 
	 * 设置 更新人
	 * @param updateBy
	 */
	public void setUpdateBy (String updateBy) {
	  this.updateBy = updateBy;
	}
	/** 
	 * 获取 更新人
	 * @param updateBy
	 */
	public String getUpdateBy () {
	  return updateBy;
	}
	/** 
	 * 设置 创建时间
	 * @param createTime
	 */
	public void setCreateTime (Timestamp createTime) {
	  this.createTime = createTime;
	}
	/** 
	 * 获取 创建时间
	 * @param createTime
	 */
	public Timestamp getCreateTime () {
	  return createTime;
	}
	/** 
	 * 设置 创建人
	 * @param createBy
	 */
	public void setCreateBy (String createBy) {
	  this.createBy = createBy;
	}
	/** 
	 * 获取 创建人
	 * @param createBy
	 */
	public String getCreateBy () {
	  return createBy;
	}
}