package resources;

import org.checkerframework.checker.units.qual.C;

import java.sql.*;

public class DatabaseConnector {
    String link = "jdbc:mysql://localhost:3306/meetingbot";
    String user = "root";
    String password = "12345";

    public void addUser(long telegramId, String name, String description, String gender, String photo, int age, String city) {
        String sql = "INSERT INTO users (telegramid, name, description, gender, photo, age, city) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(link, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, telegramId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, photo);
            preparedStatement.setInt(6, age);
            preparedStatement.setString(7, city);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void editUser(long telegramId, String cellName, String newSign) {
        String sql = "UPDATE users SET " + cellName + " = ? WHERE telegramid = ?";
        try (Connection connection = DriverManager.getConnection(link, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newSign);
            preparedStatement.setLong(2, telegramId);

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            System.err.println(e);
        }
    }

    public boolean findUser(long telegramId) {
        try (Connection connection = DriverManager.getConnection(link, user, password)) {
            String sql = "SELECT * FROM users WHERE telegramid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == true) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
}
