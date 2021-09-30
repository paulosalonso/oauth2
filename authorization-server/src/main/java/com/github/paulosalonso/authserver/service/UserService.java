package com.github.paulosalonso.authserver.service;

import com.github.paulosalonso.authserver.repository.UserRepository;
import com.github.paulosalonso.authserver.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Transactional
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findById(username)
        .map(UserMapper::toUserDetails)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
