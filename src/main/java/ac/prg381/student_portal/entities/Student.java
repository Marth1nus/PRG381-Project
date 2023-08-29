package ac.prg381.student_portal.entities;

import jakarta.persistence.*;
import java.util.Set;

import org.springframework.stereotype.Indexed;

/* TODO: Look into @Indexed and hibernate search. */

@Entity
@Table(name = "students")
public class Student {

  // Properties

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
  private Set<Register> registers;

  // Constructors

  public Student() {
  }

  public Student(String name, String address, String email, String password) {
    this.name = name;
    this.address = address;
    this.email = email;
    this.password = password;
  }

  // Getters and Setters

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Register> getRegistrations() {
    return this.registers;
  }

  public void setRegistrations(Set<Register> registers) {
    this.registers = registers;
  }

}
