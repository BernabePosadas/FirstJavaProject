package objects;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.*;
import java.io.*;

public class ProfileViewer extends JFrame implements ActionListener {

    private final UserSession UserSession = new UserSession();
    private final ImageIcon background = new ImageIcon("ProfileBack.png");
    private final ImageIcon Handler1 = new ImageIcon("HandlerForUsername.png");
    private final ImageIcon Handler2 = new ImageIcon("HandlerForUserInfo.png");
    private final ImageIcon Handler3 = new ImageIcon("PostHandler.png");
    private final ImageIcon Handler4 = new ImageIcon("SocialStatusHandler.png");
    private final ImageIcon Handler5 = new ImageIcon("OptionsHandler.png");
    private final ImageIcon icon = new ImageIcon("icon.png");
    private ImageIcon proPic;
    private final JLabel pic = new JLabel(proPic);
    private final JLabel OptionsHandler = new JLabel(Handler5);
    private final JLabel SocialStatusHandler = new JLabel(Handler4);
    private final JLabel PostWallHandler = new JLabel(Handler3);
    private final JLabel UserInfoHandler = new JLabel(Handler2);
    private final JLabel UsernameHandler = new JLabel(Handler1);
    private final JLabel back = new JLabel(background);
    private final JLabel ActionLbl = new JLabel("More Options: ");
    private final JLabel SearchUserlbl = new JLabel("User Search");
    private final JLabel fullnamelbl1 = new JLabel("Fullname:");
    private final JLabel usernamelbl1 = new JLabel("Username:");
    private final JLabel fullnamelbl2 = new JLabel("Fullname: ");
    private final JLabel usernamelbl2 = new JLabel("Username: ");
    private final JLabel genderlbl = new JLabel("Gender: ");
    private final JLabel birthdatelbl = new JLabel("Birthdate: ");
    private final JLabel addresslbl = new JLabel("Address: ");
    private final JLabel fullname = new JLabel("<null>");
    private final JLabel username = new JLabel("<null>");
    private final JLabel InfoLbl = new JLabel("User Info:");
    private final JLabel full = new JLabel("<null>");
    private final JLabel user = new JLabel("<null>");
    private final JLabel gender = new JLabel("<null>");
    private final JLabel birthDate = new JLabel("<null>");
    private final JLabel address = new JLabel("<null>");
    private final JLabel Postlbl = new JLabel("Post Wall");
    private final JLabel Friendslbl = new JLabel("Friends");
    private final JLabel Requestlbl = new JLabel("Friend Request");
    private final JTextField searchUser = new JTextField();
    private final JTextField post = new JTextField();
    private final DefaultListModel mod = new DefaultListModel();
    private final JList postArea = new JList(mod);
    private final JScrollPane scr = new JScrollPane(postArea);
    private final DefaultListModel friendMod = new DefaultListModel();
    private final JList friends = new JList(friendMod);
    private final JScrollPane friendScr = new JScrollPane(friends);
    private final DefaultListModel requestMod = new DefaultListModel();
    private final JList requests = new JList(requestMod);
    private final JScrollPane requestScr = new JScrollPane(requests);
    private final JButton btnPost = new JButton("Post");
    private final JButton btnEdit = new JButton("Edit Profile");
    private final JButton btnLogOut = new JButton("Log Out");
    private final JButton btnSearch = new JButton("Search User");
    private final JButton btnAccept = new JButton("Accept");
    private final JButton btnReject = new JButton("Reject");
    private final JButton btnView = new JButton("View Profile");
    private final JButton btnRemove = new JButton("Remove");
    private final JButton btnAdd = new JButton("Add Friend");
    private final JButton home = new JButton("Home");
    private final JButton cancelRequest = new JButton("Cancel Request");
    private final JButton globalChat = new JButton("Enter Global Chat");
    private final JButton removeFriend2 = new JButton("Remove Friend");

    public ProfileViewer(User user) {
        this.setTitle("Hunter's Guild: Profile Viewer");
        this.setSize(1330, 715);
        this.setLocation(20, 10);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.content();
        this.loadInfo(user);
        setIconImage(this.icon.getImage());
        setVisible(true);
    }

