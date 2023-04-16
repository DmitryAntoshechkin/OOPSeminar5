package personal.model;

public class UserMapper {
    public String map(User user, String separator) {
        return String.format("%d%s%s%s%s%s%s", user.getId(), separator,  user.getFirstName(), separator, user.getLastName(), separator, user.getPhone());
    }

    public User map(String line, String separator) {
        String[] lines = line.split(separator);
        return new User(Integer.parseInt(lines[0]), lines[1], lines[2], lines[3]);
    }
}

