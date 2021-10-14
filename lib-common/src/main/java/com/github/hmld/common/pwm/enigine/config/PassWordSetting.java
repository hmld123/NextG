package com.github.hmld.common.pwm.enigine.config;
/**
 * 密码设置
 * @author hmld
 *
 */
public class PassWordSetting {
	
	/*
	 * 是否包含中文
	 */
	private boolean have_chinese = false;
	
	/**
	 * 是否包含特殊字符
	 */
	private boolean have_special = false;
	
	/**
	 * 是否包含数字
	 */
	private boolean have_number = false;
	
	/**
	 * 密码长度（默认长度8个字）
	 */
	private int passord_length = 8;
	
	/**
	 * 获取包含中文配置
	 * @return
	 */
	public boolean isHave_chinese() {
		return have_chinese;
	}
	/**
	 * 设置包含中文配置
	 * @param have_chinese
	 */
	public void setHave_chinese(boolean have_chinese) {
		this.have_chinese = have_chinese;
	}
	/**
	 * 获取特殊字符配置
	 * @return
	 */
	public boolean isHave_special() {
		return have_special;
	}
	/**
	 * 设置特殊字符配置
	 * @param have_special
	 */
	public void setHave_special(boolean have_special) {
		this.have_special = have_special;
	}
	
	/**
	 * 获取密码长度
	 * @return
	 */
	public int getPassord_length() {
		return passord_length;
	}

	/**
	 * 设置密码长度
	 * @param passord_length
	 */
	public void setPassord_length(int passord_length) {
		this.passord_length = passord_length;
	}
	
	/**
	 * 获取包含数字设置
	 * @return
	 */
	public boolean isHave_number() {
		return have_number;
	}
	
	/**
	 * 获取包含数字设置
	 * @param have_number
	 */
	public void setHave_number(boolean have_number) {
		this.have_number = have_number;
	}
	
}
