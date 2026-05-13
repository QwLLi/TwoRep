package jm.task.core.jdbc.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String NAME = "postgres";
    private static final String PASSWORD = "1"; // 1 валидный

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, NAME, PASSWORD);

    }
    // реализуйте настройку соеденения с БД
}
