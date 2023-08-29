package ac.prg381.student_portal.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.prg381.student_portal.entities.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

  // find by

  Set<Administrator> findByName(String name);

  Set<Administrator> findByEmail(String email);

  // find by like (search)

  Set<Administrator> findByNameLike(String name);

  Set<Administrator> findByEmailLike(String email);
}
