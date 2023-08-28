package ac.prg381.student_portal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.prg381.student_portal.repositories.RegisterRepository;

@Service
public class RegisterService {

  private final RegisterRepository registerRepository;

  @Autowired
  public RegisterService(RegisterRepository registerRepository) {
    this.registerRepository = registerRepository;
  }

  // CRUD
}
