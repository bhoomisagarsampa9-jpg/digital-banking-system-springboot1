package com.example.smart_banking.model;

import jakarta.persistence.*;
        import jakarta.validation.constraints.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    @NotBlank(message = "Account type is required")
    private String type;

    @Min(value = 0, message = "Balance cannot be negative")
    private double balance;

    // Relationship: Each account belongs to one customer
  @ManyToOne
  @JoinColumn(name = "customer_id")
    private Customer customer;

    // Getters and Setters
  public Long getId()
  {
       return id;
  }
  public void setId(Long id)
  {
    this.id = id;
  }

  public String getAccountNumber() { return accountNumber; }

  public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

  public String getType() { return type; }

  public void setType(String type) { this.type = type; }

  public double getBalance() { return balance; }

  public void setBalance(double balance) { this.balance = balance; }

  public Customer getCustomer() { return customer; }

  public void setCustomer(Customer customer) { this.customer = customer; }
}
