package ac.prg381.student_portal.controllers.v1;

import java.security.KeyException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ac.prg381.student_portal.entities.Register;
import ac.prg381.student_portal.services.RegisterService;

@RestController
@RequestMapping("/api/v1/register")
public class RegisterController {

  private final RegisterService registerService;

  public RegisterController(RegisterService registerService) {
    this.registerService = registerService;
  }

  // ============
  // == Create ==
  // ============

  @PostMapping("/add")
  public ResponseEntity<Register> postNew(@RequestBody Register entity) throws KeyException {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(registerService.addRegister(entity));
  }

  // ==========
  // == Read ==
  // ==========

  @GetMapping("/{get}")
  public ResponseEntity<List<Register>> getAll(@RequestParam String param) {
    return ResponseEntity
        .ok(registerService.getAllRegisters());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Register> getById(@PathVariable Long id) {
    return ResponseEntity
        .status(HttpStatus.FOUND)
        .body(registerService.getRegisterById(id).get());
  }

  // ============
  // == Update ==
  // ============

  @PutMapping("/set/{id}")
  public ResponseEntity<Register> putById(@PathVariable Long id, @RequestBody Register entity) {
    entity.setId(id);
    return ResponseEntity
        .ok(registerService.setRegister(entity));
  }

  // ============
  // == Delete ==
  // ============

  @DeleteMapping("/del/{id}")
  public ResponseEntity<Register> deleteById(@PathVariable Long id) {
    Register deletedRegister = registerService.getRegisterById(id).orElseThrow();
    registerService.removeRegisterById(id);
    return ResponseEntity.ok(deletedRegister);
  }
}
