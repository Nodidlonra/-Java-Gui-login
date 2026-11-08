package org.example;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {

        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            System.out.println("Database connected successfully!");

        } else {
            System.out.println("Database connection unsuccessful!");
        }
    }
}
