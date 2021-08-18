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
	
}
