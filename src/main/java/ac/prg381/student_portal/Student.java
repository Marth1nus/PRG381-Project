package ac.prg381.student_portal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String address;
  private String email;
  private String password;

  @OneToMany(mappedBy = "student")
  private List<Register> registrations;

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

  public List<Register> getRegistrations() {
    return this.registrations;
  }

  public void setRegistrations(List<Register> registrations) {
    this.registrations = registrations;
  }

}
