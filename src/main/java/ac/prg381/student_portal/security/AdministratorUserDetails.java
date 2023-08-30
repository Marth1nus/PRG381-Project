package ac.prg381.student_portal.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ac.prg381.student_portal.entities.Administrator;

public class AdministratorUserDetails implements UserDetails {
  private final Administrator administrator;

  public AdministratorUserDetails(Administrator administrator) {
    this.administrator = administrator;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays
        .asList("ROLE_ADMINISTRATOR")
        .stream()
        .map(roleName -> new SimpleGrantedAuthority(roleName.toString()))
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return administrator.getPassword();
  }

  @Override
  public String getUsername() {
    return administrator.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public Administrator getAdministrator() {
    return administrator;
  }

}
