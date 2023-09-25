package dto;

import java.util.Date;
import java.util.List;

public class Account {

    protected String number;
    protected double sold;
    protected Date creationDate;
    protected State state;
    protected Client client;
    protected Employee employee;
    protected List<Operation> operationsList;
    public Account() {
    }

    public Account(String number, double sold, Date creationDate, State state, Client client, Employee employee, List<Operation> operationsList) {
        this.number = number;
        this.sold = sold;
        this.creationDate = creationDate;
        this.state = state;
        this.client = client;
        this.employee = employee;
        this.operationsList = operationsList;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Operation> getOperationsList() {
        return operationsList;
    }

    public void setOperationsList(List<Operation> operationsList) {
        this.operationsList = operationsList;
    }

    public enum State{
        active,
        inactive
    }
}
