package example.com.user;

import java.util.ArrayList;
import java.util.List;

public class UserService{
    private List<User> listOfUsers = new ArrayList<>();
    private static UserService userService;

    UserService(){

    }

    public static UserService getUserService(){
        if(userService == null){
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getListOfUsers(){
        return listOfUsers;
    }

    public void saveUser(User user){
        listOfUsers.add(user);
    }

    public User getUser(String email) {
        return listOfUsers.stream()
                          .filter(user -> user.getEmail().equals(email))
                          .findFirst()
                          .orElse(null);
    }
}