package ac.prg381.student_portal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ac.prg381.student_portal.entities.Register;
import ac.prg381.student_portal.repositories.RegisterRepository;

@Service
public class RegisterService {

  private final RegisterRepository registerRepository;

  public RegisterService(RegisterRepository registerRepository) {
    this.registerRepository = registerRepository;
  }

  // ============
  // == Create ==
  // ============

  public Optional<Register> addRegister(Register register) {
    return registerRepository.findById(register.getId()).isPresent()
        ? Optional.empty()
        : Optional.ofNullable(registerRepository.save(register));
  }

  // ==========
  // == Read ==
  // ==========

  public List<Register> getAllRegisters() {
    return registerRepository.findAll();
  }

  public Optional<Register> getRegisterById(Long id) {
    return registerRepository.findById(id);
  }

  public List<Register> getRegistersByCourseName(String courseName) {
    return registerRepository.findByCourseName(courseName);
  }

  public List<Register> getRegistersByCourseNameLike(String courseName) {
    return registerRepository.findByCourseNameLike(courseName);
  }

  // ============
  // == Update ==
  // ============

  public Optional<Register> setRegister(Register register) {
    return registerRepository.findById(register.getId()).isPresent()
        ? Optional.ofNullable(registerRepository.save(register))
        : Optional.empty();
  }

  // ============
  // == Delete ==
  // ============

  public void removeRegisterById(Long id) {
    registerRepository.deleteById(id);
  }

  public void removeRegister(Register register) {
    removeRegisterById(register.getId());
  }

}
