package ac.prg381.student_portal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.prg381.student_portal.entities.Register;
import ac.prg381.student_portal.repositories.RegisterRepository;

@Service
public class RegisterService {

  private final RegisterRepository registerRepository;

  @Autowired
  public RegisterService(RegisterRepository registerRepository) {
    this.registerRepository = registerRepository;
  }

  // Create

  public Register addOrSetRegister(Register register) {
    return registerRepository.save(register);
  }

  public Register addRegister(Register register) {
    Register existingRegister = registerRepository.findById(register.getId()).orElse(null);
    return existingRegister == null ? addOrSetRegister(register) : null;
  }

  // Read

  public List<Register> getAllRegisters() {
    return registerRepository.findAll();
  }

  public Register getRegisterById(Long id) {
    return registerRepository.findById(id).orElse(null);
  }

  // Update

  public Register setRegister(Register register) {
    Register existingRegister = registerRepository.findById(register.getId()).orElse(null);
    return existingRegister != null ? addOrSetRegister(register) : null;
  }

  // Delete

  public void removeRegisterById(Long id) {
    registerRepository.deleteById(id);
  }

  public void removeRegister(Register register) {
    removeRegisterById(register.getId());
  }

}
