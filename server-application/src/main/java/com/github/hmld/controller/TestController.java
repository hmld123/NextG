package com.github.hmld.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.hmld.common.logger.Log;
import com.github.hmld.common.utils.MsageUtils;

@Controller
@RequestMapping("/py")
public class TestController {
  @Log(level = 0,detail = "execPython()")
  @PreAuthorize(value = "hasRole('USER')")
  @GetMapping("/do")
  public void execPython() {
    try {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (principal instanceof UserDetails) {
        String username = ((UserDetails)principal).getUsername();
        String password = ((UserDetails)principal).getPassword();
        System.out.println("username="+username);
        System.out.println("password="+password);
      }
      else {
        String username = principal.toString();
        System.out.println("username="+username);
      }
      System.out.println(MsageUtils.message("sys.version"));
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
