package com.github.hmld.system.user.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.hmld.common.core.domain.model.LoginUser;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.system.func.service.ISysRoleFuncService;
import com.github.hmld.system.role.service.ISysUserRoleService;
import com.github.hmld.system.user.domain.SysUser;
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
	private ISysRoleFuncService roleFuncService;
	/**
	 * 用户登录验证
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = userService.querySysUserByName(username);
		if (StringUtils.isNotNull(user)) {
			
		}
		if (user.getDelFlag().equals("1")) {
			
		}
		if (user.getStatus().equals("1")) {
			
		}
		return createLoginUser(user);
	}

	public UserDetails createLoginUser(SysUser user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String thpassword = passwordEncoder.encode(user.getUserPassWord().getUserPassword());
		user.getUserPassWord().setUserPassword(thpassword);
		Set<String> roles = userRoleService.queryUserRoleByUserID(user.getUserPk());
		roles.parallelStream().map(s -> "GROUP_"+s).collect(Collectors.toSet());
		roles.add("ROLE_ACTIVITI_USER");
		List<SimpleGrantedAuthority> collect = roles.stream().map(
        SimpleGrantedAuthority::new).collect(Collectors.toList());
		Set<String> perms = roleFuncService.queryUserPermsByID(user.getUserPk());
		return new LoginUser(user, perms, collect);
	}
	
}
