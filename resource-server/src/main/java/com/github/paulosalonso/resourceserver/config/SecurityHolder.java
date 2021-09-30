package com.github.paulosalonso.resourceserver.config;

import net.minidev.json.JSONArray;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class SecurityHolder {

  private final SecurityContext securityContext;

  public SecurityHolder() {
    this.securityContext = SecurityContextHolder.getContext();
  }

  public boolean hasScope(String scope) {
    return ((JSONArray) ((BearerTokenAuthentication) securityContext.getAuthentication())
        .getTokenAttributes()
        .get("scope"))
        .contains(scope);
  }
}
