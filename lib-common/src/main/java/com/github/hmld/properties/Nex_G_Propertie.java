package com.github.hmld.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * 自定义配置
 * @author hmldt
 *
 */
@ConfigurationProperties("nextg")
@Component
public class Nex_G_Propertie {
	
	/** 版本号 */
	private String version;
	
	/** 上传下载默认路径*/
	private String upload;
	
	/** 代码默认包路径*/
	private String genbasepack;
	
	/**
	 * 获取版本号
	 * @return 版本号
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * 设置获取版本号
	 * @param version
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取上传下载默认路径
	 * @return
	 */
  public String getUpload() {
    return upload;
  }
  /**
   * 设置上传下载默认路径
   * @param upload
   */
  public void setUpload(String upload) {
    this.upload = upload;
  }
  /**
   * 获取代码默认包路径
   * @return
   */
	public String getGenbasepack() {
		return genbasepack;
	}
	/**
	 * 设置代码默认包路径
	 * @param genbasepack
	 */
	public void setGenbasepack(String genbasepack) {
		this.genbasepack = genbasepack;
	}
	
}
