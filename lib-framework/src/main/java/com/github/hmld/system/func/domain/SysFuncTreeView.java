package com.github.hmld.system.func.domain;

import java.util.ArrayList;
import java.util.List;

public class SysFuncTreeView extends SysFunc{
	// 子节点
	private List<SysFuncTreeView> children = new ArrayList<SysFuncTreeView>();
	
	// 获取子节点
	public List<SysFuncTreeView> getChildren() {
		return children;
	}
	// 设置子节点
	public void setChildren(List<SysFuncTreeView> children) {
		this.children = children;
	}
	
	
}
