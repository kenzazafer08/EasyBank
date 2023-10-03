package interfaces;

import dto.Account;
import dto.Client;

import java.util.List;

public interface AccountI {
    Account add(Account account);
    List<Account> searchByClient(Client client);
    boolean delete(String id);
    Account updateStatus(String id);
    List<Account> showList();
    Account getByNumber(String accountNumber);
    Account searchByOperationN(String n);
}
