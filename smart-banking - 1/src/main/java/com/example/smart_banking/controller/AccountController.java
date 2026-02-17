package com.example.smart_banking.controller;

import com.example.smart_banking.model.Account;
import com.example.smart_banking.model.Customer;
import com.example.smart_banking.repository.AccountRepository;
import com.example.smart_banking.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/** rest api operation
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  private final AccountRepository accountRepository;
  private final CustomerRepository customerRepository;

  /*
    *
    * @param accountRepository * @param customerRepository
    */
  public AccountController(AccountRepository accountRepository, CustomerRepository customerRepository) {
    this.accountRepository = accountRepository;
    this.customerRepository = customerRepository;
    }
//Get all accounts
@GetMapping
  public List<Account> getAllAccounts() {
    return accountRepository.findAll();
    }

//Get account by ID

@GetMapping("/{id}")
  public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
    return accountRepository.findById(id)
                .map(account -> ResponseEntity.ok(account))
                .orElse(ResponseEntity.notFound().build());
    }

//Create account for customer
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<?> createAccount(@PathVariable Long customerId, @Valid @RequestBody Account account) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

    if (customerOptional.isPresent()) {
      return ResponseEntity.badRequest().body("Customer not found");
        }

    account.setCustomer(customerOptional.get());
    return ResponseEntity.ok(accountRepository.save(account));
    }

  //Update account
  @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @Valid @RequestBody Account updatedAccount) {
        return accountRepository.findById(id)
                .map(existingAccount -> {
                  existingAccount.setAccountNumber(updatedAccount.getAccountNumber());
                  existingAccount.setType(updatedAccount.getType());
                  existingAccount.setBalance(updatedAccount.getBalance());
                  return ResponseEntity.ok(accountRepository.save(existingAccount));
                })
                .orElse(ResponseEntity.notFound().build());
    }

  /* Delete account

  */
  @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
    if (!accountRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
        }
      accountRepository.deleteById(id);
    return ResponseEntity.noContent().build();
    }
}
