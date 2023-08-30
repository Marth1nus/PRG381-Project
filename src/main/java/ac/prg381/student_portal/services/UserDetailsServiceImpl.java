package ac.prg381.student_portal.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ac.prg381.student_portal.entities.Administrator;
import ac.prg381.student_portal.entities.Student;
import ac.prg381.student_portal.repositories.AdministratorRepository;
import ac.prg381.student_portal.repositories.StudentRepository;
import ac.prg381.student_portal.security.AdministratorUserDetails;
import ac.prg381.student_portal.security.StudentUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final AdministratorRepository administratorRepository;
  private final StudentRepository studentRepository;

  public UserDetailsServiceImpl(AdministratorRepository administratorRepository, StudentRepository studentRepository) {
    this.administratorRepository = administratorRepository;
    this.studentRepository = studentRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Administrator> administrator = administratorRepository
        .findByEmail(username);
    if (administrator.isPresent())
      return new AdministratorUserDetails(administrator.get());

    Optional<Student> student = studentRepository
        .findByEmail(username);
    if (student.isPresent())
      return new StudentUserDetails(student.get());

    throw new UsernameNotFoundException(username + " not found");
  }

}
