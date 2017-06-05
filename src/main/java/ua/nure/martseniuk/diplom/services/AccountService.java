package ua.nure.martseniuk.diplom.services;

import ua.nure.martseniuk.diplom.domain.Account;
import ua.nure.martseniuk.diplom.domain.User;

/**
 * @author Roman_Martseniuk
 */
public interface AccountService {

    Account create(User user);

    Account findByName(String accountName);

    Account update(String username, Account account);
}
