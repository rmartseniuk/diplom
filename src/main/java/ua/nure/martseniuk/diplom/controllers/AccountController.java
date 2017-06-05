package ua.nure.martseniuk.diplom.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.martseniuk.diplom.domain.Account;
import ua.nure.martseniuk.diplom.domain.User;
import ua.nure.martseniuk.diplom.services.AccountService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * @author Roman_Martseniuk
 */
@RestController
public class AccountController{

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private AccountService accountService;


    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Account createAccount(@Valid @RequestBody User user) {
        logger.info("Create new user with name: " + user.getUsername());
        return accountService.create(user);
    }

    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public Account getCurrentAccount(Principal principal) {
        Account account = accountService.findByName(principal.getName());
        logger.info("Get account for " + principal.getName() + " : " + account);
        return account;
    }

    @RequestMapping(path = "/current", method = RequestMethod.PUT)
    public void saveAccount(Principal principal, @RequestBody Account account) {
        accountService.update(principal.getName(), account);
    }

}
