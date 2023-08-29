package ac.prg381.student_portal.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.prg381.student_portal.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  // find by

  Set<Student> findByName(String name);

  Set<Student> findByAddress(String address);

  Set<Student> findByEmail(String email);

  // find by like (search)

  Set<Student> findByNameLike(String name);

  Set<Student> findByAddressLike(String address);

  Set<Student> findByEmailLike(String email);
}
