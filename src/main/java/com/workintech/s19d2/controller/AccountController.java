package com.workintech.s19d2.controller;


import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.repository.AccountRepository;
import com.workintech.s19d2.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;


    @PostMapping
    public Account save(Account account){
        return accountService.save(account);
    }

    @GetMapping
    public List<Account> findAll(){
       return accountService.findAll();
    }
}
