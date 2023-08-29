package ac.prg381.student_portal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.prg381.student_portal.entities.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {
  // =============
  // == find by ==
  // =============

  List<Register> findByCourseName(String courseName);

  // ===========================
  // == find by like (search) ==
  // ===========================

  List<Register> findByCourseNameLike(String courseName);
}
