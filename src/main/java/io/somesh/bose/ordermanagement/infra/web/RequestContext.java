package io.somesh.bose.ordermanagement.infra.web;

import org.slf4j.MDC;

public enum RequestContext {

  CORRELATION_ID_KEY("x-corrId");

  private final String value;

  RequestContext(String value) {
    this.value = value;
  }

  public String value() {
    return this.value;
  }

  public static void put(RequestContext contextKey, String value) {
    MDC.put(contextKey.value(), value);
  }

  public static String get(RequestContext contextKey) {
    return MDC.get(contextKey.value());
  }

  public static void clearThreadLocalContext() {
    MDC.clear();
  }
}
