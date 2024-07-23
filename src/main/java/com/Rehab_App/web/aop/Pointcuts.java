package com.Rehab_App.web.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
  @Pointcut("@annotation(WarnIfExecutionExceeds)")
  void onWarnIfExecutionTimeExceeds(){}
}
