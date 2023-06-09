package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            try {
                String command = prompt("Введите команду: ");
                com = Commands.valueOf(command.toUpperCase());

                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE:
                        create();
                        break;
                    case READ:
                        read();

                        break;
                    case LIST:
                        list();
                        break;
                    case UPDATE:
                        updateUser();
                        break;
                    case DELETE:
                        delete();
                        break;
                    case SEPARATOR:
                        changeSeparator();
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void changeSeparator() {
        String separators = ",;: *#";
        String separator = prompt("Введите новый разделитель: ");
        if(!separators.contains(separator)){
            System.out.println("Введен неверный разделитель. Допустимые разделители: "+separators);
        }else{
            userController.changeSeparator(separator);
        }
    }

    private void updateUser() throws Exception {
        Integer id = Integer.parseInt(prompt("Идентификатор пользователя: "));
        User user = userController.readUser(id);
        System.out.println(user);
        System.out.println();
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        userController.updateUser(new User(id, firstName, lastName, phone));
    }

    private void create() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        userController.saveUser(new User(firstName, lastName, phone));
    }

    private void read() throws Exception {
        Integer id = Integer.parseInt(prompt("Идентификатор пользователя: "));
        User user = userController.readUser(id);
        System.out.println(user);
    }

    private void list() {
        List<User> allUsers = userController.allUsers();
        for (User user : allUsers) {
            System.out.println(user);
            System.out.println();
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private void delete() throws Exception {
        Integer id = Integer.parseInt(prompt("Идентификатор пользователя: "));
        User user = userController.readUser(id);
        System.out.println(user);
        System.out.println();
        String confirm = prompt("Вы уверены? (Y/N): ");
        if (confirm.toUpperCase().equals("Y")){
            userController.deleteUser(user);
            System.out.println("Пользователь удален");
        }
    }


}
