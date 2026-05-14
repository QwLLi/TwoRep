package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS users(
                    id serial PRIMARY KEY,
                    name VARCHAR(100),
                    last_name VARCHAR(100),
                    age int
                
                )
                """;

        try (var connect = Util.connect();
             Statement statement = connect.createStatement()) {

            statement.executeUpdate(sql);
            log.info("UserDaoJDBCImpl.createUsersTable таблица users создана");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String sql = """
                DROP TABLE IF EXISTS users;
                """;

        try (var connections = Util.connect();
             var statment = connections.createStatement()) {

            statment.executeUpdate(sql);
            log.info("DB Delete");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = """
                INSERT INTO users(name , last_name , age)
                VALUES (? , ? , ?)
                """;

        try (var connection = Util.connect();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();

            log.info("User с именем: " + name + " " + lastName + " возраст: " + age + " успешкко добавлен в БД");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = """
                SELECT  * FROM users
                """;
        try (var connectons = Util.connect();
             var statment = connectons.createStatement();
             var resultstatment = statment.executeQuery(sql)) {

            while (resultstatment.next()) {
                User user = new User();
                user.setId(resultstatment.getLong("id"));
                user.setName(resultstatment.getString("name"));
                user.setLastName(resultstatment.getString("last_name"));
                user.setAge(resultstatment.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void cleanUsersTable() {
        String sql = """
                DELETE FROM users
                """;

        try (var connections = Util.connect();
             var statment = connections.createStatement()) {
            statment.executeUpdate(sql);
            log.info("Table clear");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

