import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Districts {
    private Connection connection;

    public Districts(Connection connection) {
        this.connection = connection;
    }

    public void addDistrict(String districtName) {
        String query = "INSERT INTO districts (district_name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, districtName);
            preparedStatement.executeUpdate();
            System.out.println("Район добавлен.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDistrict(int id) {
        String query = "DELETE FROM districts WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Район удален.");
            } else {
                System.out.println("Район с таким ID не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listDistricts() {
        String query = "SELECT * FROM districts";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Название района: " + resultSet.getString("district_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
