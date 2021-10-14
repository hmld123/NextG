package com.github.hmld.common.pwm.enigine.bastdata;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 生存需要的基本参数字典
 * @author hmld
 *
 */
public class Materials {
	
	/**
	 * 大写英文
	 */
	public static final String[] UPPERCASE = {
			"a","b","c","d","e","f",
			"g","h","i","j","k","l",
			"m","n","o","p","q","r",
			"s","t","u","v","w","x",
			"y","z"
	};
	
	/**
	 * 小写英文
	 */
	public static final String[] LOWERCASE = {
			"A","B","C","D","E","F",
			"G","H","I","J","K","L",
			"M","N","O","P","Q","R",
			"S","T","U","V","W","X",
			"Y","Z"
	};
	
	/**
	 * 数字
	 */
	public static final String[] NUMBER = {
			"0","1","2","3","4",
			"5","6","7","8","9"
	};
	
	/**
	 * 特殊字符
	 */
	public static final String[] SPECIAL_CHAR = {
			"-","_","!","@","#",
			"$","%","^","&","*",
			":",";","\"","'",".",
			","
	} ;
	
	/**
	 * 获取中文
	 */
	public static String[] CHINESE = getchinese();
	
	/**
	 * 读取中文字符
	 * @return
	 */
	private static String[] getchinese() {
		try {
			InputStream fileIn = Materials.class.getClassLoader().getResourceAsStream("language_data/chinese.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(fileIn));
			BufferedReader br = new BufferedReader(reader);
			List<String> strList = new ArrayList<String>();
			String line;
			while ((line = br.readLine()) != null) {
				char[] chars = line.toCharArray();
				for (char c : chars) {
					String cStr = String.valueOf(c);
					strList.add(cStr);
				}
			}
			br.close();
			String[] strs = new String[strList.size()];
			for (int i = 0; i < strList.size();i++) {
				strs[i] = strList.get(i);
			}
			return strs;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
