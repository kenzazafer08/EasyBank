package Implementation;

import dto.Affectation;
import helpers.DBconnection;
import interfaces.AffectationI;
import java.sql.*;

public class AffectationDAO implements AffectationI {
    private DBconnection dbConnection;

    public AffectationDAO(DBconnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Affectation createNewAffectation(Affectation affectation) {
        try (Connection connection = dbConnection.getConnection()) {
            String insertAffectationSQL = "INSERT INTO affectation (start_date, end_date, employee_number, mission_code) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertAffectationSQL, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setDate(1, new java.sql.Date(affectation.getStartDate().getTime()));
                preparedStatement.setDate(2, new java.sql.Date(affectation.getEndDate().getTime()));
                preparedStatement.setString(3, affectation.getEmployee().getNumber());
                preparedStatement.setString(4, affectation.getMission().getCode());

                // Execute the INSERT statement
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                        return affectation;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return null;
    }

    @Override
    public boolean deleteAffectation(int id) {
        try (Connection connection = dbConnection.getConnection()) {
            String deleteAffectationSQL = "DELETE FROM affectation WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteAffectationSQL)) {
                preparedStatement.setInt(1, id);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    return true; // Deletion successful
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return false; // Affectation with the specified ID was not found or deletion failed
    }
}
