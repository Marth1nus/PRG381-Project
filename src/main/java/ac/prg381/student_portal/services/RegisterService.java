package ac.prg381.student_portal.services;

import java.security.KeyException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ac.prg381.student_portal.entities.Register;
import ac.prg381.student_portal.repositories.RegisterRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RegisterService {

  private final RegisterRepository registerRepository;

  public RegisterService(RegisterRepository registerRepository) {
    this.registerRepository = registerRepository;
  }

  // ============
  // == Create ==
  // ============

  public Register addRegister(Register register) throws KeyException {
    if (register.getId() != null)
      throw new KeyException("register.id must be null to be added");
    return registerRepository.save(register);
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

  public Register setRegister(Register register) {
    registerRepository
        .findById(register.getId())
        .orElseThrow(
            () -> new EntityNotFoundException(String.format("Register with id '%d' not found", register.getId())));
    return registerRepository.save(register);
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
