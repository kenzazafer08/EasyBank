package Implementation;

import dto.*;
import helpers.DBconnection;
import helpers.helper;
import interfaces.AccountI;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

                // Insert into the specific account type table (currentaccount or savingaccount)
                if (account instanceof CurrentAccount) {
                    CurrentAccount currentAccount = (CurrentAccount) account;
                    String insertCurrentAccountSQL = "INSERT INTO currentaccount (account_number, overdraft) VALUES (?, ?)";
                    try (PreparedStatement currentAccountStmt = conn.prepareStatement(insertCurrentAccountSQL)) {
                        currentAccountStmt.setString(1, currentAccount.getNumber());
                        currentAccountStmt.setDouble(2, currentAccount.getOverdraft());
                        currentAccountStmt.executeUpdate();
                    }
                } else if (account instanceof SavingAccount) {
                    SavingAccount savingAccount = (SavingAccount) account;
                    String insertSavingAccountSQL = "INSERT INTO savingaccount (account_number, interest_rate) VALUES (?, ?)";
                    try (PreparedStatement savingAccountStmt = conn.prepareStatement(insertSavingAccountSQL)) {
                        savingAccountStmt.setString(1, savingAccount.getNumber());
                        savingAccountStmt.setDouble(2, savingAccount.getInterestRate());
                        savingAccountStmt.executeUpdate();
                    }
                }

                return account;
            }
        } catch (SQLException e) {
            // Handle any SQL errors here
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> searchByClient(Client client) {
            List<Account> clientAccounts = new ArrayList<>();
            try (Connection connection = dbConnection.getConnection()) {
                String query = "SELECT " +
                        "a.number AS account_number, " +
                        "a.sold AS account_balance, " +
                        "a.creation_date AS account_creation_date, " +
                        "a.state AS account_state, " +
                        "a.client_code AS account_client_code, " +
                        "a.employe_number AS account_employee_number, " +
                        "c.overdraft AS current_account_overdraft, " +
                        "s.interest_rate AS saving_account_interest_rate " +
                        "FROM " +
                        "account AS a " +
                        "LEFT JOIN currentaccount AS c ON a.number = c.account_number " +
                        "LEFT JOIN savingaccount AS s ON a.number = s.account_number " +
                        "WHERE " +
                        "a.client_code = ? AND a.deleted = false";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, client.getCode());

                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        String accountNumber = resultSet.getString("account_number");
                        double accountBalance = resultSet.getDouble("account_balance");
                        Date creationDate = resultSet.getDate("account_creation_date");
                        Account.State state = Account.State.valueOf(resultSet.getString("account_state"));

                        String employeeNumber = resultSet.getString("account_employee_number");
                        EmployeeDAO employeeDAO = new EmployeeDAO(dbConnection);
                        Employee employee = employeeDAO.searchByMatricul(employeeNumber);


                        if (resultSet.getObject("current_account_overdraft") != null) {
                            double overdraft = resultSet.getDouble("current_account_overdraft");
                            CurrentAccount currentAccount = new CurrentAccount();
                            currentAccount.setNumber(accountNumber);
                            currentAccount.setSold(accountBalance);
                            currentAccount.setCreationDate(creationDate);
                            currentAccount.setState(state);
                            currentAccount.setOverdraft(overdraft);
                            currentAccount.setEmployee(employee);
                            clientAccounts.add(currentAccount);
                        } else if (resultSet.getObject("saving_account_interest_rate") != null) {
                            double interestRate = resultSet.getDouble("saving_account_interest_rate");
                            SavingAccount savingAccount = new SavingAccount();
                            savingAccount.setNumber(accountNumber);
                            savingAccount.setSold(accountBalance);
                            savingAccount.setCreationDate(creationDate);
                            savingAccount.setState(state);
                            savingAccount.setInterestRate(interestRate);
                            savingAccount.setEmployee(employee);
                            clientAccounts.add(savingAccount);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately
            }

            return clientAccounts;
    }

    @Override
    public boolean delete(String id) {
        String sql = "UPDATE account SET deleted = ? WHERE number = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // Create a PreparedStatement with the query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set the "deleted" attribute to true (1)
            preparedStatement.setBoolean(1, true);

            // Set the employee ID in the query
            preparedStatement.setString(2, id);

            // Execute the query
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                return true; // Soft delete successful
            }
            return false;
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
