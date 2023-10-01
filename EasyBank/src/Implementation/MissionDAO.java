package Implementation;

import dto.Mission;
import helpers.DBconnection;
import helpers.helper;
import interfaces.MissionI;

import java.sql.Connection;
import java.sql.*;
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
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Mission> showList() {
        return null;
    }

    @Override
    public List<Mission> missionHistory() {
        return null;
    }

    @Override
    public List<Mission> missionStatistics() {
        return null;
    }
}
