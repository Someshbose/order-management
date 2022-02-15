package io.somesh.bose.ordermanagement.infra.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
public class LogRequestInterceptor extends HandlerInterceptorAdapter {

  private static final String START_TIME_KEY = "startTime";

  @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    long startTime = System.currentTimeMillis();
    log.info("Request received {} .", request.getRequestURI());

    //logParameters(request);
    request.setAttribute(START_TIME_KEY, startTime);
    return true;
  }

  @Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    Long startTime = (Long) request.getAttribute(START_TIME_KEY);
    log.info("Request processed in {} time.", System.currentTimeMillis() - startTime);
  }

  private void logParameters(HttpServletRequest request) {
    Enumeration<?> params = request.getAttributeNames();
    while (params.hasMoreElements()) {
      String paramName = (String) params.nextElement();
      log.debug("Parameter Name- {}, Value-{}", paramName, request.getAttribute(paramName));
    }

  }
}
