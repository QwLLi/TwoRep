package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;


@Slf4j
public class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String NAME = "postgres";
    private static final String PASSWORD = "1";

    private static SessionFactory sessionFactory;

    static {

        try {
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
                log.info("sessionFactoty создана");

        } catch (HibernateException e) {
            log.error("Ошибка при создании sessionFactoty",e);
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
