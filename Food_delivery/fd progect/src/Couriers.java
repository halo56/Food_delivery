import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Couriers {
    private Connection connection;

    public Couriers(Connection connection) {
        this.connection = connection;
    }

    public void applyCourier(String courierName, String phone) {
        String query = "INSERT INTO courier_applications (courier_name, phone) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, courierName);
            preparedStatement.setString(2, phone);
            preparedStatement.executeUpdate();
            System.out.println("Заявление курьера подано.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourierApplication(int id) {
        String query = "DELETE FROM courier_applications WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Заявление курьера удалено.");
            } else {
                System.out.println("Заявление с таким ID не найдено.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listCourierApplications() {
        String query = "SELECT * FROM courier_applications";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Имя курьера: " + resultSet.getString("courier_name") +
                        ", Телефон: " + resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
