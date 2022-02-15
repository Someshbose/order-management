package io.somesh.bose.ordermanagement.infra.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class HttpReqHeaderInterceptor extends HandlerInterceptorAdapter {

  @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if ( StringUtils.isNotBlank(request.getHeader(RequestContext.CORRELATION_ID_KEY.value()))) {
      RequestContext.put(RequestContext.CORRELATION_ID_KEY, request.getHeader(RequestContext.CORRELATION_ID_KEY.value()));
    }
    else {
      log.warn("Correlation Id is missing in the request.");
      RequestContext.put(RequestContext.CORRELATION_ID_KEY, UUID.randomUUID().toString());
    }
    return true;
  }

  @Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    RequestContext.clearThreadLocalContext();
  }
}
