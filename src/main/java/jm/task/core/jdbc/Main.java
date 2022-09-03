package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {

    private final static UserService userService = new UserServiceImpl();


    public static void main(String[] args) {
        // реализуйте алгоритм здесь
      //  Util util = new Util();
      //  util.getConnection();

        userService.createUsersTable();

        userService.saveUser("Алексей", "Владыкин", (byte) 37);
        userService.saveUser("Заур", "Трегулов", (byte) 35);
        userService.saveUser("Герман", "Севостьянов", (byte) 26);
        userService.saveUser("Наиль", "Алишев", (byte) 26);

        userService.removeUserById(1);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
