package com.github.hmld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.hmld.common.logger.Log;

@Controller
@RequestMapping("/py")
public class TestController {
  @Log(level = 0,detail = "execPython()")
  @GetMapping("/do")
  public void execPython() {
    try {
      
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
