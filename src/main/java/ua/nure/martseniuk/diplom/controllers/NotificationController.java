package ua.nure.martseniuk.diplom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.martseniuk.diplom.domain.Account;
import ua.nure.martseniuk.diplom.services.NotificationService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * @author Roman_Martseniuk.
 */
@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(path = "/report", method = RequestMethod.POST)
    public void sendMessage(Principal principal) {
        notificationService.sendReportMessage(principal.getName());
    }

}
