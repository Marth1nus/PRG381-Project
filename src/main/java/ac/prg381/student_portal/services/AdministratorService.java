package ac.prg381.student_portal.services;

import java.security.KeyException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ac.prg381.student_portal.entities.Administrator;
import ac.prg381.student_portal.repositories.AdministratorRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AdministratorService {

  private final AdministratorRepository administratorRepository;

  public AdministratorService(AdministratorRepository administratorRepository) {
    this.administratorRepository = administratorRepository;
  }

  // ============
  // == Create ==
  // ============

  public Administrator addAdministrator(Administrator administrator) throws KeyException {
    if (administrator.getId() != null)
      throw new KeyException("administrator.id must be null to be added");
    return administratorRepository.save(administrator);
  }

  // ==========
  // == Read ==
  // ==========

  public List<Administrator> getAllAdministrators() {
    return administratorRepository.findAll();
  }

  public Optional<Administrator> getAdministratorById(Long id) {
    return administratorRepository.findById(id);
  }

  public List<Administrator> getAdministratorsByName(String name) {
    return administratorRepository.findByName(name);
  }

  public Optional<Administrator> getAdministratorsByEmail(String email) {
    return administratorRepository.findByEmail(email);
  }

  public List<Administrator> getAdministratorsByNameLike(String name) {
    return administratorRepository.findByNameLike(name);
  }

  public List<Administrator> getAdministratorsByEmailLike(String email) {
    return administratorRepository.findByEmailLike(email);
  }

  // ============
  // == Update ==
  // ============

  public Administrator setAdministrator(Administrator administrator) {
    administratorRepository
        .findById(administrator.getId())
        .orElseThrow(
            () -> new EntityNotFoundException(
                String.format("Administrator with id '%d' not found", administrator.getId())));
    return administratorRepository.save(administrator);
  }

  // ============
  // == Delete ==
  // ============

  public void removeAdministratorById(Long id) {
    administratorRepository.deleteById(id);
  }

  public void removeAdministrator(Administrator administrator) {
    removeAdministratorById(administrator.getId());
  }

}
