package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class UserAuthentication {
    public static boolean login(String username, String password) {

        String sql = "SELECT password FROM users WHERE username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                String storedPassword = result.getString("password");

                return BCrypt.checkpw(password, storedPassword);
            }

        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }

        return false;
    }
}
