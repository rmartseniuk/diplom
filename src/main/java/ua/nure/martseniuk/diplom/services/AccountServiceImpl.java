package ua.nure.martseniuk.diplom.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ua.nure.martseniuk.diplom.domain.Account;
import ua.nure.martseniuk.diplom.domain.Currency;
import ua.nure.martseniuk.diplom.domain.Saving;
import ua.nure.martseniuk.diplom.domain.User;
import ua.nure.martseniuk.diplom.repositories.AccountRepository;
import ua.nure.martseniuk.diplom.security.repository.UserRepository;
import ua.nure.martseniuk.diplom.security.service.UserService;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Roman_Martseniuk
 */
@Service
public class AccountServiceImpl implements AccountService {

    private Logger logger  = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @Override
    public Account create(User user) {
        Account existing = accountRepository.findByName(user.getUsername());
        Assert.isNull(existing, "account already exists: " + user.getUsername());

        userService.create(user);


        Saving saving = new Saving();
        saving.setAmount(new BigDecimal(0));
        saving.setCurrency(Currency.getCurrency());
        saving.setInterest(new BigDecimal(0));
        saving.setDeposit(false);
        saving.setCapitalization(false);


        Account account = new Account();
        account.setName(user.getUsername());
        account.setLastSeen(new Date());
        account.setSaving(saving);
        accountRepository.save(account);
        logger.info("create new account for: " + account.getName());

        return account;
    }

    @Override
    public Account findByName(String accountName) {
        return accountRepository.findByName(accountName);
    }

    @Override
    public Account update(String username, Account update) {
        Account account = accountRepository.findByName(username);
        Assert.notNull(account, "can't find account with name " + username);

        account.setIncomes(update.getIncomes());
        account.setExpenses(update.getExpenses());
        account.setNote(update.getNote());
        account.setLastSeen(new Date());
        account.setEmail(update.getEmail());
        accountRepository.save(account);

        logger.debug("account {} changes has been saved", username);

        return account;
    }


}
