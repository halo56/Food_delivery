import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu {
    private Connection connection;

    public Menu(Connection connection) {
        this.connection = connection;
    }

    public void addDish(String dishName, double price, String description) {
        String query = "INSERT INTO menu (dish_name, price, description) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, dishName);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, description);
            preparedStatement.executeUpdate();
            System.out.println("Блюдо добавлено.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDish(int id) {
        String query = "DELETE FROM menu WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Блюдо удалено.");
            } else {
                System.out.println("Блюдо с таким ID не найдено.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listDishes() {
        String query = "SELECT * FROM menu";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Название: " + resultSet.getString("dish_name") +
                        ", Цена: " + resultSet.getDouble("price") +
                        ", Описание: " + resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
