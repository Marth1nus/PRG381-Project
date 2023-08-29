package ac.prg381.student_portal.services;

import java.util.List;

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

  public Student addOrSetStudent(Student student) {
    return studentRepository.save(student);
  }

  public Student addStudent(Student student) {
    Student existingStudent = studentRepository.findById(student.getId()).orElse(null);
    return existingStudent == null ? addOrSetStudent(student) : null;
  }

  // ==========
  // == Read ==
  // ==========

  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  public Student getStudentById(Long id) {
    return studentRepository.findById(id).orElse(null);
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
    Student existingStudent = studentRepository.findById(student.getId()).orElse(null);
    return existingStudent != null ? addOrSetStudent(student) : null;
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
