package com.Rehab_App.web;

import com.Rehab_App.model.dto.UserRegistrationDTO;
import com.Rehab_App.service.UserService;
import com.Rehab_App.web.aop.WarnIfExecutionExceeds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class RegistrationController {

  private final UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @ModelAttribute("registerDTO")
  public UserRegistrationDTO registerDTO() {
    return new UserRegistrationDTO();
  }

  @GetMapping("/register")
  public String register() {
    return "auth-register";
  }

  @WarnIfExecutionExceeds(
      threshold = 1000
  )
  @PostMapping("/register")
  public String register(UserRegistrationDTO registerDTO) {

    userService.registerUser(registerDTO);

    return "redirect:/";
  }
}
