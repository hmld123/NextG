package com.github.hmld.system.func.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.github.hmld.common.exception.EnityRequiredException;
import com.github.hmld.common.utils.StringUtils;
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
	/** 前端组件 */
	private String component;
	/** 功能类型 
	 * m,menu 菜单类型
	 * f,function 功能类型
	 * c,control 按钮类型
	 * */
	private String funcType;
	/** 图标 */
	private String icon;
	/** 是否外链 */
	private String isFrame;
	
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
	//子节点
	private List<SysFuncTreeView> children = new ArrayList<SysFuncTreeView>();
	
	// 获取子节点
	public List<SysFuncTreeView> getChildren() {
		return children;
	}
	// 设置子节点
	public void setChildren(List<SysFuncTreeView> children) {
		this.children = children;
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
	/**
	 * 获取组件地址
	 * @return
	 */
	public String getComponent() {
		return component;
	}
	/**
	 * 设置组件地址
	 * @param component
	 */
	public void setComponent(String component) {
		this.component = component;
	}
	/**
	 * 获取功能类型
	 * @return
	 */
	public String getFuncType() {
		return funcType;
	}
	/**
	 * 设置功能类型
	 * @param funcType
	 */
	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}
	/**
	 * 获取图标
	 * @return
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置图标
	 * @param icon
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取是否外链
	 * @return
	 */
	public String getIsFrame() {
		return isFrame;
	}
	/**
	 * 设置是否外链
	 * @param isFrame
	 */
	public void setIsFrame(String isFrame) {
		this.isFrame = isFrame;
	}
	/**
	 * 必填项校验
	 * @param sysFunc
	 * @throws EnityRequiredException
	 */
	public static void retryEnity(SysFunc sysFunc) throws EnityRequiredException{
		if (StringUtils.isNull(sysFunc)) {
			throw new EnityRequiredException("数据体不能为空");
		}
//		if (StringUtils.isEmpty(sysFunc.getFuncPk())) {
//			throw new EnityRequiredException("数据主键不能为空");
//		}
		if (StringUtils.isEmpty(sysFunc.getFuncName())) {
			throw new EnityRequiredException("数据功能名称不能为空");
		}
//		if (StringUtils.isEmpty(sysFunc.getFuncUrl())) {
//			throw new EnityRequiredException("数据请求地址不能为空");
//		}
		if(StringUtils.isEmpty(sysFunc.getFuncType())) {
			throw new EnityRequiredException("数据功能类型不能为空");
		}
//		if (StringUtils.isEmpty(sysFunc.getFunPerms())) {
//			throw new EnityRequiredException("数据功能权限编码不能为空");
//		}
		return;
	}
}
