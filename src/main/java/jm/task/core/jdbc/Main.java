package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

       userService.saveUser("Влад", "Влад", (byte) 5);
       userService.saveUser("Коля", "Коля", (byte) 6);
       userService.saveUser("Илья", "Илья", (byte) 7);
       userService.saveUser("Даня", "Даня", (byte) 8);

     //   System.out.println(userService.getAllUsers());

        List<User> users = userService.getAllUsers();
        for( User user : users){
            System.out.println(user);
        }


        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
