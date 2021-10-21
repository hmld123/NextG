package com.github.hmld.system.user.contraller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.hmld.common.core.domain.AjaxResult;

@Controller
public class LoginController {
	@GetMapping("/lin")
	public String login() {
		return "login";
	}
	@GetMapping("/out")
	public String logout() {
		return "logout";
	}
	@PreAuthorize("hasAuthority('read')")
	@GetMapping("u/list")
	@ResponseBody
	public AjaxResult getAll() {
		return AjaxResult.success("list is yours!");
	}

	@PreAuthorize("hasAuthority('delete')")
	@GetMapping("u/{id}")
	@ResponseBody
	public AjaxResult getAll(@PathVariable Long id) {
		return AjaxResult.success(id+" is delete");
	}
	
	@GetMapping("/u/url/api")
	@ResponseBody
	public AjaxResult getUrl(HttpServletRequest req) {
		return AjaxResult.success(req.getRemoteAddr());
	}
}
