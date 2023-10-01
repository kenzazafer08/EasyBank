package Implementation;

import dto.Account;
import dto.Operation;
import helpers.DBconnection;
import helpers.helper;
import interfaces.AccountI;
import interfaces.OperationI;

import java.sql.Connection;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class OperationDAO implements OperationI {
    private DBconnection dbConnection;

    public OperationDAO(DBconnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Operation add(Operation operation) {
        Connection conn = dbConnection.getConnection();
        try {

            // Check if the account exists
            AccountI accountDAO = new AccountDAO(dbConnection);
            Account account = accountDAO.getByNumber(operation.getAccount().getNumber());

            if (account == null) {
                return null;
            }

            // Check if the operation type is "payment" and if there's sufficient balance
            if (operation.getType() == Operation.Type.payment) {
                double amount = operation.getAmount();
                if (amount <= 0) {
                    System.out.println("Payment amount must be greater than 0");
                    return null;
                }

                if (account.getSold() < amount) {
                    System.out.println("Insufficient balance for payment");
                    return null;
                }

                // Deduct the payment amount from the account's balance
                account.setSold(account.getSold() - amount);
            } else if (operation.getType() == Operation.Type.withdrawal) {
                double amount = operation.getAmount();
                if (amount <= 0) {
                    throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
                }

                // Add the withdrawal amount to the account's balance
                account.setSold(account.getSold() + amount);
            }
            // Insert the operation record into the "operation" table
            String insertOperationSQL = "INSERT INTO operation (number, creation_date, amount, type, employee_number, account_number) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertOperationSQL, Statement.RETURN_GENERATED_KEYS)) {
                String number = helper.generateClientCode(10);
                operation.setNumber(number);
                stmt.setString(1, operation.getNumber());
                stmt.setDate(2, new java.sql.Date(Date.valueOf(LocalDate.now()).getTime()));
                stmt.setDouble(3, operation.getAmount());
                stmt.setString(4, String.valueOf(operation.getType()));
                stmt.setString(5, operation.getEmployee().getNumber());
                stmt.setString(6, operation.getAccount().getNumber());

                // Execute the insert statement
                stmt.executeUpdate();

                return operation;
            }
        } catch (SQLException e) {
            // Handle any SQL errors here
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Operation> searchByNumber() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        Connection conn = dbConnection.getConnection();
        try {
            // Create a SQL DELETE query to delete the operation by its ID
            String deleteOperationSQL = "DELETE FROM operation WHERE number = ?";

            try (PreparedStatement stmt = conn.prepareStatement(deleteOperationSQL)) {
                stmt.setString(1, id);

                // Execute the delete statement
                int rowCount = stmt.executeUpdate();

                // Check if any rows were deleted (rowCount > 0)
                if (rowCount > 0) {
                    return true; // Deletion successful
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL errors here
        }
        return false; // Deletion failed
    }
}
