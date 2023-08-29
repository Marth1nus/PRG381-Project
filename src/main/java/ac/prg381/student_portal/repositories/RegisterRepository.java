package ac.prg381.student_portal.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.prg381.student_portal.entities.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
  // find by

  Set<Register> findByCourseName(String courseName);

  // find by like (search)

  Set<Register> findByCourseNameLike(String courseName);
}
