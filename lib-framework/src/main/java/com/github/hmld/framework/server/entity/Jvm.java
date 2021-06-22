package com.github.hmld.framework.server.entity;

import java.lang.management.ManagementFactory;

import com.github.hmld.common.utils.Arith;
import com.github.hmld.common.utils.DateUtils;

/**
 * JVM相关信息
 * @author hmld
 *
 */
public class Jvm {
	/** JVM 占用的内存总数（M）*/
	private double total;
	/** JVM 最大可用内存总数（M）*/
	private double max;
	/** JVM 空闲内存（M）*/
	private double free;
	/** Java 版本*/
	private String version;
	/** Java 路径*/
	private String home;
	public double getTotal() {
		return Arith.div(total, (1024 * 1024 * 1024), 2);
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getMax() {
		return Arith.div(max, (1024 * 1024 * 1024), 2);
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getFree() {
		return Arith.div(free, (1024 * 1024 * 1024), 2);
	}
	public void setFree(double free) {
		this.free = free;
	}
	public double getUsed() {
		return Arith.div(total - free, (1024 * 1024 * 1024), 2);
	}
	public double getUsage() {
		return Arith.mul(Arith.div(total - free, total,4), free);
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getName() {
		return ManagementFactory.getRuntimeMXBean().getVmName();
	}
	
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	
	/**
	 * Java 启动时间
	 */
	public String getStartTime() {
		return DateUtils.parseDateStr(DateUtils.YYYY_MM_DD_HH_MM_SS, DateUtils.getServerStartDate());
	}
	
	/**
	 * Java 运行时间
	 */
	public String getRunTime() {
		return DateUtils.getDatePoor(DateUtils.getNowDate(), DateUtils.getServerStartDate());
	}
}
