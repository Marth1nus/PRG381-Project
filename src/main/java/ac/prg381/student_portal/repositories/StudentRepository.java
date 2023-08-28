package ac.prg381.student_portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.prg381.student_portal.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
