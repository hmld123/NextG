package com.github.hmld.system.func.service;

import java.util.Set;

public interface ISysRoleFuncService {
	/**
	 * 获取用户的权限
	 * @param userPk
	 * @return
	 */
	public Set<String> queryUserPermsByID(String userPk);
}
