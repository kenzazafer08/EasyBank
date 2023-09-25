package interfaces;

import dto.Account;
import dto.SavingAccount;

import java.util.List;

public interface SavingAccountI {
    SavingAccount add(SavingAccount account);
    List<Account> searchByClient();
    boolean delete(int id);
    Account updateStatus();
    List<Account> filterByStatus();
    List<Account> showList();
    List<Account> filterByDCreation();
    Account update();
    List<Account> searchByOperationN();
}
