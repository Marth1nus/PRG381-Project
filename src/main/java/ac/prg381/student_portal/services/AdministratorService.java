package ac.prg381.student_portal.services;

import java.util.List;

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

  public Administrator addOrSetAdministrator(Administrator administrator) {
    return administratorRepository.save(administrator);
  }

  public Administrator addAdministrator(Administrator administrator) {
    Administrator existingAdministrator = administratorRepository.findById(administrator.getId()).orElse(null);
    return existingAdministrator == null ? addOrSetAdministrator(administrator) : null;
  }

  // ==========
  // == Read ==
  // ==========

  public List<Administrator> getAllAdministrators() {
    return administratorRepository.findAll();
  }

  public Administrator getAdministratorById(Long id) {
    return administratorRepository.findById(id).orElse(null);
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

  public Administrator setAdministrator(Administrator administrator) {
    Administrator existingAdministrator = administratorRepository.findById(administrator.getId()).orElse(null);
    return existingAdministrator != null ? addOrSetAdministrator(administrator) : null;
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
