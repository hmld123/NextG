package com.github.hmld.system.user.contraller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.hmld.common.constant.Constants;
import com.github.hmld.common.core.domain.AjaxResult;
import com.github.hmld.common.core.domain.module.LoginBody;
import com.github.hmld.framework.security.impls.SysLoginService;

@RestController
public class SysLoginController {
	@Autowired
	private SysLoginService loginService;

	/**
	 * 登录
	 * @param loginBody 登录信息
	 * @return 结果
	 */
	@PostMapping("/login")
	public AjaxResult login(@RequestBody LoginBody loginBody) {
		AjaxResult ajaxResult = AjaxResult.success();
		String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
		ajaxResult.put(Constants.TOKEN, token);
		return ajaxResult;
	}
}
