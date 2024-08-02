package com.Rehab_App.service.impl;

import com.Rehab_App.model.entity.UserRoleEntity;
import com.Rehab_App.model.enums.UserRoleEnum;
import com.Rehab_App.model.user.RehabUserDetails;
import com.Rehab_App.model.entity.UserEntity;
import com.Rehab_App.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RehabUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public RehabUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException {

    return userRepository
        .findByEmail(email)
        .map(RehabUserDetailsService::map)
        .orElseThrow(
            () -> new UsernameNotFoundException("User with email " + email + " not found!"));
  }

  private static UserDetails map(UserEntity userEntity) {

    return new RehabUserDetails(
        userEntity.getUuid(),
        userEntity.getEmail(),
        userEntity.getPassword(),
        userEntity.getRoles().stream().map(UserRoleEntity::getRole).map(RehabUserDetailsService::map).toList(),
        userEntity.getFirstName(),
        userEntity.getLastName()
    );
  }

  private static GrantedAuthority map(UserRoleEnum role) {
    return new SimpleGrantedAuthority(
        "ROLE_" + role
    );
  }
}
