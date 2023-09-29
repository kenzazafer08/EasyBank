package Implementation;

import dto.Account;
import dto.SavingAccount;
import helpers.DBconnection;
import interfaces.AccountI;

import java.sql.*;
import java.util.List;

public class SavingAcountDAO implements AccountI<SavingAccount> {
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
    public List<SavingAccount> searchByClient() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public SavingAccount updateStatus() {
        return null;
    }

    @Override
    public List<SavingAccount> filterByStatus() {
        return null;
    }

    @Override
    public List<SavingAccount> showList() {
        return null;
    }

    @Override
    public List<SavingAccount> filterByDCreation() {
        return null;
    }

    @Override
    public SavingAccount update() {
        return null;
    }

    @Override
    public List<SavingAccount> searchByOperationN() {
        return null;
    }
}
