package ua.nure.martseniuk.diplom.services;

/**
 * @author Roman_Martseniuk.
 */
public interface NotificationService {

    void sendRemindMessage();

    void sendReportMessage(String username);
}
