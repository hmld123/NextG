package com.github.hmld.system.user.domain;
import java.sql.Timestamp;
/**
 * 用户密码历史
 * @author hmld
 *
 */
public class SysUserPasswordHistory {
	/** 用户变动历史主键*/
	private String userHistoryPk;
	/** 用户主键*/
	private String userPk;
	/** 用户名*/
	private String userName;
	/** 昵称*/
	private String nickName;
	/** 密码*/
	private String userPassword;
	/** 盐*/
	private String salt;
	/** 状态(0代表正常，1代表停用)*/
	private String status;
	/** 密码更新时间*/
	private Timestamp updateTime;
	/** 密码更新人*/
	private String updateBy;
	/** 创建时间*/
	private Timestamp createTime;
	/** 创建人*/
	private String createBy;
	/** 
	 * 设置 用户变动历史主键
	 * @param userHistoryPk
	 */
	public void setUserHistoryPk (String userHistoryPk) {
	  this.userHistoryPk = userHistoryPk;
	}
	/** 
	 * 获取 用户变动历史主键
	 * @param userHistoryPk
	 */
	public String getUserHistoryPk () {
	  return userHistoryPk;
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
	 * 设置 密码
	 * @param userPassword
	 */
	public void setUserPassword (String userPassword) {
	  this.userPassword = userPassword;
	}
	/** 
	 * 获取 密码
	 * @param userPassword
	 */
	public String getUserPassword () {
	  return userPassword;
	}
	/** 
	 * 设置 盐
	 * @param salt
	 */
	public void setSalt (String salt) {
	  this.salt = salt;
	}
	/** 
	 * 获取 盐
	 * @param salt
	 */
	public String getSalt () {
	  return salt;
	}
	/** 
	 * 设置 状态(0代表正常，1代表停用)
	 * @param status
	 */
	public void setStatus (String status) {
	  this.status = status;
	}
	/** 
	 * 获取 状态(0代表正常，1代表停用)
	 * @param status
	 */
	public String getStatus () {
	  return status;
	}
	/** 
	 * 设置 密码更新时间
	 * @param updateTime
	 */
	public void setUpdateTime (Timestamp updateTime) {
	  this.updateTime = updateTime;
	}
	/** 
	 * 获取 密码更新时间
	 * @param updateTime
	 */
	public Timestamp getUpdateTime () {
	  return updateTime;
	}
	/** 
	 * 设置 密码更新人
	 * @param updateBy
	 */
	public void setUpdateBy (String updateBy) {
	  this.updateBy = updateBy;
	}
	/** 
	 * 获取 密码更新人
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