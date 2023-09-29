package Implementation;

import dto.Employee;
import helpers.DBconnection;
import interfaces.EmployeeI;

import java.sql.*;
import java.util.List;
import java.time.LocalDate;

public class EmployeeDAO implements EmployeeI {
    private DBconnection dbConnection;

    public EmployeeDAO(DBconnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Employee add(Employee employee) {
        Connection connection = dbConnection.getConnection();
        String insertPersonQuery = "INSERT INTO person (first_name, last_name, phone, address) " +
                "VALUES (?, ?, ?, ?)";

        String insertEmployeeQuery = "INSERT INTO employee (number, recruitment_date, email, person_id) " +
                "VALUES (?, ?, ?, ?)";

        try {
            // Insert the Person record and retrieve the generated ID
            PreparedStatement personStatement = connection.prepareStatement(insertPersonQuery, Statement.RETURN_GENERATED_KEYS);
            personStatement.setString(1, employee.getFirstName());
            personStatement.setString(2, employee.getLastName());
            personStatement.setString(3, employee.getPhone());
            personStatement.setString(4, employee.getAddress());

            int rowsInserted = personStatement.executeUpdate();
            if (rowsInserted == 0) {
                System.out.println("here");
                return null;
            }

            ResultSet generatedKeys = personStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int personId = generatedKeys.getInt(1); // Get the generated person_id
                // Insert the Employee record with the person_id
                PreparedStatement employeeStatement = connection.prepareStatement(insertEmployeeQuery);
                employeeStatement.setString(1, employee.getNumber());
                employeeStatement.setDate(2, new java.sql.Date(Date.valueOf(LocalDate.now()).getTime()));
                employeeStatement.setString(3, employee.getEmail());
                employeeStatement.setInt(4, personId); // Use the person_id

                int employeeRowsInserted = employeeStatement.executeUpdate();
                if (employeeRowsInserted > 0) {
                    return employee;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Employee searchByMatricul(String matriculationNumber) {
        String sql = "SELECT e.*, p.* FROM employee e " +
                "INNER JOIN person p ON e.person_id = p.id " +
                "WHERE e.number = ? AND e.deleted = FALSE";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, matriculationNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setNumber(resultSet.getString("number"));
                    employee.setFirstName(resultSet.getString("first_name"));
                    employee.setLastName(resultSet.getString("last_name"));
                    employee.setPhone(resultSet.getString("phone"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setAddress(resultSet.getString("address"));
                    // Set other employee attributes here
                    return employee;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection or query error
        }
        return null; // Return null if no employee with the specified matriculation number is found
    }

    @Override
    public boolean delete(String id) {
        String sql = "UPDATE employee SET deleted = ? WHERE number = ?";
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
    public List<Employee> showList() {
        return null;
    }

    @Override
    public List<Employee> searchByDateR() {
        return null;
    }

    @Override
    public Employee update() {
        return null;
    }
}
