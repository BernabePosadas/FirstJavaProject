package objects;

public class UserSession {
    public static User CurrentUser = new User("");
    public void setCurrentUser(User user){
        UserSession.CurrentUser = user; 
    }
    public void resetSession(){
        UserSession.CurrentUser = new User("");
    }
}

