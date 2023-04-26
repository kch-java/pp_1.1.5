package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String CREATE = "CREATE TABLE IF NOT EXISTS `users` (" +
            "  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NOT NULL," +
            "  `lastName` VARCHAR(45) NOT NULL," +
            "  `age` TINYINT UNSIGNED NOT NULL," +
            "  PRIMARY KEY (`id`));";
    private static final String DROP = "DROP TABLE IF EXISTS users";
    private static final String INSERT_NEW = "INSERT INTO users (name, lastName, age) VALUES(?, ?, ?)";
    private static final String REMOVE_ID = "DELETE FROM users WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM users";
    private static final String CLEAN_ALL = "TRUNCATE TABLE users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate(CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate(DROP);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(INSERT_NEW)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(REMOVE_ID)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()){

            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate(CLEAN_ALL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
