package personal.model;

import java.util.ArrayList;
import java.util.List;

public class NewRepositoryFile implements Repository {
    private NewMapper newMapper = new NewMapper();
    private FileOperation fileOperation;

    public NewRepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(newMapper.map(line));
        }
        return users;
    }

    @Override
    public String CreateUser(User user) {

        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        saveUsers(users);
        return id;
    }

    private void saveUsers(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User item  : users) {
          lines.add(newMapper.map(item));  
        }
        fileOperation.saveAllLines(lines);
    }

    @Override
    public User updateUser(User user) throws Exception {
        List<User> users = getAllUsers();
        User foundUser = findUserById(users, user.getId());
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setPhone(user.getPhone());
        saveUsers(users);
        return foundUser;
    }

    private User findUserById(List<User> users, String userId) throws Exception {
        for (User user : users) {
           if (user.getId().equals(userId)) {
            return user;
           } 
        }
        throw new Exception("User not found");
    }

    @Override
    public User readUser(String userId) throws Exception {
        List<User> users = this.getAllUsers();
        for (User user : users) {
           if (user.getId().equals(userId)) {
            return user;
           } 
        }
        throw new Exception("User not found");
    }

   
}
