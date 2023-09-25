package dto;

import java.util.Date;

public class Operation {

    private String number;
    private Date creationDate;
    private double amount;
    private Type type;
    private Account account;
    private Employee employee;
    public Operation() {
    }

    public Operation(String number, Date creationDate, double amount, Type type, Account account, Employee employee) {
        this.number = number;
        this.creationDate = creationDate;
        this.amount = amount;
        this.type = type;
        this.account = account;
        this.employee = employee;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public enum Type{
        payment,
        withdrawal
    }
}
