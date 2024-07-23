package com.Rehab_App.web;

import com.Rehab_App.model.user.RehabUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home(@AuthenticationPrincipal UserDetails userDetails,
      Model model) {

    if (userDetails instanceof RehabUserDetails rehabUserDetails) {
      model.addAttribute("welcomeMessage", rehabUserDetails.getFullName());
    } else {
      model.addAttribute("welcomeMessage", "Anonymous");
    }

    return "index";
  }
}
