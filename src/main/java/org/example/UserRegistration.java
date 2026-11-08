package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class UserRegistration {
    public static boolean registerUser(String username, String password) {

        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, hashedPassword);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }
}
