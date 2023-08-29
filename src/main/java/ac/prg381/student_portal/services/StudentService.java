package ac.prg381.student_portal.services;

import java.security.KeyException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ac.prg381.student_portal.entities.Student;
import ac.prg381.student_portal.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  // ============
  // == Create ==
  // ============

  public Student addStudent(Student student) throws KeyException {
    if (student.getId() != null)
      throw new KeyException("student.id must be null to be added");
    return studentRepository.save(student);
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

  public Student setStudent(Student student) {
    studentRepository
        .findById(student.getId())
        .orElseThrow(
            () -> new EntityNotFoundException(String.format("Student with id '%d' not found", student.getId())));
    return studentRepository.save(student);
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
