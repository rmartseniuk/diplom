package ua.nure.martseniuk.diplom.domain;

import java.util.Arrays;

/**
 * @author Roman_Martseniuk.
 */
public class Email {

    private String to;
    private String subject;
    private String template;
    private String fileName;
    private byte[] attachedFile;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getAttachedFile() {
        return attachedFile;
    }

    public void setAttachedFile(byte[] attachedFile) {
        this.attachedFile = attachedFile;
    }

    @Override
    public String toString() {
        return "Email{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", template='" + template + '\'' +
                ", fileName='" + fileName + '\'' +
                ", attachedPDF=" + Arrays.toString(attachedFile) +
                '}';
    }
}
