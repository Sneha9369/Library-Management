package manager;
import java.util.ArrayList;
import model.User;


public class LoginManager {

    public User login(String u,String p,ArrayList<User> users)
    {
        for (User user : users) {
    String tempUserName = user.getUsername();
    String tempPassword = user.getPassword();
    if (tempUserName.equals(u) && tempPassword.equals(p)) {
        return user;
    }
}

        return null;
    }
    
}
