package ac.prg381.student_portal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.prg381.student_portal.entities.Administrator;
import ac.prg381.student_portal.repositories.AdministratorRepository;

@Service
public class AdministratorService {

  private final AdministratorRepository administratorRepository;

  @Autowired
  public AdministratorService(AdministratorRepository administratorRepository) {
    this.administratorRepository = administratorRepository;
  }

  // Create

  public Administrator addOrSetAdministrator(Administrator administrator) {
    return administratorRepository.save(administrator);
  }

  public Administrator addAdministrator(Administrator administrator) {
    Administrator existingAdministrator = administratorRepository.findById(administrator.getId()).orElse(null);
    return existingAdministrator == null ? addOrSetAdministrator(administrator) : null;
  }

  // Read

  public List<Administrator> getAllAdministrators() {
    return administratorRepository.findAll();
  }

  public Administrator getAdministratorById(Long id) {
    return administratorRepository.findById(id).orElse(null);
  }

  // Update

  public Administrator setAdministrator(Administrator administrator) {
    Administrator existingAdministrator = administratorRepository.findById(administrator.getId()).orElse(null);
    return existingAdministrator != null ? addOrSetAdministrator(administrator) : null;
  }

  // Delete

  public void removeAdministratorById(Long id) {
    administratorRepository.deleteById(id);
  }

  public void removeAdministrator(Administrator administrator) {
    removeAdministratorById(administrator.getId());
  }

}
