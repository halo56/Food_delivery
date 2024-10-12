import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Orders {
    private Connection connection;

    public Orders(Connection connection) {
        this.connection = connection;
    }

    public void addOrder(int clientId, int dishId, int quantity) {

        String query = "INSERT INTO orders (client_id, dish_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clientId);
            preparedStatement.setInt(2, dishId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.executeUpdate();
            System.out.println("Заказ добавлен.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        String query = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Заказ удален.");
            } else {
                System.out.println("Заказ с таким ID не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listOrders() {
        String query = "SELECT * FROM orders";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Клиент ID: " + resultSet.getInt("client_id") +
                        ", Блюдо ID: " + resultSet.getInt("dish_id") +
                        ", Кол-во: " + resultSet.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
