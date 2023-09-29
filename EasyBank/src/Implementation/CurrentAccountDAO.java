package Implementation;

import dto.Account;
import dto.Client;
import helpers.DBconnection;
import dto.CurrentAccount;
import interfaces.AccountI;

import java.sql.*;
import java.util.List;

public class CurrentAccountDAO implements AccountI<CurrentAccount> {
    private DBconnection dbConnection;

    public CurrentAccountDAO(DBconnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public CurrentAccount add(CurrentAccount account) {
        Connection conn = dbConnection.getConnection();
        try {
                    // Next, insert the saving account into the "savingaccount" table
                    String insertSavingAccountSQL = "INSERT INTO currentaccount (overdraft, account_number) VALUES (?, ?)";
                    try (PreparedStatement savingAccountStmt = conn.prepareStatement(insertSavingAccountSQL)) {
                        savingAccountStmt.setDouble(1, account.getOverdraft());
                        savingAccountStmt.setString(2, account.getNumber());

                                                // Execute the insert statement for the saving account
                        savingAccountStmt.executeUpdate();

                        // Return the account object with the generated ID
                        return account;
            }
        } catch (SQLException e) {
            // Handle any SQL errors here
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CurrentAccount> searchByClient(Client client) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public CurrentAccount updateStatus() {
        return null;
    }

    @Override
    public List<CurrentAccount> filterByStatus() {
        return null;
    }

    @Override
    public List<CurrentAccount> showList() {
        return null;
    }

    @Override
    public List<CurrentAccount> filterByDCreation() {
        return null;
    }

    @Override
    public CurrentAccount update() {
        return null;
    }

    @Override
    public List<CurrentAccount> searchByOperationN() {
        return null;
    }
}
