package objects;

import javax.swing.JOptionPane;

/**
 * User Model
 *
 * This represent the User Entity.
 *
 * @author Bernabe Posadas
 */
public class User {

    public String UserID;
    public String ImagePath;
    public String FullName;
    public String Gender;
    public String Address;
    public String BirthDay;
    //Relationship to other object
    public String[] Post;
    public String[] Friends;
    public String[] Requests;

    public User(String UserID) {
        this.UserID = UserID;
    }

    public void buildUser() {
        if (!this.UserID.equals("")) {
            IOFileStream io = new IOFileStream();
            String[] Info = io.getInfo(this.UserID);
            this.ImagePath = io.getImagePath(this.UserID);
            if (this.ImagePath == null) {
                this.ImagePath = "DefaultProfilePic.png";
            } else {
                if (io.checkIfExists(this.ImagePath) == false) {
                    this.ImagePath = "DefaultProfilePic.png";
                    io.savePath(this.UserID, "DefaultProfilePic.png");
                }
            }
            if (Info == null) {
                JOptionPane.showMessageDialog(null, "Error: Failed to fetch user info.", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            } else {
                this.FullName = Info[0];
                this.Gender = Info[1];
                this.BirthDay = Info[2];
                this.Address = Info[3];
            }
            this.Post = io.getPosts(this.UserID);
            this.Friends = io.getFriends(this.UserID);
            this.Requests = io.getRequest(this.UserID);
        }
    }

    public boolean checkIfFriends(String ID) {
        for (String friends : this.Friends) {
            if (friends.equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfHasPendingRequest(String UserID) {
        for (String requests : this.Requests) {
            if (requests.equals(UserID)) {
                return true;
            }
        }
        return false;
    }

    public boolean ValidateUserObject() {
        if (this.FullName.equals("")) {
            JOptionPane.showMessageDialog(null, "Fullname is empty. Please specify your name", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (this.UserID.equals("")) {
            JOptionPane.showMessageDialog(null, "Username is empty. Please specify your username", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (this.Gender.equals("")) {
            JOptionPane.showMessageDialog(null, "No gender is selected. Please specify your geneder", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.Address.equals("")){
            this.Address = "Not specified";
        }
        return true;
    }
}
