package com.example.smart_banking.controller;

import com.example.smart_banking.model.Customer;
import com.example.smart_banking.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//Get all customers
@GetMapping
    public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
    }

// Get customer by ID
@GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    return customerRepository.findById(id)
                .map(customer -> ResponseEntity.ok(customer))
                .orElse(ResponseEntity.notFound().build());
    }

// Create new customer (with validation)
@PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
    return ResponseEntity.ok(customerRepository.save(customer));
    }

// Update existing customer
@PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer updatedCustomer) {
    return customerRepository.findById(id)
                .map(existingCustomer -> {
                  existingCustomer.setName(updatedCustomer.getName());
                  existingCustomer.setEmail(updatedCustomer.getEmail());
                  existingCustomer.setPhone(updatedCustomer.getPhone());
                  return ResponseEntity.ok(customerRepository.save(existingCustomer));
                })
                .orElse(ResponseEntity.notFound().build());
    }

// Delete customer
@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    if (!customerRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
        }
  customerRepository.deleteById(id);
    return ResponseEntity.noContent().build();
    }
}
