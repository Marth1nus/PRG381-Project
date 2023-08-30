package ac.prg381.student_portal.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "administrators", indexes = @Index(columnList = "email", unique = true))
public class Administrator {

  // ================
  // == Properties ==
  // ================

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  // ==================
  // == Constructors ==
  // ==================

  public Administrator() {
  }

  public Administrator(String name, String email, String password) {
    this.name = name;
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

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  // =============
  // == Getters ==
  // =============

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
