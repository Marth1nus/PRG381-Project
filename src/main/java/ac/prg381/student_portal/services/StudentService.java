package ac.prg381.student_portal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ac.prg381.student_portal.entities.Student;
import ac.prg381.student_portal.repositories.StudentRepository;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  // ============
  // == Create ==
  // ============

  public Optional<Student> addStudent(Student student) {
    return studentRepository.findById(student.getId()).isPresent()
        ? Optional.empty()
        : Optional.ofNullable(studentRepository.save(student));
  }

  // ==========
  // == Read ==
  // ==========

  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  public Optional<Student> getStudentById(Long id) {
    return studentRepository.findById(id);
  }

  public List<Student> getStudentsByName(String name) {
    return studentRepository.findByName(name);
  }

  public List<Student> getStudentsByAddress(String address) {
    return studentRepository.findByAddress(address);
  }

  public List<Student> getStudentsByEmail(String email) {
    return studentRepository.findByEmail(email);
  }

  public List<Student> getStudentsByNameLike(String name) {
    return studentRepository.findByNameLike(name);
  }

  public List<Student> getStudentsByAddressLike(String address) {
    return studentRepository.findByAddressLike(address);
  }

  public List<Student> getStudentsByEmailLike(String email) {
    return studentRepository.findByEmailLike(email);
  }

  // ============
  // == Update ==
  // ============

  public Optional<Student> setStudent(Student student) {
    return studentRepository.findById(student.getId()).isPresent()
        ? Optional.ofNullable(studentRepository.save(student))
        : Optional.empty();
  }

  // ============
  // == Delete ==
  // ============

  public void removeStudentById(Long id) {
    studentRepository.deleteById(id);
  }

  public void removeStudent(Student student) {
    removeStudentById(student.getId());
  }

}
