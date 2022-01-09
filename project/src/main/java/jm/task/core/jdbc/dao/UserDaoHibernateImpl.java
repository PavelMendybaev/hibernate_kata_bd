package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.*;

import org.hibernate.cfg.Configuration;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure()
                    .addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }


    @Override
    public void createUsersTable(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS User  (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` NVARCHAR(45) NULL,\n" +
                "  `lastName` NVARCHAR(45) NULL,\n" +
                "  `age` INT NULL,\n" +
                "  PRIMARY KEY (`id`));\n";

        session.createSQLQuery(sql).executeUpdate();



        session.getTransaction().commit();
        session.close();

    }


    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS user ;";

        session.createSQLQuery(sql).executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = new User(name , lastName , age);

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = (User) session.load(User.class,id);
        session.delete(user);


        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "SELECT * FROM user;";

        users = session.createSQLQuery (sql).list();

        session.getTransaction().commit();
        session.close();

        return  users;

    }

    @Override
    public void cleanUsersTable() {
        dropUsersTable();
        createUsersTable();
    }


}
