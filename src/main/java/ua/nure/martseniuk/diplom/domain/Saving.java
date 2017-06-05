package ua.nure.martseniuk.diplom.domain;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Roman_Martseniuk on 6/3/2017.
 */
public class Saving {

    @NotNull
    private BigDecimal amount;
    @NotNull
    private Currency currency;
    @NotNull
    private BigDecimal interest;
    @NotNull
    private Boolean deposit;
    @NotNull
    private Boolean capitalization;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Boolean getDeposit() {
        return deposit;
    }

    public void setDeposit(Boolean deposit) {
        this.deposit = deposit;
    }

    public Boolean getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(Boolean capitalization) {
        this.capitalization = capitalization;
    }

    @Override
    public String toString() {
        return "Saving{" +
                "amount=" + amount +
                ", currency=" + currency +
                ", interest=" + interest +
                ", deposit=" + deposit +
                ", capitalization=" + capitalization +
                '}';
    }
}
