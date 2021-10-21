package com.github.hmld.framework.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.hmld.common.constant.Constants;
import com.github.hmld.common.core.domain.model.LoginUser;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.system.user.service.ISysUserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
/**
 * token 组件
 * @author hmld
 *
 */
@Component
public class TokenService {
	// token 自定义标识
	@Value("${token.header}")
	private String header;
	//token 密钥
	@Value("${token.secret}")
	private String secret;
	//token 有效期
	@Value("${token.expireTime}")
	private int expireTime;
	
	protected static final long MILLIS_SECOND = 1000;
	
	protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
	
	protected static final long MILLIS_MINUTE_TEN  = 20 * MILLIS_MINUTE;
	
	@Autowired
	private ISysUserService userService;
	/**
	 * 获取登录用户信息
	 * @param request
	 * @return
	 */
	public LoginUser getLoginUser(HttpServletRequest request) {
		String token = getToken(request);
		if (StringUtils.isNotEmpty(token)) {
			Claims claims = parseToken(token);
			String username = (String)claims.get("login_user_name");
			LoginUser logUser = new LoginUser();
			logUser.setUser(userService.querySysUserByName(username));
			return logUser;
		}
		return null;
	}
	
	public void setLogUser(LoginUser logUser) {
		if (StringUtils.isNotNull(logUser) && StringUtils.isNotEmpty(logUser.getToken())) {
			refreshToken(logUser);
		}
	}
	
	/**
	 * 刷新 token
	 * @param loginUser
	 */
	public void refreshToken(LoginUser loginUser) {
		loginUser.setLoginTime(System.currentTimeMillis());
		loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
	}
	
	/**
	 * 获取请求中的token
	 * @param request
	 * @return token
	 */
	private String getToken(HttpServletRequest request) {
		String token = request.getHeader(header);
		if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
			token = token.replace(Constants.TOKEN_PREFIX, "");
		}
		return token;
	}
	/**
	 * 获取token信息
	 * @param token
	 * @return
	 */
	public Claims parseToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
}
