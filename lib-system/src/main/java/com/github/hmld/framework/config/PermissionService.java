package com.github.hmld.framework.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.hmld.common.core.domain.model.LoginUser;
import com.github.hmld.common.utils.ServletUtils;
import com.github.hmld.common.utils.StringUtils;
/**
 * 用户权限校验
 * @author hmld
 *
 */
@Service("ss")
public class PermissionService {

  /** 所有权限标识 */
  private static final String ALL_PERMISSION = "*:*:*";

  /** 管理员角色权限标识 */
  private static final String SUPER_ADMIN = "admin";

  private static final String ROLE_DELIMETER = ",";

  private static final String PERMISSION_DELIMETER = ",";

  @Autowired
  private TokenService tokenService;
  
  public boolean hasPermi(String permission) {
  	if (StringUtils.isEmpty(permission)) {
			return false;
		}
  	LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
    if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions()))
    {
        return false;
    }
    return hasPermissions(loginUser.getPermissions(), permission);
  }
  
  /**
   * 用户的权限中是否有该权限
   * @param permissions 用户的权限组
   * @param permission 需要娇艳的权限
   * @return true 用户有该权限，false 该用户未被授权使用当前权限
   */
	private boolean hasPermissions(Set<String> permissions, String permission) {
		return permissions.contains(ALL_PERMISSION) || permissions.contains(permission);
	}
	
}
