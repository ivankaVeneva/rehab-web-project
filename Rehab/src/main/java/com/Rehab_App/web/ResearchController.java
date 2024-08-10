package com.Rehab_App.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResearchController {

  @GetMapping("/research")
  public String allResearch() {
    return "research";
  }

}
