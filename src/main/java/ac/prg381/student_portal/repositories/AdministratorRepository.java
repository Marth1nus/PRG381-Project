package ac.prg381.student_portal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.prg381.student_portal.entities.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

  // find by

  List<Administrator> findByName(String name);

  List<Administrator> findByEmail(String email);

  // find by like (search)

  List<Administrator> findByNameLike(String name);

  List<Administrator> findByEmailLike(String email);
}
