package ac.prg381.student_portal.controllers.v1;

import java.security.KeyException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ac.prg381.student_portal.entities.Register;
import ac.prg381.student_portal.services.RegisterService;

@RestController
@RequestMapping("/api/v1/register")
@PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMINISTRATOR')")
public class RegisterController {

  private final RegisterService registerService;

  public RegisterController(RegisterService registerService) {
    this.registerService = registerService;
  }

  // ============
  // == Create ==
  // ============

  @PostMapping("/add")
  @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
  public ResponseEntity<Register> postNew(@RequestBody Register register) throws KeyException {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(registerService.addRegister(register));
  }

  // ==========
  // == Read ==
  // ==========

  @GetMapping("/get")
  public ResponseEntity<List<Register>> getAll() {
    return ResponseEntity
        .ok(registerService.getAllRegisters());
  }

  @GetMapping({ "/get/{id}", "/{id}" })
  public ResponseEntity<Register> getById(@PathVariable Long id, Authentication authentication) {
    Register register = registerService.getRegisterById(id).orElseThrow();

    if (authentication.getAuthorities().stream()
        .anyMatch(authority -> authority.getAuthority().equals("ROLE_STUDENT"))) {
      String expectedRole = "ROLE_STUDENT_" + register.getStudent().getId();

      if (!authentication.getAuthorities().stream()
          .anyMatch(authority -> authority.getAuthority().equals(expectedRole)))
        throw new BadCredentialsException("Student is only allowed to access their own registrations");
    }

    return ResponseEntity
        .status(HttpStatus.FOUND)
        .body(register);
  }

  // ============
  // == Update ==
  // ============

  @PutMapping("/set/{id}")
  @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
  public ResponseEntity<Register> putById(@PathVariable Long id, @RequestBody Register register) {
    register.setId(id);
    return ResponseEntity
        .ok(registerService.setRegister(register));
  }

  // ============
  // == Delete ==
  // ============

  @DeleteMapping("/del/{id}")
  @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
  public ResponseEntity<Register> deleteById(@PathVariable Long id) {
    Register deletedRegister = registerService.getRegisterById(id).orElseThrow();
    registerService.removeRegisterById(id);
    return ResponseEntity.ok(deletedRegister);
  }

}
