package ac.prg381.student_portal.controllers.v1;

import java.security.KeyException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ac.prg381.student_portal.entities.Administrator;
import ac.prg381.student_portal.services.AdministratorService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdministratorController {

  private final AdministratorService administratorService;

  public AdministratorController(AdministratorService administratorService) {
    this.administratorService = administratorService;
  }

  // ============
  // == Create ==
  // ============

  @PostMapping("/add")
  public ResponseEntity<Administrator> postNew(@RequestBody Administrator administrator) throws KeyException {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(administratorService.addAdministrator(administrator));
  }

  // ==========
  // == Read ==
  // ==========

  @GetMapping("/{get}")
  public ResponseEntity<List<Administrator>> getAll(@RequestParam String param) {
    return ResponseEntity
        .ok(administratorService.getAllAdministrators());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Administrator> getById(@PathVariable Long id) {
    return ResponseEntity
        .status(HttpStatus.FOUND)
        .body(administratorService.getAdministratorById(id).get());
  }

  // ============
  // == Update ==
  // ============

  @PutMapping("/set/{id}")
  public ResponseEntity<Administrator> putById(@PathVariable Long id, @RequestBody Administrator administrator) {
    administrator.setId(id);
    return ResponseEntity
        .ok(administratorService.setAdministrator(administrator));
  }

  // ============
  // == Delete ==
  // ============

  @DeleteMapping("/del/{id}")
  public ResponseEntity<Administrator> deleteById(@PathVariable Long id) {
    Administrator deletedAdministrator = administratorService.getAdministratorById(id).orElseThrow();
    administratorService.removeAdministratorById(id);
    return ResponseEntity.ok(deletedAdministrator);
  }
}
