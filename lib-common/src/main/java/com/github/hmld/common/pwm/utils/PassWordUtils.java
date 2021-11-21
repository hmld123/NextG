package com.github.hmld.common.pwm.utils;

import com.github.hmld.common.pwm.enigine.PassWordEnigine;
import com.github.hmld.common.pwm.enigine.config.PassWordSetting;
/**
 * 密码生成功能
 * @author hmld
 *
 */
public class PassWordUtils {
	/**
	 * 自动生成密码
	 * @return 密码
	 */
	public static String genDefaultPassWord() {
		PassWordEnigine enigine = new PassWordEnigine();
		PassWordSetting passWordSetting = new PassWordSetting();
		passWordSetting.setPassord_length(8);
		passWordSetting.setHave_chinese(false);
		passWordSetting.setHave_number(true);
		passWordSetting.setHave_special(true);
		return enigine.getPassWord(passWordSetting);
	}
	
}
