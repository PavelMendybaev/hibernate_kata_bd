package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.sql.SQLException;

public class Main {

    private static  UserService service = new UserServiceImpl();

    private static UserDao dao = new UserDaoHibernateImpl();

    public static void main(String[] args) throws SQLException {

        startHibernate();
    }
    public static void startHibernate() {

        dao.createUsersTable();
        dao.saveUser("имя" , "фамилия" , (byte)10);
        dao.removeUserById(1);
        dao.cleanUsersTable();
        dao.dropUsersTable();
        System.out.println("Успешно");

    }
}
