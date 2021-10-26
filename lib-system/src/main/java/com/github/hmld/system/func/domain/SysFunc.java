package com.github.hmld.system.func.domain;

import java.sql.Timestamp;
/**
 * 系统功能
 * @author hmld
 *
 */
public class SysFunc {
	/** 功能主键 */
	private String funcPk;
	/** 父节点*/
	private String parentId;
	/** 功能名称*/
	private String funcName;
	/** 请求地址*/
	private String funcUrl;
	/** 功能权限编码*/
	private String funPerms;
	/** 功能显示状态*/
	private String visible;
	/** 排序*/
	private Integer orderNum;
	/** 功能说明*/
	private String funcExplanation;
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
	 * 获取 父节点
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置 父节点
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取 功能名称
	 * @return
	 */
	public String getFuncName() {
		return funcName;
	}
	/**
	 * 设置 功能名称
	 * @param funcName
	 */
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	/**
	 * 获取 请求地址
	 * @return
	 */
	public String getFuncUrl() {
		return funcUrl;
	}
	/**
	 * 设置 请求地址
	 * @param funcUrl
	 */
	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}
	/**
	 * 获取 功能权限编码
	 * @return
	 */
	public String getFunPerms() {
		return funPerms;
	}
	/**
	 * 设置 功能权限编码
	 * @param funPerms
	 */
	public void setFunPerms(String funPerms) {
		this.funPerms = funPerms;
	}
	/**
	 * 获取 功能显示状态
	 * @return
	 */
	public String getVisible() {
		return visible;
	}
	/**
	 * 设置 功能显示状态
	 * @param visible
	 */
	public void setVisible(String visible) {
		this.visible = visible;
	}
	/**
	 * 获取 排序
	 * @return
	 */
	public Integer getOrderNum() {
		return orderNum;
	}
	/**
	 * 设置 排序
	 * @param orderNum
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取 功能说明
	 * @return
	 */
	public String getFuncExplanation() {
		return funcExplanation;
	}
	/**
	 * 设置 功能说明
	 * @param funcExplanation
	 */
	public void setFuncExplanation(String funcExplanation) {
		this.funcExplanation = funcExplanation;
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
	 * 设置 删除标志
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
	 * 获取 updateBy
	 * @return
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置 updateBy
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
