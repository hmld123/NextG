package com.github.hmld.system.user.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.hmld.common.enums.DelFlgEmnu;
import com.github.hmld.common.enums.UseFlgEmnu;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.framework.security.core.domain.LoginUser;
import com.github.hmld.system.user.domain.SysUser;
import com.github.hmld.system.user.service.ISysUserPermsService;
import com.github.hmld.system.user.service.ISysUserRoleService;
import com.github.hmld.system.user.service.ISysUserService;
/**
 * 用户登录验证实现
 * @author hmld
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private ISysUserService userService;
	@Autowired
	private ISysUserRoleService userRoleService;
	@Autowired
	private ISysUserPermsService userPermsService;
	/**
	 * 用户登录验证
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = userService.querySysUserByName(username);
		if (StringUtils.isNotNull(user)) {
			throw new UsernameNotFoundException(StringUtils.format("用户 {} 不存在", username));
		}
		if (user.getDelFlag().equals(DelFlgEmnu.DEL_TYPE)) {
			throw new UsernameNotFoundException(StringUtils.format("用户 {} 已被删除", username));
		}
		if (user.getStatus().equals(UseFlgEmnu.DEL_TYPE)) {
			throw new UsernameNotFoundException(StringUtils.format("用户 {} 已被停用", username));
		}
		return createLoginUser(user);
	}

	public UserDetails createLoginUser(SysUser user) {
		Set<String> roles = userRoleService.queryUserRoleByUserID(user.getUserPk());
		roles.parallelStream().map(s -> "GROUP_"+s).collect(Collectors.toSet());
		roles.add("ROLE_ACTIVITI_USER");
		List<SimpleGrantedAuthority> collect = roles.stream().map(
        SimpleGrantedAuthority::new).collect(Collectors.toList());
		Set<String> perms = userPermsService.queryPermsByUserID(user.getUserPk());
		return new LoginUser(user, perms, collect);
	}
	
}
