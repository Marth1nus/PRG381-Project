package ac.prg381.student_portal.controllers.v1;

import java.security.KeyException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import ac.prg381.student_portal.entities.Student;
import ac.prg381.student_portal.security.StudentUserDetails;
import ac.prg381.student_portal.services.StudentService;

@RestController
@RequestMapping("/api/v1/student")
// @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMINISTRATOR')")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  // ============
  // == Create ==
  // ============

  @PostMapping("/add")
  // @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
  public ResponseEntity<Student> postNew(@RequestBody Student student) throws KeyException {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(studentService.addStudent(student));
  }

  // ==========
  // == Read ==
  // ==========

  @GetMapping("/get")
  // @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
  public ResponseEntity<List<Student>> getAll() {
    return ResponseEntity
        .ok(studentService.getAllStudents());
  }

  @GetMapping({ "/get/{id}", "/{id}" })
  public ResponseEntity<Student> getById(@PathVariable Long id,
      @AuthenticationPrincipal StudentUserDetails studentUserDetails) {
    assertStudentAccessingOwnDate(id, studentUserDetails);
    return ResponseEntity
        .status(HttpStatus.FOUND)
        .body(studentService.getStudentById(id).get());
  }

  // ============
  // == Update ==
  // ============

  @PutMapping("/set/{id}")
  public ResponseEntity<Student> putById(@PathVariable Long id, @RequestBody Student student,
      @AuthenticationPrincipal StudentUserDetails studentUserDetails) {
    assertStudentAccessingOwnDate(id, studentUserDetails);
    student.setId(id);
    return ResponseEntity
        .ok(studentService.setStudent(student));
  }

  // ============
  // == Delete ==
  // ============

  @DeleteMapping("/del/{id}")
  // @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
  public ResponseEntity<Student> deleteById(@PathVariable Long id) {
    Student deletedStudent = studentService.getStudentById(id).orElseThrow();
    studentService.removeStudentById(id);
    return ResponseEntity.ok(deletedStudent);
  }

  // ====================
  // == Auth utilities ==
  // ====================

  private static void assertStudentAccessingOwnDate(Long id, StudentUserDetails studentUserDetails) {
    if (studentUserDetails == null ||
        studentUserDetails.getStudent().getId().equals(id))
      return;
    throw new AccessDeniedException("Students may only access their own data after login");
  }
}
