package com.github.hmld.framework.security.impls;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.hmld.common.constant.Constants;
import com.github.hmld.common.core.redis.RedisCache;
import com.github.hmld.common.utils.ServletUtils;
import com.github.hmld.common.utils.StringUtils;
import com.github.hmld.common.utils.ip.AddressUtils;
import com.github.hmld.common.utils.ip.IpUtils;
import com.github.hmld.framework.security.core.domain.LoginUser;

import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
/**
 * token 工具
 * @author hmld
 *
 */
@Component
public class TokenService {
	@Autowired
	private RedisCache redisCache;
	
	// token 标识
	@Value("${token.header}")
	private String tokenHeader;
	// token 密钥
	@Value("${token.secret}")
	private String tokenSecret;
	// token 有效期（默认30分钟）
	@Value("${token.expireTime}")
	private Integer tokenExpireTime;

  protected static final long MILLIS_SECOND = 1000;

  protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

  private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;
  
  /**
   * 设置用户身份信息
   * @param loginUser
   */
  public void setLoginUser(LoginUser loginUser) {
  	if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
  		refreshToken(loginUser);
		}
  }
  
  /**
   * 获取用户身份信息
   * @param request
   * @return
   */
  public LoginUser getLoginUser(HttpServletRequest request) {
  	String token = getToken(request);
  	if (StringUtils.isNotEmpty(token)) {
			try {
				Claims claims = praseToken(token);
				String userKey = (String) claims.get(Constants.LOGIN_USER_KEY);
				LoginUser loginUser = redisCache.getCacheObject(getTokenKey(userKey));
				return loginUser;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
  	}else {
			return null;
		}
  }
  
  /**
   * 删除用户信息
   * @param token
   */
  public void delLoginUser(String token) {
  	if (StringUtils.isNotEmpty(token)) {
			String userKey = getTokenKey(token);
			redisCache.deleteObject(userKey);
  	}
  }
  /**
   * 设置token
   * @param loginUser 用户信息
   * @return token
   */
  public String regisToken(LoginUser loginUser) {
  	String token = StringUtils.genPkStr();
  	loginUser.setToken(token);
  	setUserAgent(loginUser);
  	refreshToken(loginUser);
  	Map<String, Object> claims = new HashMap<String, Object>();
  	claims.put(Constants.LOGIN_USER_KEY, token);
  	return createToken(claims);
  }
  
  /**
   * 创建token
   * @param claims token数据
   * @return token
   */
  private String createToken(Map<String, Object> claims) {
		String token = Jwts.builder().setClaims(claims).signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, tokenSecret).compact();
  	return token;
	}

  /**
   * 设置用户代理信息
   * @param loginUser
   */
	private void setUserAgent(LoginUser loginUser) {
		UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
		String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
		loginUser.setIpAddr(ip);
		loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
		loginUser.setBrowser(userAgent.getBrowser().getName());
		loginUser.setOs(userAgent.getOperatingSystem().getName());
	}

	/**
   * 解析token
   * @param token
   * @return
   */
  private Claims praseToken(String token) {
		return Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
	}
  
  /**
   * 获取 token
   * @param request
   * @return token
   */
	private String getToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
			token = token.replace(Constants.TOKEN_PREFIX, "");
		}
		return token;
	}
	
	/**
	 * 验证token有效期，相差不足20分钟，自动刷新缓存
	 * @param loginUser
	 */
	public void verifyToken(LoginUser loginUser) {
		Long expireTime = loginUser.getExpireTime();
		long currentTime = System.currentTimeMillis();
		if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
			refreshToken(loginUser);
		}
	}
	
	/**
	 * 从token中获取用户名
	 * @param token
	 * @return 用户名
	 */
	public String getUsernameFromToken(String token) {
		Claims claims = praseToken(token);
		return claims.getSubject();
	}
	
	/**
   * 刷新token有效期
   * @param loginUser
   */
	private void refreshToken(LoginUser loginUser) {
		loginUser.setLoginTime(System.currentTimeMillis());
		loginUser.setExpireTime(loginUser.getLoginTime() + tokenExpireTime * MILLIS_MINUTE);
		// 根据uuid将loginUser缓存
		String userKey = getTokenKey(loginUser.getToken());
		redisCache.setCacheObject(userKey, loginUser, tokenExpireTime, TimeUnit.MINUTES);
	}
	
	
	
	/**
	 * 获取 token 缓存key
	 * @param token
	 * @return
	 */
	private String getTokenKey(String token) {
		return Constants.LOGIN_TOKEN_KEY + token;
	}
  
}