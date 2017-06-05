package ua.nure.martseniuk.diplom.services;

import org.springframework.stereotype.Service;
import ua.nure.martseniuk.diplom.domain.Account;
import ua.nure.martseniuk.diplom.domain.Email;

/**
 * @author Roman_Martseniuk.
 */
public interface EmailService {

    boolean send(Email email);

}
