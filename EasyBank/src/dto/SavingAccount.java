package dto;

import java.util.Date;
import java.util.List;

public class SavingAccount extends Account {

    private double interestRate;

    public SavingAccount() {
    }

    public SavingAccount(String number, double sold, Date creationDate, State state, double interestRate, Client client , Employee employee, List<Operation> operationList) {
        super(number, sold, creationDate, state, client, employee, operationList);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
