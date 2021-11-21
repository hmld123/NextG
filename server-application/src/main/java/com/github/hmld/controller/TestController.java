package com.github.hmld.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.hmld.common.core.domain.AjaxResult;
import com.github.hmld.common.logger.Log;
import com.github.hmld.common.utils.MsageUtils;

@Controller
@RequestMapping("/py")
public class TestController {
	
  @Log(level = 0,detail = "execPython()")
  @GetMapping("/do")
  @PreAuthorize("@ss.hasPermi('py:test')")
  @ResponseBody
  public AjaxResult execPython() {
    return AjaxResult.success(MsageUtils.message("sys.version"));
  }
}
