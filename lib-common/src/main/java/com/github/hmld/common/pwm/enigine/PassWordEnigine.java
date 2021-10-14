package com.github.hmld.common.pwm.enigine;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.github.hmld.common.pwm.enigine.bastdata.Materials;
import com.github.hmld.common.pwm.enigine.config.PassWordSetting;
/**
 * 密码生成引擎
 * @author hmld
 *
 */
public class PassWordEnigine {
	/**
	 * 密码生成类
	 * @param setting 密码配置
	 * @return 密码字符串
	 */
	public String getPassWord(PassWordSetting setting) {
		// 获取密码长度
		int passLength = setting.getPassord_length();

		// 使用 Set 的无序特性及不重复特性对素材进行打乱和去重
		Set<String> passSet = new HashSet<String>();
		
		// 获取随机大写英文素材
		String[] uppercases = getPassStrs(passLength, Materials.UPPERCASE);
		passSetadd(passSet, uppercases);
		
		// 获取随机小写英文素材
		String[] lowercases = getPassStrs(passLength, Materials.LOWERCASE);
		passSetadd(passSet, lowercases);
		
		if (setting.isHave_number()) {
			// 获取随机数字素材
			String[] numbers = getPassStrs(passLength, Materials.NUMBER);
			passSetadd(passSet, numbers);
		}
		if (setting.isHave_special()) {
			// 获取随机特殊符号素材
			String[] specials = getPassStrs(passLength, Materials.SPECIAL_CHAR);
			passSetadd(passSet, specials);
		}
		if (setting.isHave_chinese()) {
			// 获取随机中文素材
			String[] chineses = getPassStrs(passLength, Materials.CHINESE);
			passSetadd(passSet, chineses);
		}
		
		List<String> passArray = new ArrayList<String>();
		passArray.addAll(passSet);
		// 获取随即密码
		StringBuilder passWordStr = new StringBuilder();
		for (int i = 0; i < passLength; i++) {
			int index = new Random().nextInt(passArray.size());
			passWordStr.append(passArray.get(index));
		}
		return passWordStr.toString();
	}
	
	/**
	 * 获取素材字符串数组
	 * @param passLength 密码长度
	 * @param strs 基本参数字典
	 * @return 素材字符串数组
	 */
	private static String[] getPassStrs(int passLength,String[] strs) {
		String[] datas = new String[passLength];
		for (int i = 0; i < passLength; i++) {
			int random = new Random().nextInt(strs.length);
			datas[i] = strs[random];
		}
		return datas;
	}
	
	/**
	 * 向 Set 中加入 素材字符串数组
	 * @param strSet
	 * @param strs
	 */
	private static void passSetadd(Set<String> strSet ,String[] strs){
		for (int i = 0; i < strs.length; i++) {
			strSet.add(strs[i]);
		}
	}
		
	public PassWordEnigine() {
		super();
	}

	/**
	 * 测试密码生成逻辑
	 * @param args
	 */
	public static void main(String[] args) {
		PassWordEnigine enigine = new PassWordEnigine();
		PassWordSetting passWordSetting = new PassWordSetting();
		passWordSetting.setPassord_length(14);
		passWordSetting.setHave_chinese(true);
		passWordSetting.setHave_number(true);
		passWordSetting.setHave_special(true);
		String passWordStr = enigine.getPassWord(passWordSetting);
		System.out.println(passWordStr);
	}
}
