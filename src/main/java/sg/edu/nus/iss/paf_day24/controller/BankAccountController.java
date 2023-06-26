package sg.edu.nus.iss.paf_day24.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf_day24.model.BankAccount;
import sg.edu.nus.iss.paf_day24.service.BankAccountService;

@RestController
@RequestMapping("/api/bankaccounts")
public class BankAccountController {
    @Autowired
    BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<Boolean> createAccount (@RequestBody BankAccount bankAccount){
        boolean isCreated = bankAccountService.createAccount(bankAccount);

        if (isCreated){
            return ResponseEntity.ok().body(isCreated);
        } else {
            // exception/custom exception handling
            return ResponseEntity.internalServerError().body(isCreated);
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable("accountId") Integer id){
        BankAccount bankAccount = bankAccountService.retrieveAccountById(id);

        return new ResponseEntity<BankAccount>(bankAccount, HttpStatus.OK);
    }
}
