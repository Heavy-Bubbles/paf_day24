package sg.edu.nus.iss.paf_day24.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.paf_day24.exception.BankAccountNotFoundException;
import sg.edu.nus.iss.paf_day24.model.BankAccount;

@Repository
public class BankAccountRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String GET_ACCOUNT_SQL = "select * from bank_account where id = ?";
    private final String WITHDRWAW_SQL = "update bank_account set balance = balance - ? where id = ?";
    private final String DEPOSIT_SQL = "update bank_account set balance = balance + ? where id = ?";
    private final String CREATE_ACCOUNT_SQL = "insert into bank_account (full_name, is_blocked, is_active, account_type, balance) values (?, ?, ?, ?, ?)";
    // private final Stiring CREATE_ACCOUNT_SQL = "insert into bank_account values (?, ?, ?, ?, ?, ?)";

    public BankAccount getAccountById(Integer bankAccountId){
        List<BankAccount> bankAccounts = jdbcTemplate.query(GET_ACCOUNT_SQL,
            BeanPropertyRowMapper.newInstance(BankAccount.class), bankAccountId);

        if (bankAccounts.isEmpty()) {
            throw new BankAccountNotFoundException("Account does not exist.");
        }

        BankAccount bankAccount = bankAccounts.get(0);

        return bankAccount;
    }

    public Boolean withdrawAmount(Integer withdrawAccountId, Float withdrawAmount){
        Integer result = jdbcTemplate.update(WITHDRWAW_SQL,
            withdrawAmount, withdrawAccountId);
        
        return result > 0 ? true : false;
        
    }

    public Boolean depositAmount (Integer depositAccountId, Float depositAmount){
        Integer result = jdbcTemplate.update(DEPOSIT_SQL,
            depositAmount, depositAccountId);

        return result > 0 ? true : false;

    }

    public Boolean createAccount (BankAccount bankAccount){
        Integer result = jdbcTemplate.update(CREATE_ACCOUNT_SQL,
            bankAccount.getFullName(), bankAccount.getIsBlocked(),
            bankAccount.getIsActive(), bankAccount.getAccountType(),
            bankAccount.getBalance());

        return result > 0 ? true : false;
    }

    @Transactional
    public Boolean transferMoney(Integer withdrawAccountId, Integer depositAccountId,
    Float transferAmount){
        return false;
    }

}
