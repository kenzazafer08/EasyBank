package interfaces;

import dto.Account;
import dto.Client;
import dto.CurrentAccount;

import java.util.List;

public interface AccountI<T extends Account> {
    T add(T account);
    List<T> searchByClient(Client client);
    boolean delete(String id);
    T updateStatus();
    List<T> filterByStatus();
    List<T> showList();
    List<T> filterByDCreation();
    T update();
    List<T> searchByOperationN();
}
