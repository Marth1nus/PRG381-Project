package ac.prg381.student_portal.controllers.v1;

import java.security.KeyException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ac.prg381.student_portal.entities.Student;
import ac.prg381.student_portal.services.StudentService;

@RestController
@RequestMapping("/api/v1/student")
@PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMINISTRATOR')")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  // ============
  // == Create ==
  // ============

  @PostMapping("/add")
  @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
  public ResponseEntity<Student> postNew(@RequestBody Student student) throws KeyException {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(prepareStudent(studentService.addStudent(student)));
  }

  // ==========
  // == Read ==
  // ==========

  @GetMapping("/get")
  @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
  public ResponseEntity<List<Student>> getAll() {
    return ResponseEntity
        .ok(studentService.getAllStudents().stream()
            .map(student -> prepareStudent(student))
            .collect(Collectors.toList()));
  }

  @GetMapping({ "/get/{id}", "/{id}" })
  @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_STUDENT_' + #id)")
  public ResponseEntity<Student> getById(@PathVariable Long id) {
    return ResponseEntity
        .status(HttpStatus.FOUND)
        .body(prepareStudent(studentService.getStudentById(id).get()));
  }

  // ============
  // == Update ==
  // ============

  @PutMapping("/set/{id}")
  @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_STUDENT_' + #id)")
  public ResponseEntity<Student> putById(@PathVariable Long id, @RequestBody Student student) {
    student.setId(id);
    return ResponseEntity
        .ok(prepareStudent(studentService.setStudent(student)));
  }

  // ============
  // == Delete ==
  // ============

  @DeleteMapping("/del/{id}")
  @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
  public ResponseEntity<Student> deleteById(@PathVariable Long id) {
    Student deletedStudent = studentService.getStudentById(id).orElseThrow();
    studentService.removeStudentById(id);
    return ResponseEntity.ok(prepareStudent(deletedStudent));
  }

  // ==========
  // == Util ==
  // ==========

  public static Student prepareStudent(Student student) {
    // limit depth
    student.getRegistrations().forEach(register -> register.setStudent(null));
    return student;
  }
}
