package dto;

import java.util.Date;
import java.util.List;

public class Client extends Person {


    private String Code;
    private List<Account> Accounts;

    public Client() {
    }

    public Client(String firstName, String lastName, Date birthDate, String phone, String address, String code, List<Account> Accounts) {
        super(firstName, lastName, phone, address);
        Code = code;
        this.Accounts = Accounts;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public List<Account> getAccounts() {
        return Accounts;
    }

    public void setAccounts(List<Account> Accounts) {
        this.Accounts = Accounts;
    }
}
