package Implementation;

import dto.Account;
import dto.SavingAccount;
import helpers.DBconnection;
import interfaces.SavingAccountI;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class SavingAcountDAO implements SavingAccountI {
    private DBconnection dbConnection;

    public SavingAcountDAO(DBconnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public SavingAccount add(SavingAccount account) {
        Connection conn = dbConnection.getConnection();
                    // Next, insert the saving account into the "savingaccount" table
                    String insertSavingAccountSQL = "INSERT INTO savingaccount (interest_rate, account_number) VALUES (?, ?)";
                    try (PreparedStatement savingAccountStmt = conn.prepareStatement(insertSavingAccountSQL)) {
                        savingAccountStmt.setDouble(1, account.getInterestRate());
                        savingAccountStmt.setString(2, account.getNumber());

                        // Execute the insert statement for the saving account
                        savingAccountStmt.executeUpdate();

                        // Return the account object with the generated ID
                        return account;
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
