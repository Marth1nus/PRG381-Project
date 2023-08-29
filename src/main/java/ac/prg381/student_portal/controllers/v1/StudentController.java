package ac.prg381.student_portal.controllers.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.prg381.student_portal.entities.Student;
import ac.prg381.student_portal.services.StudentService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<?> postNew(@RequestBody Student entity) {
    Optional<Student> newStudent = studentService.addStudent(entity);
    return newStudent.isPresent()
        ? ResponseEntity.status(HttpStatus.CREATED)
            .body(newStudent.get())
        : ResponseEntity.status(HttpStatus.CONFLICT)
            .body(String.format("Student with id '%d' already exists", entity.getId()));
  }

  // ==========
  // == Read ==
  // ==========

  @GetMapping("/{get}")
  public ResponseEntity<List<Student>> getAll(@RequestParam String param) {
    return ResponseEntity.ok(studentService.getAllStudents());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    Optional<Student> gotStudent = studentService.getStudentById(id);
    return gotStudent.isPresent()
        ? ResponseEntity.status(HttpStatus.FOUND)
            .body(gotStudent.get())
        : ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(String.format("Student with id '%d' not found", id));
  }

  // ============
  // == Update ==
  // ============

  @PutMapping("/set/{id}")
  public ResponseEntity<?> putById(@PathVariable Long id, @RequestBody Student entity) {
    entity.setId(id);
    Optional<Student> gotStudent = studentService.setStudent(entity);
    return gotStudent.get()
    ? 
  }

  // ============
  // == Delete ==
  // ============

  @DeleteMapping("/del/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id, @RequestBody Student entity) {
    // TODO: process Delete request
  }

}
