package personal.model;

import java.util.List;

public interface Repository {
    List<User> getAllUsers();
    Integer CreateUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    void changeSeparator(String separator);

//    String getSeparator();

}
