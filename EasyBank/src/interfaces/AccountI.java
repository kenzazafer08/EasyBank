package interfaces;

import dto.Account;
import dto.Client;
import dto.CurrentAccount;

import java.util.List;

public interface AccountI {
    Account add(Account account);
    List<Account> searchByClient(Client client);
    boolean delete(String id);
    Account updateStatus();
    List<Account> filterByStatus();
    List<Account> showList();
    List<Account> filterByDCreation();
    Account update();
    List<Account> searchByOperationN();
}
