package ac.prg381.student_portal.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ac.prg381.student_portal.entities.Student;

public class StudentUserDetails implements UserDetails {
  private final Student student;

  public StudentUserDetails(Student student) {
    this.student = student;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays
        .asList("ROLE_STUDENT")
        .stream()
        .map(roleName -> new SimpleGrantedAuthority(roleName.toString()))
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return student.getPassword();
  }

  @Override
  public String getUsername() {
    return student.getEmail();
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

  public Student getStudent() {
    return student;
  }

}
