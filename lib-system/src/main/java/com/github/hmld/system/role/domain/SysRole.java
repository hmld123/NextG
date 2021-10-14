package com.github.hmld.system.role.domain;
import java.math.BigDecimal;
import java.sql.Timestamp;
/**
 * 权限设置
 * @author hmld
 *
 */
public class SysRole {
	/** 角色主键*/
	private String rolePk;
	/** 角色名称*/
	private String roleName;
	/** 角色键值*/
	private String roleKey;
	/** 显示顺序*/
	private BigDecimal roleSort;
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
	 * 设置 角色名称
	 * @param roleName
	 */
	public void setRoleName (String roleName) {
	  this.roleName = roleName;
	}
	/** 
	 * 获取 角色名称
	 * @param roleName
	 */
	public String getRoleName () {
	  return roleName;
	}
	/** 
	 * 设置 角色键值
	 * @param roleKey
	 */
	public void setRoleKey (String roleKey) {
	  this.roleKey = roleKey;
	}
	/** 
	 * 获取 角色键值
	 * @param roleKey
	 */
	public String getRoleKey () {
	  return roleKey;
	}
	/** 
	 * 设置 显示顺序
	 * @param roleSort
	 */
	public void setRoleSort (BigDecimal roleSort) {
	  this.roleSort = roleSort;
	}
	/** 
	 * 获取 显示顺序
	 * @param roleSort
	 */
	public BigDecimal getRoleSort () {
	  return roleSort;
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