package com.github.hmld.common.encrypt;

import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 加密解密引擎
 * @author hmld
 *
 */
public class EncryptEngine {
	/**
	 * 加密
	 * @param data 加密数据
	 * @param passdata 加密用密码
	 * @param salt 盐
	 * @return 密文
	 * @throws Exception
	 */
	public static String encode(byte[] data, Object passdata, byte[] salt) throws Exception {
		ObjectMapper rootMapper = new ObjectMapper();
    // 获取加密用密码
    String password = MD5Util.hashKeyForDisk(rootMapper.writeValueAsString(passdata)); 
  	byte[] cipherByte = PBEUtil.encrypt(data, password, salt);
  	return Base64.getEncoder().encodeToString(cipherByte);
	}
	/**
	 * 解密
	 * @param data 密文
	 * @param passdata 加密用密码
	 * @param salt 盐
	 * @return 明文
	 * @throws Exception
	 */
	public static String decode(byte[] data, Object passdata, byte[] salt) throws Exception {
		ObjectMapper rootMapper = new ObjectMapper();
		String pass = MD5Util.hashKeyForDisk(rootMapper.writeValueAsString(passdata));
		// 获得密文
		byte[] decoded = Base64.getDecoder().decode(data);
		byte[] plainByte = PBEUtil.decrypt(decoded, pass, salt);
		return new String(plainByte);
	}
}
