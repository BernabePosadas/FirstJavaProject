
import Objects.ProgramUser;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.*;
import java.io.*;

public class ProfileViewer extends JFrame implements ActionListener {

    ImageIcon background = new ImageIcon("ProfileBack.png");
    ImageIcon Handler1 = new ImageIcon("HandlerForUsername.png");
    ImageIcon Handler2 = new ImageIcon("HandlerForUserInfo.png");
    ImageIcon Handler3 = new ImageIcon("PostHandler.png");
    ImageIcon Handler4 = new ImageIcon("SocialStatusHandler.png");
    ImageIcon Handler5 = new ImageIcon("OptionsHandler.png");
    ImageIcon proPic = new ImageIcon();
    JLabel pic = new JLabel(proPic);
    ;
	JLabel OptionsHandler = new JLabel(Handler5);
    JLabel SocialStatusHandler = new JLabel(Handler4);
    JLabel PostWallHandler = new JLabel(Handler3);
    JLabel UserInfoHandler = new JLabel(Handler2);
    JLabel UsernameHandler = new JLabel(Handler1);
    JLabel back = new JLabel(background);
    JLabel ActionLbl = new JLabel("More Options: ");
    JLabel SearchUserlbl = new JLabel("User Search");
    JLabel fullnamelbl1 = new JLabel("Fullname:");
    JLabel usernamelbl1 = new JLabel("Username:");
    JLabel fullnamelbl2 = new JLabel("Fullname: ");
    JLabel usernamelbl2 = new JLabel("Username: ");
    JLabel genderlbl = new JLabel("Gender: ");
    JLabel birthdatelbl = new JLabel("Birthdate: ");
    JLabel addresslbl = new JLabel("Address: ");
    JLabel fullname = new JLabel("<null>");
    JLabel username = new JLabel("<null>");
    JLabel InfoLbl = new JLabel("User Info:");
    JLabel full = new JLabel("<null>");
    JLabel user = new JLabel("<null>");
    JLabel gender = new JLabel("<null>");
    JLabel birthDate = new JLabel("<null>");
    JLabel address = new JLabel("<null>");
    JLabel Postlbl = new JLabel("Post Wall");
    JLabel Friendslbl = new JLabel("Friends");
    JLabel Requestlbl = new JLabel("Friend Request");
    JTextField searchUser = new JTextField();
    JTextField post = new JTextField();
    DefaultListModel mod = new DefaultListModel();
    JList postArea = new JList(mod);
    JScrollPane scr = new JScrollPane(postArea);
    DefaultListModel friendMod = new DefaultListModel();
    JList friends = new JList(friendMod);
    JScrollPane friendScr = new JScrollPane(friends);
    DefaultListModel requestMod = new DefaultListModel();
    JList requests = new JList(requestMod);
    JScrollPane requestScr = new JScrollPane(requests);
    JButton btnPost = new JButton("Post");
    JButton btnEdit = new JButton("Edit Profile");
    JButton btnLogOut = new JButton("Log Out");
    JButton btnSearch = new JButton("Search User");
    JButton btnAccept = new JButton("Accept");
    JButton btnReject = new JButton("Reject");
    JButton btnView = new JButton("View Profile");
    JButton btnRemove = new JButton("Remove");
    JButton btnAdd = new JButton("Add Friend");
    JButton home = new JButton("Home");
    JButton cancelRequest = new JButton("Cancel Request");
    JButton globalChat = new JButton("Enter Global Chat");
    JButton removeFriend2 = new JButton("Remove Friend");

    public ProfileViewer() {
        this.setTitle("Hunter's Guild: Profile Viewer");
        this.setSize(1330, 715);
        this.setLocation(20, 10);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        content();
        loadInfo();
        setIconImage(ProgramUser.icon.getImage());
        setVisible(true);
    }

