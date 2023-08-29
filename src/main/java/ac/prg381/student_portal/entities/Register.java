package ac.prg381.student_portal.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "registers")
public class Register {

  // Properties

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id", nullable = false)
  private Student student;

  @Column(nullable = false)
  private String courseName;

  // Constructors

  public Register() {
  }

  public Register(Student student, String courseName) {
    this.student = student;
    this.courseName = courseName;
  }

  // Getters and Setters

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Student getStudent() {
    return this.student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public String getCourseName() {
    return this.courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  // Other
}
