package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import org.hibernate.cfg.Configuration;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getsessionFactory();


    @Override
    public void createUsersTable(){


        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS User  (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` NVARCHAR(45) NULL,\n" +
                "  `lastName` NVARCHAR(45) NULL,\n" +
                "  `age` INT NULL,\n" +
                "  PRIMARY KEY (`id`));\n";

        session.createSQLQuery(sql).executeUpdate();


        try{
            transaction.commit();
        }catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
    }


    @Override
    public void dropUsersTable() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        String sql = "DROP TABLE IF EXISTS user ;";
        session.createSQLQuery(sql).executeUpdate();


        try{
            transaction.commit();
        }catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User(name , lastName , age);

        session.save(user);

        try{
            transaction.commit();
        }catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = (User) session.load(User.class,id);
        session.delete(user);


        try{
            transaction.commit();
        }catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "SELECT * FROM user;";

        users = session.createSQLQuery (sql).list();

        try{
            transaction.commit();
        }catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        finally {
            session.close();
        }

        return  users;

    }

    @Override
    public void cleanUsersTable() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        String sql = "TRUNCATE TABLE user ;";
        session.createSQLQuery(sql).executeUpdate();


        try{
            transaction.commit();
        }catch (HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
    }


}
