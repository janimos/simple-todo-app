
import java.sql.*;
import java.util.Scanner;

public class TodoApp {
    private static final String DB_URL = "jdbc:sqlite:tasks.db";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            createTableIfNotExists(conn);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n--- TODO LIST ---");
                System.out.println("1. Pievienot uzdevumu");
                System.out.println("2. Parādīt visus uzdevumus");
                System.out.println("3. Dzēst uzdevumu");
                System.out.println("0. Iziet");
                System.out.print("Izvēlies: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> addTask(conn, scanner);
                    case "2" -> listTasks(conn);
                    case "3" -> deleteTask(conn, scanner);
                    case "0" -> {
                        System.out.println("Uz redzēšanos!");
                        return;
                    }
                    default -> System.out.println("Nederīga izvēle.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Kļūda: " + e.getMessage());
        }
    }

    private static void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS tasks (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL)");
        }
    }

    private static void addTask(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Ievadi uzdevuma nosaukumu: ");
        String taskName = scanner.nextLine();
        String sql = "INSERT INTO tasks (name) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, taskName);
            pstmt.executeUpdate();
            System.out.println("Uzdevums pievienots!");
        }
    }

    private static void listTasks(Connection conn) throws SQLException {
        String sql = "SELECT * FROM tasks";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Uzdevumu saraksts ---");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("name"));
            }
        }
    }

    private static void deleteTask(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Ievadi dzēšamā uzdevuma ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int deleted = pstmt.executeUpdate();
            if (deleted > 0) {
                System.out.println("Uzdevums izdzēsts.");
            } else {
                System.out.println("Uzdevums nav atrasts.");
            }
        }
    }
}
