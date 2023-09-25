package interfaces;

import dto.Account;
import dto.CurrentAccount;

import java.util.List;

public interface CurrentAccountI {
    CurrentAccount add(CurrentAccount account);
    List<Account> searchByClient();
    boolean delete(int id);
    Account updateStatus();
    List<Account> filterByStatus();
    List<Account> showList();
    List<Account> filterByDCreation();
    Account update();
    List<Account> searchByOperationN();
}
