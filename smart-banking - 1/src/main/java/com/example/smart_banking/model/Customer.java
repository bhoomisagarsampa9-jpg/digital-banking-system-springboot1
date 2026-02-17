package com.example.smart_banking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Customer
    {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @NotBlank(message = "Name is required")
    private String name;

  @NotBlank(message = "Email is required")
  @Email(message = "Invalid email format")
    private String email;

  @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    private String phone;

  // Getters and Setters
  public Long getId()
  {
    return id;
  }
  public void setId(Long id)
  {
      this.id = id;
  }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public String getEmail() { return email; }

  public void setEmail(String email) { this.email = email; }

  public String getPhone() { return phone; }

  public void setPhone(String phone) { this.phone = phone; }
}
