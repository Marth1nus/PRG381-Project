package ac.prg381.student_portal.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "students", indexes = @Index(columnList = "email"))
public class Student {

  // ================
  // == Properties ==
  // ================

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Register> registers;

  // ==================
  // == Constructors ==
  // ==================

  public Student() {
  }

  public Student(String name, String address, String email, String password) {
    this.name = name;
    this.address = address;
    this.email = email;
    this.password = password;
  }

  // =============
  // == Getters ==
  // =============

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getAddress() {
    return this.address;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public List<Register> getRegistrations() {
    return this.registers;
  }

  // =============
  // == Setters ==
  // =============

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRegistrations(List<Register> registers) {
    this.registers = registers;
  }

}
