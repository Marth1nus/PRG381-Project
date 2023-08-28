package ac.prg381.student_portal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.prg381.student_portal.repositories.AdministratorRepository;

@Service
public class AdministratorService {

  private final AdministratorRepository administratorRepository;

  @Autowired
  public AdministratorService(AdministratorRepository administratorRepository) {
    this.administratorRepository = administratorRepository;
  }

  // CRUD

}
