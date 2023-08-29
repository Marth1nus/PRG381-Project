package ac.prg381.student_portal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ac.prg381.student_portal.entities.Administrator;
import ac.prg381.student_portal.repositories.AdministratorRepository;

@Service
public class AdministratorService {

  private final AdministratorRepository administratorRepository;

  public AdministratorService(AdministratorRepository administratorRepository) {
    this.administratorRepository = administratorRepository;
  }

  // ============
  // == Create ==
  // ============

  public Optional<Administrator> addAdministrator(Administrator administrator) {
    return administratorRepository.findById(administrator.getId()).isPresent()
        ? Optional.empty()
        : Optional.ofNullable(administratorRepository.save(administrator));
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

  public List<Administrator> getAdministratorsByEmail(String email) {
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

  public Optional<Administrator> setAdministrator(Administrator administrator) {
    return administratorRepository.findById(administrator.getId()).isPresent()
        ? Optional.ofNullable(administratorRepository.save(administrator))
        : Optional.empty();
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