    void loadInfo() {
        IOFileStream io = new IOFileStream();
        String get = ProgramUser.ViewingUser;
        String[] Info = io.getInfo(get);
        String path = io.getImagePath(get);
        if (Info == null) {
            JOptionPane.showMessageDialog(null, "Error: getInfo() Returned Null", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } else {
            boolean failedToLoad = false;
            String fn = Info[0];
            String un = ProgramUser.ViewingUser;
            String gen = Info[1];
            String bday = Info[2];
            String addr = Info[3];
            fullname.setText(fn);
            full.setText(fn);
            user.setText(un);
            username.setText(un);
            user.setText(un);
            gender.setText(gen);
            birthDate.setText(bday);
            address.setText(addr);
            if (path == null) {
                proPic = new ImageIcon("DefaultProfilePic.png");
            } else {
                if (io.checkIfExists(path) == false) {
                    failedToLoad = true;
                } else {
                    try {
                        proPic = new ImageIcon(path);
                    } catch (Exception ex) {
                        failedToLoad = true;
                    }
                }
            }
            if (failedToLoad) {
                proPic = new ImageIcon("DefaultProfilePic.png");
                io.savePath(ProgramUser.ViewingUser, "DefaultProfilePic.png");
            }
            pic.setIcon(proPic);
            pic.setBounds(40, 40, 100, 100);
            getContentPane().add(pic);
            if (ProgramUser.CurrentUser.equals(ProgramUser.ViewingUser)) {
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
                mod.removeAllElements();
                String[] getPosts = io.getPosts(ProgramUser.ViewingUser);
                if (getPosts != null) {
                    for (int i = 0; i < getPosts.length; i++) {
                        mod.addElement(getPosts[i]);
                    }
                }
                friendMod.removeAllElements();
                String[] getFriends = io.getFriends(ProgramUser.ViewingUser);
                if (getFriends != null) {
                    for (int i = 0; i < getFriends.length; i++) {
                        friendMod.addElement(getFriends[i]);
                    }
                }
                requestMod.removeAllElements();
                String[] getRequest = io.getRequest(ProgramUser.ViewingUser);
                if (getRequest != null) {
                    for (int i = 0; i < getRequest.length; i++) {
                        requestMod.addElement(getRequest[i]);
                    }
                }
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
                loadBackgrounds();
            } else {
                boolean areFriends = false;
                btnAdd.setVisible(true);
                btnEdit.setVisible(false);
                cancelRequest.setVisible(false);
                removeFriend2.setVisible(false);
                post.setVisible(false);
                postArea.setVisible(false);
                birthDate.setVisible(false);
                address.setVisible(false);
                btnPost.setVisible(false);
                mod.removeAllElements();
                String[] getPosts = io.getPosts(ProgramUser.ViewingUser);
                if (getPosts != null) {
                    for (int i = 0; i < getPosts.length; i++) {
                        mod.addElement(getPosts[i]);
                    }
                }
                friendMod.removeAllElements();
                String[] getFriends = io.getFriends(ProgramUser.ViewingUser);
                if (getFriends != null) {
                    for (int i = 0; i < getFriends.length; i++) {
                        friendMod.addElement(getFriends[i]);
                        if (getFriends[i].equalsIgnoreCase(ProgramUser.CurrentUser)) {
                            areFriends = true;
                            post.setVisible(true);
                            postArea.setVisible(true);
                            birthDate.setVisible(true);
                            address.setVisible(true);
                            btnPost.setVisible(true);
                            btnAdd.setVisible(false);
                            cancelRequest.setVisible(false);
                            removeFriend2.setVisible(true);
                        }

                    }
                }
                requestMod.removeAllElements();
                String[] getRequest = io.getRequest(ProgramUser.ViewingUser);
                if (getRequest != null) {
                    for (int i = 0; i < getRequest.length; i++) {
                        requestMod.addElement(getRequest[i]);
                        if (getRequest[i].equalsIgnoreCase(ProgramUser.CurrentUser)) {
                            btnAdd.setVisible(false);
                            if (areFriends) {
                                cancelRequest.setVisible(false);
                            } else {
                                cancelRequest.setVisible(true);
                            }
                        }
                    }
                }
                String getGender = "";
                if (gender.getText().equalsIgnoreCase("Male")) {
                    getGender = "his";
                } else {
                    getGender = "her";
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
                loadBackgrounds();
                if (areFriends == false) {
                    JOptionPane.showMessageDialog(null, "I'm sorry but this Hunter's profile is classified please request for " + getGender + " Guild card", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPost) {

            String getPost = post.getText();
            if (getPost.equals("")) {
                JOptionPane.showMessageDialog(null, "Posting Failed: Empty Post", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            } else {
                SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/YY hh:mm");
                Calendar cld = Calendar.getInstance();
                mod.insertElementAt("[" + fmt.format(cld.getTime()) + "] " + ProgramUser.CurrentUser + ": " + getPost, 0);
                String getAllPost = "";
                for (int i = 0; i < mod.size(); i++) {
                    getAllPost += mod.getElementAt(i) + "\n";
                }
                IOFileStream io = new IOFileStream();
                io.savePost(ProgramUser.ViewingUser, getAllPost);
            }
            post.setText("");

        } else if (e.getSource() == btnEdit) {
            new PasswordConfirmation();
            if (ProgramUser.PasswordConfirmationReply) {
                new EditProfile();
                loadInfo();
            }
            ProgramUser.PasswordConfirmationReply = false;

        } else if (e.getSource() == btnLogOut) {
            ProgramUser.CurrentUser = "";
            ProgramUser.ViewingUser = "";
            JOptionPane.showMessageDialog(null, "You Log Out Successfully. Thank You For Staying", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
            new LogInWin();
            dispose();
        } else if (e.getSource() == btnSearch) {
            File f = new File("users/" + searchUser.getText());
            if (f.exists() && !searchUser.getText().trim().equals("")) {
                ProgramUser.ViewingUser = searchUser.getText().substring(0, 1).toUpperCase() + searchUser.getText().substring(1).toLowerCase();
                searchUser.setText("");
                loadInfo();
            } else if (searchUser.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Operation Failed: Search Box Is Empty", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Username Not Found. Please Check It Carefully", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnView) {
            if (friends.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "Operation Failed: Nothing Is Selected", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            } else {
                ProgramUser.ViewingUser = friends.getSelectedValue().toString();
                loadInfo();
            }
        } else if (e.getSource() == btnRemove) {
            if (friends.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "Operation Failed: Nothing Is Selected", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            } else {
                if (JOptionPane.showConfirmDialog(null, "Do You Want To Remove The Selected Person?", "Hunter's Guild", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    IOFileStream io = new IOFileStream();
                    io.RemoveFriendStatus(friends.getSelectedValue().toString(), ProgramUser.CurrentUser);
                    friendMod.remove(friends.getSelectedIndex());
                    String getAllFriends = "";
                    for (int i = 0; i < friendMod.size(); i++) {
                        getAllFriends += friendMod.getElementAt(i) + "\n";
                    }
                    io.saveFriends(ProgramUser.CurrentUser, getAllFriends);
                    JOptionPane.showMessageDialog(null, "Removed Successfully", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        } else if (e.getSource() == btnAdd) {
            requestMod.addElement(ProgramUser.CurrentUser);
            String getAllRequest = "";
            for (int i = 0; i < requestMod.size(); i++) {
                getAllRequest += requestMod.getElementAt(i) + "\n";
            }
            IOFileStream io = new IOFileStream();
            io.saveRequest(ProgramUser.ViewingUser, getAllRequest);
            btnAdd.setVisible(false);
            cancelRequest.setVisible(true);
            JOptionPane.showMessageDialog(null, "Request Sent", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
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
                    io.saveRequest(ProgramUser.CurrentUser, getAllRequest);
                    io.saveFriends(ProgramUser.CurrentUser, getAllFriends);
                    io.saveAcceptedUsername(getAcceptedUsername, ProgramUser.CurrentUser);
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
                    io.saveRequest(ProgramUser.CurrentUser, getAllRequest);
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
                io.saveRequest(ProgramUser.CurrentUser, getAllRequest);
                JOptionPane.showMessageDialog(null, "Rejected Successfully", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == home) {
            ProgramUser.ViewingUser = ProgramUser.CurrentUser;
            loadInfo();
        } else if (e.getSource() == cancelRequest) {
            String getUsername = ProgramUser.CurrentUser;
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
                io.saveRequest(ProgramUser.ViewingUser, getAllRequest);
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
            if (JOptionPane.showConfirmDialog(null, "Do You Want To Unfiend " + ProgramUser.ViewingUser + "?", "Hunter's Guild", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String getUsername = ProgramUser.CurrentUser;
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
                    io.saveFriends(ProgramUser.ViewingUser, getAllFriends);
                    io.RemoveFriendStatus(ProgramUser.CurrentUser, ProgramUser.ViewingUser);
                    JOptionPane.showMessageDialog(null, "Removed Successfully", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
                    loadInfo();

                } else {
                    JOptionPane.showMessageDialog(null, "Operation Failed", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
                }
            } else {

            }
        }

    }

    void loadBackgrounds() {
        Container con = getContentPane();
        con.add(OptionsHandler);
        con.add(SocialStatusHandler);
        con.add(PostWallHandler);
        con.add(UserInfoHandler);
        con.add(UsernameHandler);
        con.add(back);
    }

    void content() {
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