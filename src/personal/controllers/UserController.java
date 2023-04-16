package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {

    private final Validat valid = new Validat();
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        valid.validate(user);
        repository.CreateUser(user);
    }

    public User readUser(Integer userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }

        throw new Exception("User not found");
    }

    public List<User> allUsers() {
        return repository.getAllUsers();

    }
    public void updateUser(User user){
        valid.validate(user);
        repository.updateUser(user);
    }

    public void deleteUser(User user){
       // valid.validate(user);
        repository.deleteUser(user);
    }


    public void changeSeparator(String separator) {
        repository.changeSeparator(separator);
    }
}
