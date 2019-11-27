package objects;

public class UserSession {
    public static User CurrentUser = new User("");
    public UserSession(){
    }
    public void setCurrentUser(User user){
        this.CurrentUser = user; 
    }
    public void resetSession(){
        this.CurrentUser = new User("");
    }
}

