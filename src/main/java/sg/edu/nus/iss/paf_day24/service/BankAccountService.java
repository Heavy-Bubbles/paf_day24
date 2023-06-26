package sg.edu.nus.iss.paf_day24.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day24.exception.BankAccountNotFoundException;
import sg.edu.nus.iss.paf_day24.model.BankAccount;
import sg.edu.nus.iss.paf_day24.repository.BankAccountRepo;

@Service
public class BankAccountService {
    @Autowired
    BankAccountRepo bankAccountRepo;

    public BankAccount retrieveAccountById(Integer accountId){
       BankAccount bankAccount = bankAccountRepo.getAccountById(accountId);

       // System.out.println("BankAccountService > retrieveAccountById > " + bankAccount.toString());
 
       if (bankAccount == null){
            throw new BankAccountNotFoundException("Account details cannot be retrieved");
       }
       return bankAccount;
    }

    public Boolean createAccount(BankAccount bankAccount){
        return bankAccountRepo.createAccount(bankAccount);
    }
}
