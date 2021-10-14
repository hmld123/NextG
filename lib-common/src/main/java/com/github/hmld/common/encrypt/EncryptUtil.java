package com.github.hmld.common.encrypt;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
/**
 * 加密工具类
 * @author hmld
 * ————————————————
 * 版权声明：本文为CSDN博主「paavochan」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/theUncle/article/details/100156976
 */
public class EncryptUtil {

  public static final String ALGORITHM = "PBEWITHMD5andDES";
  
  public static final int ITERATION_COUNT = 100;
  
  
  public static byte[] initSalt() throws Exception{
    //实例化安全随机数
    SecureRandom random = new SecureRandom();
    return random.generateSeed(8);
  }
  
  /***
   * 转换密钥
   * @param password 密码
   * @return 密钥
   * @throws Exception
   */
  private static Key toKey(String password) throws Exception{
    //密钥材料
    PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
    //实例化
    SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
    //生成密钥
    return factory.generateSecret(keySpec);
  }
  
  /***
   * 加密
   * @param data 待加密数据ALGORITHM
   * @param password 密钥
   * @param salt
   * @return
   * @throws Exception
   */
  public static byte[] encrypt(byte[] data, String password, byte[] salt) throws Exception{
    //转换密钥
    Key key = toKey(password);
    //实例化PBE参数材料
    PBEParameterSpec spec = new PBEParameterSpec(salt, ITERATION_COUNT);
    //实例化
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    //初始化
    cipher.init(Cipher.ENCRYPT_MODE, key, spec);
    return cipher.doFinal(data);
  }
  
  
  /***
   * 解密
   * @param data 待解密数据
   * @param password 密钥
   * @param salt
   * @return
   * @throws Exception
   */
  public static byte[] decrypt(byte[] data, String password, byte[] salt) throws Exception{
    //转换密钥
    Key key = toKey(password);
    //实例化PBE参数材料
    PBEParameterSpec spec = new PBEParameterSpec(salt, ITERATION_COUNT);
    //实例化
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    //初始化  
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
    //执行操作  
    return cipher.doFinal(data);  
  }
  
  
  private static String showByteArray(byte[] data) {
    if(null == data) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    for(byte b : data) {
      sb.append(b).append(",");
    }
    sb.deleteCharAt(sb.length()-1);
    sb.append("");
    return sb.toString();
  }
  
  
  public static void main(String[] args) throws Exception{
    byte[] salt = initSalt();
    System.out.println("salt："+showByteArray(salt));
    String password = "1111";
    System.out.println("口令："+password);
    String data = "PBE数据";
    System.out.println("加密前数据：String:"+data);
    System.out.println("加密前数据：byte[]:"+showByteArray(data.getBytes()));
    
    byte[] encryptData = encrypt(data.getBytes(), password, salt);
    System.out.println("加密后数据：byte[]:"+showByteArray(encryptData));
    
    byte[] decryptData = decrypt(encryptData, password, salt);
        System.out.println("解密后数据: byte[]:"+showByteArray(decryptData));
        System.out.println("解密后数据: string:"+new String(decryptData));
  }
}
