package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

       userService.saveUser("Влад" , "Влад" , (byte) 5);
       userService.saveUser("Коля" , "Коля" , (byte) 6);
       userService.saveUser("Илья " , "Илья" , (byte) 7);
       userService.saveUser("Даня" , "Даня" , (byte) 8);

     //   System.out.println(userService.getAllUsers());

        List<User> users = userService.getAllUsers();
        for( User r : users){
            System.out.println(r);
        }


        userService.cleanUsersTable();

        userService.dropUsersTable();


        // реализуйте алгоритм здесь
    }
}
