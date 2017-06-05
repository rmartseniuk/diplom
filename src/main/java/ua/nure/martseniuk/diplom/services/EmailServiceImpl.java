package ua.nure.martseniuk.diplom.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.nure.martseniuk.diplom.domain.Account;
import ua.nure.martseniuk.diplom.domain.Email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

/**
 * @author Roman_Martseniuk.
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final String ERROR_MESSAGE_EMAIL_NOTIFICATION = "Message Exception \n{}";
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    private static final String ENCODING = "UTF-8";
    private static final boolean MULTIPART = true;

    @Autowired
    private JavaMailSender sender;

    @Override
    public boolean send(Email email) {
        boolean isMailSend = false;
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MULTIPART, ENCODING);
            helper.setFrom(((JavaMailSenderImpl) sender).getUsername());
            helper.setTo(email.getTo());
            helper.setText(email.getTemplate());
            helper.setSubject(email.getSubject());

            if (Objects.nonNull(email.getFileName()) && Objects.nonNull(email.getAttachedFile()) &&
                    email.getAttachedFile().length > 0) {
                helper.addAttachment(email.getFileName(), new ByteArrayResource(email.getAttachedFile()));
            }
            sender.send(message);
            isMailSend = true;
        } catch (MessagingException e) {
            LOGGER.warn(ERROR_MESSAGE_EMAIL_NOTIFICATION, email);
            LOGGER.warn(e.toString());
        }
        return isMailSend;
    }

}
