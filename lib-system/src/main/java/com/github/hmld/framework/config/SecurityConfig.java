package com.github.hmld.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

import com.github.hmld.system.user.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * user service 
	 * <p>实现了 UserDetailsService 接口的自定义 用户 servic 服务</p>
	 */
	@Autowired
	private UserDetailsServiceImpl loginUserService;
	/**
	 * 实现了 AuthenticationEntryPoint 接口的组件
	 * <p>用于将认证的报错信息返回到 response 中并显示到浏览器页面上</p>
	 */
	@Autowired
	private AuthenticationEntryPointImpl unauthorizedHandler;
	/**
	 * 自定义退出登录处理
	 * <p>用于实现自定义的推出逻辑</p>
	 */
	@Autowired
	private LogoutSuccessHandlerImpl logoutSucessHandler;
	/**
	 * 自定义token过滤器
	 * <p>用于实现自定义token验证</p>
	 */
	@Autowired
	private JwtAuthenticationTokenFilter authenticationTokenFilter;
	/**
	 * 跨域过滤器
	 */
	@Autowired
	private CorsFilter corsFilter;
	

  /**
   * 解决 无法直接注入 AuthenticationManager
   *
   * @return
   * @throws Exception
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception
  {
      return super.authenticationManagerBean();
  }

	
	/**
	 * 允许基于选择匹配在资源级配置基于网络的安全性。
	 * <p>
	 * 也就是对角色的权限——所能访问的路径做出限制
	 * </p>
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		// 认证失败处里类
		exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().
		// 禁用 session
		sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
		// 过滤请求
		authorizeRequests().
		// 设置可以用于匿名访问的路径
		antMatchers("/login").anonymous().
		anyRequest().authenticated().and().
		// 这里不需要csrf防护并且禁用缓存
		csrf().disable().headers().cacheControl();
		// 设置登录失败的处理方法
		http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSucessHandler);
		// 自定义token过滤器，用于实现自定义token验证
		http.addFilterAfter(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterAfter(corsFilter,JwtAuthenticationTokenFilter.class);
		http.addFilterAfter(corsFilter,LogoutFilter.class);
	}

	/**
	 * 用于通过允许AuthenticationProvider容易地添加来建立认证机制
	 * <p>
	 * 也就是说用来记录账号，密码，角色信息。
	 * </p>
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginUserService).passwordEncoder(encoder());
	}

	/**
	 * 用于影响全局安全性(配置资源，设置调试模式，通过实现自定义防火墙定义拒绝请求)的配置设置。
	 * <p>
	 * 一般用于配置全局的某些通用事物，例如静态资源等
	 * </p>
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	/**
	 * 返回自定义的加密实现
	 * 
	 * @return
	 */
	@Bean
	public static PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
