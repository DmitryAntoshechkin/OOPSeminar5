package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;

    private String separator;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers() {
        separator = fileOperation.getSeparator();
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.map(line, separator));
        }
        return users;
    }

    @Override
    public Integer CreateUser(User user) {

        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = item.getId();
            if (max < id) {
                max = id;
            }
        }
        int id = max + 1;
        user.setId(id);
        users.add(user);
        saveUser(users);
        return id;
    }

    private void saveUser(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User item : users) {
            lines.add(mapper.map(item, separator));
        }
        fileOperation.saveAllLines(lines);
    }

    public void updateUser(User user) {
        List<User> users = getAllUsers();
        for (User item : users) {
            if (item.getId().equals(user.getId())) {
                item.setFirstName(user.getFirstName());
                item.setLastName(user.getLastName());
                item.setPhone(user.getPhone());
            }
        }
        saveUser(users);
    }

    public void deleteUser(User user) {
        List<User> users = getAllUsers();
        int removeIndex = -1;
        for (User item : users) {
            if (item.getId() == user.getId()) {
                removeIndex = users.indexOf(item);
            } else if (item.getId() > (user.getId())) {
                item.setId(item.getId() - 1);
            }
        }
        users.remove(removeIndex);
        saveUser(users);

    }

    @Override
    public void changeSeparator(String separator) {
        List<User> users = getAllUsers();
        this.separator = separator;
        saveUser(users);
        fileOperation.saveSeparator(separator);
    }


}
