package interfaces;

import dto.Account;

import java.util.List;

public interface AccountI {
    Account add(Account account);
    List<Account> searchByClient();
    boolean delete(int id);
    Account updateStatus();
    List<Account> filterByStatus();
    List<Account> showList();
    List<Account> filterByDCreation();
    Account update();
    List<Account> searchByOperationN();
}
