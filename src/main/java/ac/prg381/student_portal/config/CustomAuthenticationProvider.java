package ac.prg381.student_portal.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ac.prg381.student_portal.entities.Administrator;
import ac.prg381.student_portal.entities.Student;
import ac.prg381.student_portal.repositories.AdministratorRepository;
import ac.prg381.student_portal.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final AdministratorRepository administratorRepository;

  private final StudentRepository studentRepository;

  public CustomAuthenticationProvider(
      AdministratorRepository administratorRepository,
      StudentRepository studentRepository) {
    this.administratorRepository = administratorRepository;
    this.studentRepository = studentRepository;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();

    Optional<Administrator> administrator = administratorRepository.findByEmail(email);
    if (administrator.isPresent() && password.equals(administrator.get().getPassword()))
      return new UsernamePasswordAuthenticationToken(email, password,
          List.of("ROLE_ADMINISTRATOR", "ROLE_ADMINISTRATOR_%d")
              .stream()
              .map(role -> String.format(role, administrator.get().getId()))
              .map(role -> new SimpleGrantedAuthority(role))
              .collect(Collectors.toList()));

    Optional<Student> student = studentRepository.findByEmail(email);
    if (student.isPresent() && password.equals(student.get().getPassword()))
      return new UsernamePasswordAuthenticationToken(email, password,
          List.of("ROLE_STUDENT", "ROLE_STUDENT_%d")
              .stream()
              .map(role -> String.format(role, student.get().getId()))
              .map(role -> new SimpleGrantedAuthority(role))
              .collect(Collectors.toList()));

    throw new UsernameNotFoundException("Invalid credentials");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

}
