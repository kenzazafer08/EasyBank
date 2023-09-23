package Implementation;

import dto.Account;
import interfaces.AccountI;

import java.util.List;

public class SavingAcountDAO implements AccountI {
    @Override
    public Account add(Account account) {
        return null;
    }

    @Override
    public List<Account> searchByClient() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Account updateStatus() {
        return null;
    }

    @Override
    public List<Account> filterByStatus() {
        return null;
    }

    @Override
    public List<Account> showList() {
        return null;
    }

    @Override
    public List<Account> filterByDCreation() {
        return null;
    }

    @Override
    public Account update() {
        return null;
    }

    @Override
    public List<Account> searchByOperationN() {
        return null;
    }
}
