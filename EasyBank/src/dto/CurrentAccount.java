package dto;

import java.util.Date;
import java.util.List;

public class CurrentAccount extends Account {

    private double overdraft;

    public CurrentAccount() {
    }

    public CurrentAccount(String number, double sold, Date creationDate, State state, double overdraft, Client client, Employee employee, List<Operation> operationList) {
        super(number, sold, creationDate, state, client, employee,operationList);
        this.overdraft = overdraft;
    }


    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }
}
