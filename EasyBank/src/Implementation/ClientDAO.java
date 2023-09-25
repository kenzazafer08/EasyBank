package Implementation;

import dto.Client;
import helpers.DBconnection;
import helpers.helper;
import interfaces.ClientI;

import java.sql.*;
import java.util.List;

public class ClientDAO implements ClientI {
    private DBconnection dbConnection;

    public ClientDAO(DBconnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Client add(Client client) {
        Connection connection = dbConnection.getConnection();

        String insertPersonQuery = "INSERT INTO person (first_name, last_name, phone, address) " +
                "VALUES (?, ?, ?, ?)";

        String insertClientQuery = "INSERT INTO client (code, person_id) " +
                "VALUES (?, ?)";

        try {
            // Insert the Person record and retrieve the generated ID
            PreparedStatement personStatement = connection.prepareStatement(insertPersonQuery, Statement.RETURN_GENERATED_KEYS);
            personStatement.setString(1, client.getFirstName());
            personStatement.setString(2, client.getLastName());
            personStatement.setString(3, client.getPhone());
            personStatement.setString(4, client.getAddress());

            int rowsInserted = personStatement.executeUpdate();
            if (rowsInserted == 0) {
                System.out.println("Failed to insert Person record.");
                return null;
            }

            ResultSet generatedKeys = personStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int personId = generatedKeys.getInt(1); // Get the generated person_id
                // Insert the Client record with the person_id
                String clientCode = helper.generateClientCode(5);
                client.setCode(clientCode);
                PreparedStatement clientStatement = connection.prepareStatement(insertClientQuery);
                clientStatement.setString(1, client.getCode());
                clientStatement.setInt(2, personId); // Use the person_id

                int clientRowsInserted = clientStatement.executeUpdate();
                if (clientRowsInserted > 0) {
                    return client;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error adding client: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Client searchByCode(String code) {
        String sql = "SELECT c.*, p.* FROM client c " +
                "INNER JOIN person p ON c.person_id = p.id " +
                "WHERE c.code = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, code);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Client client = new Client();
                    client.setCode(resultSet.getString("code"));
                    client.setFirstName(resultSet.getString("first_name"));
                    client.setLastName(resultSet.getString("last_name"));
                    client.setPhone(resultSet.getString("phone"));
                    client.setAddress(resultSet.getString("address"));
                    return client;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Client> showList() {
        return null;
    }

    @Override
    public List<Client> searchByPrenom() {
        return null;
    }

    @Override
    public Client update() {
        return null;
    }
}
