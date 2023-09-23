package dto;

import java.util.Date;
import java.util.List;

public class Employee extends Person {

    public Employee() {
    }

    public Employee(String firstName, String lastName, Date birthDate, String phone, String address, String number, Date recruitementDate, String email, String mission, List<Account> createdAccounts, List<Operation> Operations, List<Affectation> affectationList) {
        super(firstName, lastName, birthDate, phone, address);
        this.number = number;
        this.recruitementDate = recruitementDate;
        this.email = email;
        this.mission = mission;
        this.createdAccounts = createdAccounts;
        this.Operations = Operations;
        this.affectationList = affectationList;
    }

    private String number;
    private Date recruitementDate;
    private String email;
    private String mission;
    private List<Account> createdAccounts;
    private List<Operation> Operations;
    private List<Affectation> affectationList;
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getRecruitementDate() {
        return recruitementDate;
    }

    public void setRecruitementDate(Date recruitementDate) {
        this.recruitementDate = recruitementDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public List<Account> getCreatedAccounts() {
        return createdAccounts;
    }

    public void setCreatedAccounts(List<Account> createAccounts) {
        this.createdAccounts = createAccounts;
    }

    public List<Operation> getOperations() {
        return Operations;
    }

    public void setOperations(List<Operation> Operations) {
        this.Operations = Operations;
    }

    public List<Affectation> getAffectationList() {
        return affectationList;
    }

    public void setAffectationList(List<Affectation> affectationList) {
        this.affectationList = affectationList;
    }
}
