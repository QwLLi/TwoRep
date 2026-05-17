package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS users
                (
                id serial PRIMARY KEY,
                name VARCHAR(100),
                last_name VARCHAR(100),
                age int                  
                )
                """;


        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery(sql)
                    .executeUpdate();

            transaction.commit();

            log.info("userDaoHibernate.createUsersTable(): Таблица создана");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка при создании таблицы: {}" + e);
        }


    }

    @Override
    public void dropUsersTable() {
        String sql = """
                DROP TABLE IF EXISTS users;
                """;

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();

            log.info("userDaoHibernate.dropUsersTable(): Таблица удалена");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка при удалении таблицы: {}" + e);
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();

            log.info("userDaoHibernate.save(): User с именем " + name + " добавлен");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка при добавлении User: {}" + e);
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
            }
            transaction.commit();

            log.info("userDaoHibernate.removeUserById(): User c id: " + id + " удален");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка при удалении User c id: {}" + id + e);
        }
    }

    @Override
    public List<User> getAllUsers() {

        try (Session session = sessionFactory.openSession()){
            return session.createQuery("FROM User",User.class).getResultList();

        } catch (HibernateException e) {
            log.error("Ошибка при вывводе всех User {}" + e);

        }
          return new ArrayList<>();
    }

    @Override
    public void cleanUsersTable() {
        String sql = """
                DELETE FROM users;
                """;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();

            log.info("userDaoHibernate.cleanUsersTable(): Таблица очищена успешно");
        }catch (HibernateException e){
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Ошибка при очищение таблицы {}" + e);

        }
    }
}
