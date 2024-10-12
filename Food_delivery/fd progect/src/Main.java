import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyDatabase myDatabase = new MyDatabase();
        Connection connection = myDatabase.getConnection();
        Scanner scanner = new Scanner(System.in);

        if (connection == null) {
            System.out.println("Не удалось подключиться к базе данных.");
            return;
        }

        Menu menu = new Menu(connection);
        Orders orders = new Orders(connection);
        Clients clients = new Clients(connection);
        Districts districts = new Districts(connection);
        Couriers couriers = new Couriers(connection);

        while (true) {
            System.out.println("\nГлавное меню:");
            System.out.println("1. Управление Блюдами");
            System.out.println("2. Управление Заказами");
            System.out.println("3. Управление Клиентами");
            System.out.println("4. Управление Районами");
            System.out.println("5. Управление Заявлениями Курьеров");
            System.out.println("6. Выход");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // очищаем буфер

            switch (choice) {
                case 1:
                    // Управление блюдами
                    System.out.println("1. Добавить блюдо");
                    System.out.println("2. Удалить блюдо");
                    System.out.println("3. Показать блюда");

                    System.out.print("Выберите опцию: ");
                    int menuChoice = scanner.nextInt();
                    scanner.nextLine(); // очищаем буфер
                    if (menuChoice == 1) {
                        System.out.print("Введите название блюда: ");
                        String dishName = scanner.nextLine();
                        System.out.print("Введите цену: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine(); // очищаем буфер
                        System.out.print("Введите описание: ");
                        String description = scanner.nextLine();
                        menu.addDish(dishName, price, description);
                    } else if (menuChoice == 2) {
                        System.out.print("Введите ID блюда для удаления: ");
                        int idToDelete = scanner.nextInt();
                        menu.deleteDish(idToDelete);
                    } else if (menuChoice == 3) {
                        menu.listDishes();
                    }
                    break;

                case 2:
                    // Управление заказами
                    System.out.println("1. Добавить заказ");
                    System.out.println("2. Удалить заказ");
                    System.out.println("3. Показать заказы");
                    System.out.print("Выберите опцию: ");
                    int ordersChoice = scanner.nextInt();
                    scanner.nextLine(); // очищаем буфер
                    if (ordersChoice == 1) {
                        System.out.print("Введите ID клиента: ");
                        int clientId = scanner.nextInt();
                        System.out.print("Введите ID блюда: ");
                        int dishId = scanner.nextInt();
                        System.out.print("Введите количество: ");
                        int quantity = scanner.nextInt();
                        orders.addOrder(clientId, dishId, quantity);
                    } else if (ordersChoice == 2) {
                        System.out.print("Введите ID заказа для удаления: ");
                        int idToDelete = scanner.nextInt();
                        orders.deleteOrder(idToDelete);
                    } else if (ordersChoice == 3) {
                        orders.listOrders();
                    }
                    break;

                case 3:
                    // Управление клиентами
                    System.out.println("1. Добавить клиента");
                    System.out.println("2. Удалить клиента");
                    System.out.println("3. Показать клиентов");
                    System.out.print("Выберите опцию: ");
                    int clientsChoice = scanner.nextInt();
                    scanner.nextLine(); // очищаем буфер
                    if (clientsChoice == 1) {
                        System.out.print("Введите имя клиента: ");
                        String name = scanner.nextLine();
                        System.out.print("Введите телефон клиента: ");
                        String phone = scanner.nextLine();
                        System.out.print("Введите адрес клиента: ");
                        String address = scanner.nextLine();
                        clients.addClient(name, phone, address);
                    } else if (clientsChoice == 2) {
                        System.out.print("Введите ID клиента для удаления: ");
                        int idToDelete = scanner.nextInt();
                        clients.deleteClient(idToDelete);
                    } else if (clientsChoice == 3) {
                        clients.listClients();
                    }
                    break;

                case 4:
                    // Управление районами
                    System.out.println("1. Добавить район");
                    System.out.println("2. Удалить район");
                    System.out.println("3. Показать районы");
                    System.out.print("Выберите опцию: ");

                    int districtsChoice = scanner.nextInt();
                    scanner.nextLine(); // очищаем буфер
                    if (districtsChoice == 1) {
                        System.out.print("Введите название района: ");
                        String districtName = scanner.nextLine();
                        districts.addDistrict(districtName);
                    } else if (districtsChoice == 2) {
                        System.out.print("Введите ID района для удаления: ");
                        int idToDelete = scanner.nextInt();
                        districts.deleteDistrict(idToDelete);
                    } else if (districtsChoice == 3) {
                        districts.listDistricts();
                    }
                    break;

                case 5:
                    // Управление заявками курьеров
                    System.out.println("1. Подать заявление курьера");
                    System.out.println("2. Удалить заявление курьера");
                    System.out.println("3. Показать заявления курьеров");
                    System.out.print("Выберите опцию: ");
                    int courierChoice = scanner.nextInt();
                    scanner.nextLine(); // очищаем буфер
                    if (courierChoice == 1) {

                        System.out.print("Введите имя курьера: ");
                        String courierName = scanner.nextLine();
                        System.out.print("Введите телефон курьера: ");
                        String phone = scanner.nextLine();
                        couriers.applyCourier(courierName, phone);
                    } else if (courierChoice == 2) {
                        System.out.print("Введите ID заявления для удаления: ");
                        int idToDelete = scanner.nextInt();
                        couriers.deleteCourierApplication(idToDelete);
                    } else if (courierChoice == 3) {
                        couriers.listCourierApplications();
                    }
                    break;

                case 6:
                    // Выход
                    myDatabase.closeConnection(); // Используйте метод closeConnection
                    System.out.println("Вы вышли из приложения.");
                    return;

                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
}

