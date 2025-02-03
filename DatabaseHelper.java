import java.sql.*;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:contacts.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            return null;
        }
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS contacts (id INTEGER PRIMARY KEY, name TEXT, phone TEXT)";
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addContact(String name, String phone) {
        String sql = "INSERT INTO contacts(name, phone) VALUES(?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.executeUpdate();
            System.out.println("Contact added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewContacts() {
        String sql = "SELECT * FROM contacts";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("name") + " - " + rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateContact(int id, String newName, String newPhone) {
        String sql = "UPDATE contacts SET name = ?, phone = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, newPhone);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Contact updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteContact(int id) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Contact deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
