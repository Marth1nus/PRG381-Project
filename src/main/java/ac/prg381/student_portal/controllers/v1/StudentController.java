package ac.prg381.student_portal.controllers.v1;

import java.security.KeyException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ac.prg381.student_portal.entities.Student;
import ac.prg381.student_portal.services.StudentService;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  // ============
  // == Create ==
  // ============

  @PostMapping("/add")
  public ResponseEntity<Student> postNew(@RequestBody Student entity) throws KeyException {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(studentService.addStudent(entity));
  }

  // ==========
  // == Read ==
  // ==========

  @GetMapping("/{get}")
  public ResponseEntity<List<Student>> getAll(@RequestParam String param) {
    return ResponseEntity
        .ok(studentService.getAllStudents());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Student> getById(@PathVariable Long id) {
    return ResponseEntity
        .status(HttpStatus.FOUND)
        .body(studentService.getStudentById(id).get());
  }

  // ============
  // == Update ==
  // ============

  @PutMapping("/set/{id}")
  public ResponseEntity<Student> putById(@PathVariable Long id, @RequestBody Student entity) {
    entity.setId(id);
    return ResponseEntity
        .ok(studentService.setStudent(entity));
  }

  // ============
  // == Delete ==
  // ============

  @DeleteMapping("/del/{id}")
  public ResponseEntity<Student> deleteById(@PathVariable Long id) {
    Student deletedStudent = studentService.getStudentById(id).orElseThrow();
    studentService.removeStudentById(id);
    return ResponseEntity.ok(deletedStudent);
  }

}
