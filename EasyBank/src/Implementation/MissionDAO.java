package Implementation;

import dto.Mission;
import helpers.DBconnection;
import helpers.helper;
import interfaces.MissionI;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MissionDAO implements MissionI {

    private DBconnection dbConnection;

    public MissionDAO(DBconnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Mission add(Mission mission) {
        Connection conn = dbConnection.getConnection();
        try {
            // Generate a unique code for the mission (you can use your method or UUID)
            String missionCode = helper.generateClientCode(3);

            // Create an SQL INSERT query
            String insertMissionSQL = "INSERT INTO mission (code, name, description) VALUES (?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(insertMissionSQL)) {
                stmt.setString(1, missionCode);
                stmt.setString(2, mission.getName());
                stmt.setString(3, mission.getDescription());

                // Execute the insert statement
                int rowCount = stmt.executeUpdate();

                if (rowCount > 0) {
                    // The mission was successfully added to the database
                    mission.setCode(missionCode); // Set the generated code in the mission object
                    return mission;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL errors here
        }
        return null; // Adding mission failed
    }
    @Override
    public boolean delete(String id) {
        Connection conn = dbConnection.getConnection();
        try {
            // Create an SQL UPDATE query to set the 'deleted' column to true
            String updateMissionSQL = "UPDATE mission SET deleted = true WHERE code = ?";

            try (PreparedStatement stmt = conn.prepareStatement(updateMissionSQL)) {
                stmt.setString(1, id);

                // Execute the update statement
                int rowCount = stmt.executeUpdate();

                if (rowCount > 0) {
                    // The mission was successfully "soft" deleted
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL errors here
        }
        return false;
    }

    @Override
    public List<Mission> showList() {
        List<Mission> missionList = new ArrayList<>();

        try (Connection connection = dbConnection.getConnection()) {
            String query = "SELECT * FROM mission";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Mission mission = new Mission();
                    mission.setCode(resultSet.getString("code"));
                    mission.setName(resultSet.getString("name"));
                    mission.setDescription(resultSet.getString("description"));

                    missionList.add(mission);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return missionList;
    }

    @Override
    public List<Mission> missionHistory() {
        return null;
    }

}
