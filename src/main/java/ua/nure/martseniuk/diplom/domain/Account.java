package ua.nure.martseniuk.diplom.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author Roman_Martseniuk
 */

@Document(collection = "accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @Id
    private String name;

    private Date lastSeen;

    private String email;

    private List<Item> incomes;

    private List<Item> expenses;

    @Valid
    @NotNull
    private Saving saving;

    private String note;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Item> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Item> incomes) {
        this.incomes = incomes;
    }

    public List<Item> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Item> expenses) {
        this.expenses = expenses;
    }

    public Saving getSaving() {
        return saving;
    }

    public void setSaving(Saving saving) {
        this.saving = saving;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", lastSeen=" + lastSeen +
                ", email='" + email + '\'' +
                ", incomes=" + incomes +
                ", expenses=" + expenses +
                ", saving=" + saving +
                ", note='" + note + '\'' +
                '}';
    }
}
