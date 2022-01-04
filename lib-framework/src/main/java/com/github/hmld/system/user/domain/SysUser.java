package com.github.hmld.system.user.domain;
import java.sql.Timestamp;
/**
 * 用户
 * @author hmld
 *
 */
public class SysUser {
	/** 用户主键*/
	private String userPk;
	/** 用户名*/
	private String userName;
	/** 昵称*/
	private String nickName;
	/** 头像*/
	private String avatar;
	/** 介绍*/
	private String introduction;
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
	/** 用户密码*/
	private SysUserPasswordHistory userPassWord;
	
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
	 * 设置 用户名
	 * @param userName
	 */
	public void setUserName (String userName) {
	  this.userName = userName;
	}
	/** 
	 * 获取 用户名
	 * @param userName
	 */
	public String getUserName () {
	  return userName;
	}
	/** 
	 * 设置 昵称
	 * @param nickName
	 */
	public void setNickName (String nickName) {
	  this.nickName = nickName;
	}
	/** 
	 * 获取 昵称
	 * @param nickName
	 */
	public String getNickName () {
	  return nickName;
	}
	/**
	 * 获取头像
	 * @return
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * 设置头像
	 * @param avatar
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * 获取介绍
	 * @return
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * 设置介绍
	 * @param introduction
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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
	/**
	 * 获取 用户密码
	 * @return
	 */
	public SysUserPasswordHistory getUserPassWord() {
		return userPassWord;
	}
	/**
	 * 设置 用户密码
	 * @param userPassWord
	 */
	public void setUserPassWord(SysUserPasswordHistory userPassWord) {
		this.userPassWord = userPassWord;
	}

}