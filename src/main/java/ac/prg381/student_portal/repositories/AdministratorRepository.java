package ac.prg381.student_portal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ac.prg381.student_portal.entities.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

  // =============
  // == find by ==
  // =============

  List<Administrator> findByName(String name);

  Optional<Administrator> findByEmail(String email);

  // ===========================
  // == find by like (search) ==
  // ===========================

  List<Administrator> findByNameLike(String name);

  List<Administrator> findByEmailLike(String email);
}
