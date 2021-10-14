package com.github.hmld.common.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 获取字符串的Hash值工具类
 * @author hmld
 * ————————————————
 * 版权声明：本文为CSDN博主「鱼七喵」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_34113011/article/details/107840853
 */
public class MD5Util {
	/**
	* 获取Hash值
	*/
	public static String hashKeyForDisk(String key) {
	   String cacheKey;
	   try {
	   	   //Java利用MessageDigest获取字符串MD5
	       final MessageDigest mDigestData = MessageDigest.getInstance("MD5");
	       mDigestData.update(key.getBytes());
	       cacheKey = bytesToHexString(mDigestData.digest());
	   } catch (NoSuchAlgorithmException e) {
	       cacheKey = String.valueOf(key.hashCode());
	   }
	   return cacheKey;
	}
	
	private static String bytesToHexString(byte[] bytes) {
	   StringBuilder sb = new StringBuilder();
	   for (int i = 0; i < bytes.length; i++) {
	       String hex = Integer.toHexString(0xFF & bytes[i]);
	       if (hex.length() == 1) {
	           sb.append('0');
	       }
	       sb.append(hex);
	   }
	   return sb.toString();
	}
}
