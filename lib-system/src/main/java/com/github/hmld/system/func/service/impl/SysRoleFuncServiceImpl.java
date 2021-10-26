package com.github.hmld.system.func.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hmld.system.func.mapper.SysRoleFuncMapper;
import com.github.hmld.system.func.service.ISysRoleFuncService;

@Service
public class SysRoleFuncServiceImpl implements ISysRoleFuncService {
	@Autowired
	private SysRoleFuncMapper sysRoleFuncMapper;
	/**
	 * 获取用户的权限
	 */
	public Set<String> queryUserPermsByID(String userPk){
		return sysRoleFuncMapper.queryUserPermsByID(userPk);
	}
}
