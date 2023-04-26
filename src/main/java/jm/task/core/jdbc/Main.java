package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Petr", "Petrov", (byte) 11);
        userService.saveUser("Sidor", "Sidorov", (byte) 22);
        userService.saveUser("Alexey", "Alexeev", (byte) 33);
        userService.saveUser("Maxim", "Maximov", (byte) 44);

        System.out.println(userService.getAllUsers().toString());

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
