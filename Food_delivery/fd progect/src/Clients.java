import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Clients {
    private Connection connection;

    public Clients(Connection connection) {
        this.connection = connection;
    }

    public void addClient(String name, String phone, String address) {
        String query = "INSERT INTO clients (name, phone, address) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, address);
            preparedStatement.executeUpdate();
            System.out.println("Клиент добавлен.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(int id) {
        String query = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Клиент удален.");
            } else {
                System.out.println("Клиент с таким ID не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listClients() {
        String query = "SELECT * FROM clients";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Имя: " + resultSet.getString("name") +
                        ", Телефон: " + resultSet.getString("phone") +
                        ", Адрес: " + resultSet.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
