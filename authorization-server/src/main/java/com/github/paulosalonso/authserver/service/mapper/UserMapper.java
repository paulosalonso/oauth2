package com.github.paulosalonso.authserver.service.mapper;

import static java.util.stream.Collectors.toList;

import com.github.paulosalonso.authserver.repository.model.UserEntity;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public final class UserMapper {

  static boolean ENABLED = true;
  static boolean ACCOUNT_NON_EXPIRED = true;
  static boolean CREDENTIALS_NON_EXPIRED = true;
  static boolean ACCOUNT_NON_LOCKED = true;

  private UserMapper() {}

  public static User toUserDetails(UserEntity user) {
    return new User(
        user.getUsername(),
        user.getPassword(),
        ENABLED,
        ACCOUNT_NON_EXPIRED,
        CREDENTIALS_NON_EXPIRED,
        ACCOUNT_NON_LOCKED,
        toGrantedAuthorities(user.getAuthorities()));
  }

  private static List<GrantedAuthority> toGrantedAuthorities(List<String> authorities) {
    return authorities.stream()
        .map(SimpleGrantedAuthority::new)
        .collect(toList());
  }
}
