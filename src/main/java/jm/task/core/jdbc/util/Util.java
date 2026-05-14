package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;

public class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String NAME = "postgres";
    private static final String PASSWORD = "1";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        // org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        if (sessionFactory == null) {

            sessionFactory = new org.hibernate.cfg.Configuration()
                    .setProperty(Environment.DRIVER, "org.postgresql.Driver")
                    .setProperty(Environment.URL, URL)
                    .setProperty(Environment.USER, NAME)
                    .setProperty(Environment.PASS, PASSWORD)
                    .setProperty(Environment.SHOW_SQL, "true")
                    .setProperty(Environment.HBM2DDL_AUTO, "none")
                    .setProperty(Environment.DIALECT , "org.hibernate.dialect.PostgreSQLDialect")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }

        return sessionFactory;
    }
}
