package ua.nure.martseniuk.diplom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.martseniuk.diplom.domain.Account;
import ua.nure.martseniuk.diplom.domain.Email;
import ua.nure.martseniuk.diplom.repositories.AccountRepository;
import ua.nure.martseniuk.diplom.util.GenerateReportByDate;

import java.io.IOException;

/**
 * @author Roman_Martseniuk.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void sendRemindMessage() {

    }

    @Override
    public void sendReportMessage(String username) {
        Account account = accountRepository.findByName(username);
        Email email = prepareEmail(account);
        emailService.send(email);
    }

    private Email prepareEmail(Account account) {
        Email email = new Email();
        email.setTo(account.getEmail());
        email.setFileName("Report about incomes and expenses");
        email.setSubject("Report");
        email.setTemplate("Confratulation");
        try {
            email.setAttachedFile(GenerateReportByDate.generateReport(account));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return email;
    }
}
