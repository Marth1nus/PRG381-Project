package ac.prg381.student_portal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.prg381.student_portal.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

  // =============
  // == find by ==
  // =============

  List<Student> findByName(String name);

  List<Student> findByAddress(String address);

  Optional<Student> findByEmail(String email);

  // ===========================
  // == find by like (search) ==
  // ===========================

  List<Student> findByNameLike(String name);

  List<Student> findByAddressLike(String address);

  List<Student> findByEmailLike(String email);
}
