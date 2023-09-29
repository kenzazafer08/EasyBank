package interfaces;

import dto.Account;
import dto.CurrentAccount;

import java.util.List;

public interface AccountI<T extends Account> {
    T add(T account);
    List<T> searchByClient();
    boolean delete(int id);
    T updateStatus();
    List<T> filterByStatus();
    List<T> showList();
    List<T> filterByDCreation();
    T update();
    List<T> searchByOperationN();
}
