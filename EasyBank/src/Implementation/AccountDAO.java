package Implementation;

import dto.Account;
import helpers.DBconnection;
import helpers.helper;
import interfaces.AccountI;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class AccountDAO implements AccountI {
    private DBconnection dbConnection;

    public AccountDAO(DBconnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public AccountDAO() {
    }

    @Override
    public Account add(Account account) {
        Connection conn = dbConnection.getConnection();
        try {
            // First, insert the account into the "account" table
            String insertAccountSQL = "INSERT INTO account (number, sold, creation_date, state, client_code, employe_number) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertAccountSQL, Statement.RETURN_GENERATED_KEYS)) {
                String number = helper.generateClientCode(10);
                account.setNumber(number);
                stmt.setString(1, account.getNumber());
                stmt.setDouble(2, account.getSold());
                stmt.setDate(3, new java.sql.Date(Date.valueOf(LocalDate.now()).getTime()));
                stmt.setString(4, String.valueOf(Account.State.active));
                stmt.setString(5, account.getClient().getCode());
                stmt.setString(6, account.getEmployee().getNumber());

                // Execute the insert statement
                stmt.executeUpdate();

                    return account;
            }
        } catch (SQLException e) {
            // Handle any SQL errors here
            e.printStackTrace();
        }
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
