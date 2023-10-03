package Implementation;

import dto.Affectation;
import dto.Employee;
import dto.Mission;
import helpers.DBconnection;
import interfaces.AffectationI;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


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

    @Override
    public List<Affectation> affectationsList() {
        List<Affectation> affectations = new ArrayList<>();

        try (Connection connection = dbConnection.getConnection()) {
            String query = "SELECT " +
                    "a.id AS affectation_id, " +
                    "a.start_date AS affectation_start_date, " +
                    "a.end_date AS affectation_end_date, " +
                    "a.employee_number AS affectation_employee_number, " +
                    "a.mission_code AS affectation_mission_code, " +
                    "m.name AS mission_name, " +
                    "m.description AS mission_description " +
                    "FROM " +
                    "affectation AS a " +
                    "INNER JOIN mission AS m ON a.mission_code = m.code";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Date startDate = resultSet.getDate("affectation_start_date");
                    Date endDate = resultSet.getDate("affectation_end_date");
                    String employeeNumber = resultSet.getString("affectation_employee_number");
                    String missionCode = resultSet.getString("affectation_mission_code");
                    String missionName = resultSet.getString("mission_name");
                    String missionDescription = resultSet.getString("mission_description");

                    EmployeeDAO employeeDAO = new EmployeeDAO(dbConnection);
                    Employee employee = employeeDAO.searchByMatricul(employeeNumber);


                    Mission mission = new Mission();
                    mission.setCode(missionCode);
                    mission.setName(missionName);
                    mission.setDescription(missionDescription);

                    Affectation affectation = new Affectation();
                    affectation.setStartDate(startDate);
                    affectation.setEndDate(endDate);
                    affectation.setEmployee(employee);
                    affectation.setMission(mission);

                    affectations.add(affectation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affectations;
    }

}
