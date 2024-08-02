package com.Rehab_App.web;

import com.Rehab_App.model.dto.UserLoginDTO;
import com.Rehab_App.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class LoginController {

  @GetMapping("/login")
  public String login() {
    return "auth-login";
  }
}