    private void loadInfo(User ViewUser) {
        ViewUser.buildUser();
        fullname.setText(ViewUser.FullName);
        full.setText(ViewUser.FullName);
        user.setText(ViewUser.UserID);
        username.setText(ViewUser.UserID);
        gender.setText(ViewUser.Gender);
        birthDate.setText(ViewUser.BirthDay);
        address.setText(ViewUser.Address);
        proPic = new ImageIcon(new ImageIcon(ViewUser.ImagePath).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        pic.setIcon(proPic);
        pic.setBounds(40, 40, 100, 100);
        getContentPane().add(pic);
        this.setBoardContents(ViewUser);
        if (UserSession.CurrentUser.UserID.equals(ViewUser.UserID)) {
            this.prepareComponentCurrentUser();
        } else {
            this.prepareComponentViewingUser();
             String getGender = "";
             boolean areFriends = ViewUser.checkIfFriends(this.UserSession.CurrentUser.UserID);
            if (areFriends) {
                post.setVisible(true);
                postArea.setVisible(true);
                birthDate.setVisible(true);
                address.setVisible(true);
                btnPost.setVisible(true);
                btnAdd.setVisible(false);
                cancelRequest.setVisible(false);
                removeFriend2.setVisible(true);
            } else {
                if (gender.getText().equalsIgnoreCase("Male")) {
                    getGender = "his";
                } else {
                    getGender = "her";
                }
                if (ViewUser.checkIfHasPendingRequest(this.UserSession.CurrentUser.UserID)) {
                    btnAdd.setVisible(false);
                    cancelRequest.setVisible(true);
                }
            }
            Requestlbl.setVisible(false);
            requestScr.setVisible(false);
            btnAccept.setVisible(false);
            btnReject.setVisible(false);
            btnRemove.setVisible(false);
            Friendslbl.setBounds(950, 55, 150, 20);
            friendScr.setBounds(950, 75, 350, 575);
            btnView.setBounds(1060, 650, 140, 20);
            getContentPane().add(friendScr);
            getContentPane().add(Friendslbl);
            getContentPane().add(btnView);
            if (!areFriends) {
                JOptionPane.showMessageDialog(null, "I'm sorry but this Hunter's profile is classified please request for " + getGender + " Guild card", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        this.loadBackgrounds();
    }
    private void prepareComponentCurrentUser(){
        btnAdd.setVisible(false);
            cancelRequest.setVisible(false);
            Requestlbl.setVisible(true);
            requestScr.setVisible(true);
            btnEdit.setVisible(true);
            btnAccept.setVisible(true);
            btnReject.setVisible(true);
            btnRemove.setVisible(true);
            post.setVisible(true);
            postArea.setVisible(true);
            birthDate.setVisible(true);
            address.setVisible(true);
            btnPost.setVisible(true);
            removeFriend2.setVisible(false);

            Requestlbl.setBounds(950, 55, 150, 20);
            requestScr.setBounds(950, 75, 345, 255);
            btnAccept.setBounds(990, 330, 140, 20);
            btnReject.setBounds(1130, 330, 140, 20);
            Friendslbl.setBounds(950, 360, 150, 20);
            friendScr.setBounds(950, 380, 350, 270);
            btnView.setBounds(990, 650, 140, 20);
            btnRemove.setBounds(1130, 650, 140, 20);
            btnAdd.setBounds(150, 110, 100, 20);
            cancelRequest.setBounds(150, 110, 130, 20);
            removeFriend2.setBounds(150, 110, 130, 20);
            getContentPane().add(Requestlbl);
            getContentPane().add(requestScr);
            getContentPane().add(btnAccept);
            getContentPane().add(btnReject);
            getContentPane().add(Friendslbl);
            getContentPane().add(friendScr);
            getContentPane().add(btnView);
            getContentPane().add(btnRemove);
            getContentPane().add(btnAdd);
            getContentPane().add(cancelRequest);
            getContentPane().add(removeFriend2);
    }
    private void prepareComponentViewingUser(){
            btnAdd.setVisible(true);
            btnEdit.setVisible(false);
            cancelRequest.setVisible(false);
            removeFriend2.setVisible(false);
            post.setVisible(false);
            postArea.setVisible(false);
            birthDate.setVisible(false);
            address.setVisible(false);
            btnPost.setVisible(false);
    }
    private void setBoardContents(User user) {
        mod.removeAllElements();
        if (user.Post != null) {
            for (String Post : user.Post) {
                mod.addElement(Post);
            }
        }
        friendMod.removeAllElements();
        if (user.Friends != null) {
            for (String Friends : user.Friends) {
                friendMod.addElement(Friends);
            }
        }
        requestMod.removeAllElements();
        if (user.Requests != null) {
            for (String request : user.Requests) {
                requestMod.addElement(request);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPost) {
            String getPost = post.getText();
            if (getPost.equals("")) {
                JOptionPane.showMessageDialog(null, "Posting Failed: Empty Post", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            } else {
                SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/YY hh:mm");
                Calendar cld = Calendar.getInstance();
                mod.insertElementAt("[" + fmt.format(cld.getTime()) + "] " + UserSession.CurrentUser.UserID + ": " + getPost, 0);
                String getAllPost = "";
                for (int i = 0; i < mod.size(); i++) {
                    getAllPost += mod.getElementAt(i) + "\n";
                }
                IOFileStream io = new IOFileStream();
                io.savePost(this.user.getText(), getAllPost);
            }
            post.setText("");

        } else if (e.getSource() == btnEdit) {
            PasswordConfirmation passconfirm = new PasswordConfirmation();
            if (passconfirm.PasswordConfirmationReply) {
                new EditProfile();
                this.loadInfo(this.UserSession.CurrentUser);
            }

        } else if (e.getSource() == btnLogOut) {
            this.UserSession.resetSession();
            JOptionPane.showMessageDialog(null, "You Log Out Successfully. Thank You For Staying", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
            new LogInWin();
            dispose();
        } else if (e.getSource() == btnSearch) {
            File f = new File("users/" + searchUser.getText());
            if (f.exists() && !searchUser.getText().trim().equals("")) {
                User ViewUser = new User(searchUser.getText().substring(0, 1).toUpperCase() + searchUser.getText().substring(1).toLowerCase());
                this.loadInfo(ViewUser);
                searchUser.setText("");
            } else if (searchUser.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Operation Failed: Search Box Is Empty", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Username Not Found. Please Check It Carefully", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnView) {
            if (friends.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "Operation Failed: Nothing Is Selected", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            } else {
                User ViewUser = new User(friends.getSelectedValue().toString());
                this.loadInfo(ViewUser);
            }
        } else if (e.getSource() == btnRemove) {
            if (friends.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "Operation Failed: Nothing Is Selected", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Do You Want To Remove The Selected Person?", "Hunter's Guild", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    IOFileStream io = new IOFileStream();
                    io.RemoveFriendStatus(friends.getSelectedValue().toString(), this.UserSession.CurrentUser.UserID);
                    friendMod.remove(friends.getSelectedIndex());
                    String getAllFriends = "";
                    for (int i = 0; i < friendMod.size(); i++) {
                        getAllFriends += friendMod.getElementAt(i) + "\n";
                    }
                    io.saveFriends(this.UserSession.CurrentUser.UserID, getAllFriends);
                    JOptionPane.showMessageDialog(null, "Removed Successfully", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (e.getSource() == btnAdd) {
            requestMod.addElement(this.UserSession.CurrentUser.UserID);
            String getAllRequest = "";
            for (int i = 0; i < requestMod.size(); i++) {
                getAllRequest += requestMod.getElementAt(i) + "\n";
            }
            IOFileStream io = new IOFileStream();
            io.saveRequest(this.user.getText(), getAllRequest);
            btnAdd.setVisible(false);
            cancelRequest.setVisible(true);
            JOptionPane.showMessageDialog(null, "Friend Request sent", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == btnAccept) {
            if (requests.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "Operation Failed: Nothing Is Selected", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            } else {
                String getAcceptedUsername = requests.getSelectedValue().toString();
                boolean isNotYetAdded = true;
                for (int i = 0; i < friendMod.size(); i++) {
                    if (friendMod.getElementAt(i).equals(getAcceptedUsername)) {
                        isNotYetAdded = false;
                    }
                }
                if (isNotYetAdded) {
                    friendMod.addElement(getAcceptedUsername);
                    String getAllFriends = "";
                    for (int i = 0; i < friendMod.size(); i++) {
                        getAllFriends += friendMod.getElementAt(i) + "\n";
                    }
                    requestMod.remove(requests.getSelectedIndex());
                    String getAllRequest = "";
                    if (requestMod.size() != 0) {
                        for (int i = 0; i < requestMod.size(); i++) {
                            getAllRequest += requestMod.getElementAt(i) + "\n";
                        }
                    }
                    IOFileStream io = new IOFileStream();
                    io.saveRequest(this.UserSession.CurrentUser.UserID, getAllRequest);
                    io.saveFriends(this.UserSession.CurrentUser.UserID, getAllFriends);
                    io.saveAcceptedUsername(getAcceptedUsername, this.UserSession.CurrentUser.UserID);
                    JOptionPane.showMessageDialog(null, "You Are Now Friends With " + getAcceptedUsername, "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Operation Failed: You Already Friends With " + getAcceptedUsername, "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
                    requestMod.remove(requests.getSelectedIndex());
                    String getAllRequest = "";
                    if (requestMod.size() != 0) {
                        for (int i = 0; i < requestMod.size(); i++) {
                            getAllRequest += requestMod.getElementAt(i) + "\n";
                        }
                    }
                    IOFileStream io = new IOFileStream();
                    io.saveRequest(this.UserSession.CurrentUser.UserID, getAllRequest);
                }

            }

        } else if (e.getSource() == btnReject) {
            if (requests.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "Operation Failed: Nothing Is Selected", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            } else {
                requestMod.remove(requests.getSelectedIndex());
                String getAllRequest = "";
                if (requestMod.size() != 0) {
                    for (int i = 0; i < requestMod.size(); i++) {
                        getAllRequest += requestMod.getElementAt(i) + "\n";
                    }
                }
                IOFileStream io = new IOFileStream();
                io.saveRequest(this.UserSession.CurrentUser.UserID, getAllRequest);
                JOptionPane.showMessageDialog(null, "Rejected Successfully", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == home) {
            loadInfo(this.UserSession.CurrentUser);
        } else if (e.getSource() == cancelRequest) {
            String getUsername = this.UserSession.CurrentUser.UserID;
            boolean isFound = false;
            int getIndex = 0;
            for (int i = 0; i < requestMod.size(); i++) {
                if (requestMod.getElementAt(i).equals(getUsername)) {
                    getIndex = i;
                    isFound = true;
                    break;
                } else {
                    isFound = false;
                }
            }
            if (isFound) {
                requestMod.remove(getIndex);
                String getAllRequest = "";
                if (requestMod.size() != 0) {
                    for (int i = 0; i < requestMod.size(); i++) {
                        getAllRequest += requestMod.getElementAt(i) + "\n";
                    }
                }
                IOFileStream io = new IOFileStream();
                io.saveRequest(this.user.getText(), getAllRequest);
                JOptionPane.showMessageDialog(null, "Canceled Successfully", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
                cancelRequest.setVisible(false);
                btnAdd.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Operation Failed", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == globalChat) {
            new GlobalChat();
            dispose();
        } else if (e.getSource() == removeFriend2) {
            if (JOptionPane.showConfirmDialog(null, "Do You Want To Unfiend " + this.user.getText() + "?", "Hunter's Guild", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String getUsername = this.UserSession.CurrentUser.UserID;
                boolean isFound = false;
                int getIndex = 0;
                for (int i = 0; i < friendMod.size(); i++) {
                    if (friendMod.getElementAt(i).equals(getUsername)) {
                        getIndex = i;
                        isFound = true;
                        break;
                    } else {
                        isFound = false;
                    }
                }
                if (isFound) {
                    friendMod.remove(getIndex);
                    String getAllFriends = "";
                    for (int i = 0; i < friendMod.size(); i++) {
                        getAllFriends = friendMod.getElementAt(i) + "\n";
                    }
                    IOFileStream io = new IOFileStream();
                    io.saveFriends(this.user.getText(), getAllFriends);
                    io.RemoveFriendStatus(this.UserSession.CurrentUser.UserID, this.user.getText());
                    JOptionPane.showMessageDialog(null, "Removed Successfully", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
                    this.ReloadPage();

                } else {
                    JOptionPane.showMessageDialog(null, "Operation Failed", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    private void ReloadPage() {
        User ViewUser = new User(this.user.getText());
        loadInfo(ViewUser);
    }

    private void loadBackgrounds() {
        Container con = getContentPane();
        con.add(OptionsHandler);
        con.add(SocialStatusHandler);
        con.add(PostWallHandler);
        con.add(UserInfoHandler);
        con.add(UsernameHandler);
        con.add(back);
    }

    private void content() {
        Container con = getContentPane();
        con.setLayout(null);
        Font FullnameFont = new Font("Comic Sans MS", Font.PLAIN, 16);
        Font bannerFont = new Font("Comic Sans MS", Font.BOLD, 14);
        Font OptionFont = new Font("Comic Sans MS", Font.BOLD, 16);
        SearchUserlbl.setFont(OptionFont);
        Postlbl.setFont(bannerFont);
        InfoLbl.setFont(bannerFont);
        Friendslbl.setFont(bannerFont);
        Requestlbl.setFont(bannerFont);
        ActionLbl.setFont(OptionFont);
        fullnamelbl1.setBounds(150, 40, 100, 30);
        fullnamelbl1.setFont(FullnameFont);
        usernamelbl1.setBounds(350, 40, 100, 30);
        usernamelbl1.setFont(FullnameFont);
        fullname.setBounds(150, 70, 220, 30);
        fullname.setFont(FullnameFont);
        username.setBounds(350, 70, 220, 30);
        username.setFont(FullnameFont);
        InfoLbl.setBounds(55, 185, 100, 30);
        fullnamelbl2.setBounds(55, 215, 100, 30);
        full.setBounds(155, 215, 400, 30);
        usernamelbl2.setBounds(55, 245, 100, 30);
        user.setBounds(155, 245, 400, 30);
        genderlbl.setBounds(55, 275, 100, 30);
        gender.setBounds(155, 275, 200, 30);
        birthdatelbl.setBounds(55, 305, 100, 30);
        birthDate.setBounds(155, 305, 300, 30);
        addresslbl.setBounds(55, 335, 100, 30);
        address.setBounds(155, 335, 400, 30);
        Postlbl.setBounds(520, 50, 100, 20);
        post.setBounds(520, 70, 300, 25);
        btnPost.setBounds(820, 70, 80, 25);
        scr.setBounds(520, 100, 380, 580);
        btnEdit.setBounds(55, 375, 100, 20);
        btnLogOut.setBounds(330, 590, 100, 20);
        searchUser.setBounds(60, 510, 270, 20);
        btnSearch.setBounds(330, 510, 120, 20);
        home.setBounds(60, 590, 100, 20);
        back.setBounds(0, 0, this.getWidth(), this.getHeight());
        UsernameHandler.setBounds(30, 30, 460, 120);
        UserInfoHandler.setBounds(30, 160, 460, 280);
        PostWallHandler.setBounds(500, 30, 420, 660);
        SocialStatusHandler.setBounds(930, 30, 390, 660);
        OptionsHandler.setBounds(30, 450, 460, 230);
        ActionLbl.setBounds(60, 560, 150, 20);
        SearchUserlbl.setBounds(60, 480, 100, 20);
        globalChat.setBounds(170, 590, 150, 20);

        btnPost.addActionListener(this);
        btnEdit.addActionListener(this);
        btnLogOut.addActionListener(this);
        btnSearch.addActionListener(this);
        btnView.addActionListener(this);
        btnRemove.addActionListener(this);
        btnAdd.addActionListener(this);
        btnAccept.addActionListener(this);
        btnReject.addActionListener(this);
        home.addActionListener(this);
        cancelRequest.addActionListener(this);
        globalChat.addActionListener(this);
        removeFriend2.addActionListener(this);

        con.add(fullname);
        con.add(username);
        con.add(InfoLbl);
        con.add(full);
        con.add(user);
        con.add(gender);
        con.add(birthDate);
        con.add(address);
        con.add(Postlbl);
        con.add(post);
        con.add(btnPost);
        con.add(scr);
        con.add(btnEdit);
        con.add(btnLogOut);
        con.add(searchUser);
        con.add(btnSearch);
        con.add(Requestlbl);
        con.add(requestScr);
        con.add(btnAccept);
        con.add(btnReject);
        con.add(Friendslbl);
        con.add(friendScr);
        con.add(btnView);
        con.add(btnRemove);
        con.add(btnAdd);
        con.add(fullnamelbl1);
        con.add(usernamelbl1);
        con.add(fullnamelbl2);
        con.add(usernamelbl2);
        con.add(genderlbl);
        con.add(birthdatelbl);
        con.add(addresslbl);
        con.add(home);
        con.add(ActionLbl);
        con.add(SearchUserlbl);
        con.add(globalChat);
    }

}
