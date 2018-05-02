package com.qa.account.demo.accountproject.controller;

import com.qa.account.demo.accountproject.dao.Account;
import com.qa.account.demo.accountproject.dao.AccountNotFoundException;
import com.qa.account.demo.accountproject.dao.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Created by rajeevsachdeva on 02/05/2018.
 */
@RestController
@RequestMapping(value = "rest")
public class AccountResource {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> retrieveAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account retrieveAccount(@PathVariable Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        if(!account.isPresent()) {
            throw new AccountNotFoundException(String.format("Id : %s", id));
        }
        return account.get();
    }

    @PostMapping("/accounts")
    public ResponseEntity<Object> createAccount(@Valid @RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);

        URI locagtion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAccount.getId())
                .toUri();

        return ResponseEntity.created(locagtion).build();
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Integer id) {
        accountRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}