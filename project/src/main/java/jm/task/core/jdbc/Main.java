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
        //startJDBC();
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




    public static void startJDBC() throws SQLException {
        service.createUsersTable();

        service.saveUser("pavel" , "petrov" , (byte)12);
        service.saveUser("sasha" , "petro2" , (byte)13);
        service.saveUser("pavel" , "petrov3" , (byte)14);
        service.saveUser("pavel" , "petrov4" , (byte)75);
        for(User user : service.getAllUsers()){
            System.out.println(user.toString());
        }
        service.cleanUsersTable();
        service.dropUsersTable();
        System.out.println("всё прошло");
    }



}
