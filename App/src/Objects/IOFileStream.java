package Objects;

import ConcreteImplementation.AESEncryptionProvider;
import java.awt.HeadlessException;
import ConcreteImplementation.SHA256ChecksumProvider;
import Interface.IEncryptionProvider;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class IOFileStream {

    LinkedList<String> stk = new LinkedList();
    IEncryptionProvider encipher;
    SHA256ChecksumProvider sha1;

    public IOFileStream() {
        EncryptionKeyClass EncryptionKey = new EncryptionKeyClass("Hunter's Guild", "Java Project");
        this.encipher = new AESEncryptionProvider(EncryptionKey);
        this.sha1 = new SHA256ChecksumProvider();
    }

    public boolean logIn(String user, String pw) {
        try {
            File f = new File("users/" + user);
            if (f.exists() && !user.trim().equals("")) {
                String data = decrypt(new File("users/" + user + "/pass.txt"));
                if (this.sha1.checkIfMatch(pw.getBytes(), data)) {
                    JOptionPane.showMessageDialog(null, "Log In Successfull", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else if (pw.trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Log In Failed. Password Box is Empty", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Log In Failed. Password Incorrect", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
                }
            } else if (!f.exists()) {
                JOptionPane.showMessageDialog(null, "Log In Failed. Username You Entered Does Not Exist In Our List Please Check It Carefully. Username Is Not Case Sensitive", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Log In Failed. Username Box is Empty", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Log In Failed. Exception Encountered: " + ex.getMessage(), "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        return false;
    }

    public void makeUser(String usern) {
        File f = new File("users/" + usern);
        f.mkdirs();
    }

    public boolean register(User user, String pass) {
        try {
            try (FileOutputStream wt = new FileOutputStream(new File("users/" + user.UserID + "/Info.txt"))) {
                String toEncrypt = user.FullName + "\r\n" + user.Gender + "\r\n" + user.BirthDay + "\r\n" + user.Address;
                wt.write(encryptData(toEncrypt));
                wt.flush();
            }
            try (FileOutputStream pw = new FileOutputStream(new File("users/" + user.UserID + "/pass.txt"))) {
                pw.write(encryptData(this.sha1.generateHashMessageDigestBase64(pass.getBytes())));
                pw.flush();
            }
            JOptionPane.showMessageDialog(null, "You Successfully Registered", "Hunter's Guild Registration Office", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (HeadlessException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Registration Failed. System thrown an exception : " + ex.getMessage(), "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private byte[] encryptData(String Message) {
        byte[] data = this.encipher.encrypt(Message.getBytes());
        if (data == null) {
            System.exit(-1);
        }
        return data;
    }

    private String decrypt(File f) {
        String data = "";
        try {
            FileInputStream inputStream = new FileInputStream(f);
            int size = (int) f.length();
            byte[] dataHold = new byte[size];
            inputStream.read(dataHold);
            data = new String(this.encipher.decrypt(dataHold));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        return data;
    }

    public String[] getInfo(String name) {
        String data = decrypt(new File("users/" + name + "/Info.txt"));
        Scanner in = new Scanner(data);
        String Info[] = new String[4];
        for (int i = 0; i < Info.length; i++) {
            Info[i] = in.nextLine();
        }
        return Info;
    }

    public void savePost(String user, String post) {
        File f = new File("users/" + user);
        try {
            try (FileOutputStream wt = new FileOutputStream(new File(f + "/post.txt"))) {
                wt.write(encryptData(post));
                wt.flush();
            }
            JOptionPane.showMessageDialog(null, "Posted Successfully", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Post Failed To Save: " + ex.getMessage(), "Hunter's Guild", JOptionPane.WARNING_MESSAGE);
            System.exit(-1);
        }
    }

    public String[] getPosts(String user) {
        stk.clear();
        File f = new File("users/" + user + "/post.txt");
        if (f.exists()) {
            String data = decrypt(f);
            Scanner in = new Scanner(data);
            stk.add(in.nextLine());
            while (in.hasNextLine()) {
                stk.add(in.nextLine());
            }
            return stk.toArray(new String[0]);
        }
        return null;
    }

    public boolean edit(String full, String gen, String dd, String mm, String yy, String addr) {
        File f = new File("users/" + UserSession.CurrentUser.UserID);
        try {
            try (FileOutputStream wt = new FileOutputStream(new File(f + "/Info.txt"))) {
                String toEncrypt = full + "\r\n" + gen + "\r\n" + mm + " " + dd + " " + yy + "\r\n" + addr;
                wt.write(encryptData(toEncrypt));
                wt.flush();
            }
            JOptionPane.showMessageDialog(null, "Your Profile Successfully Edited", "Edit Profile", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (HeadlessException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Edit Failed: " + ex.getMessage(), "Edit Profile", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        return false;
    }

    public boolean checkPass(String pw) {
        String data = decrypt(new File("users/" + UserSession.CurrentUser.UserID + "/pass.txt"));
        Scanner in = new Scanner(data);
        String pass = in.nextLine();
        return pass.equals(pw);
    }

    public boolean changePassword(String pw) {
        File f = new File("users/" + UserSession.CurrentUser.UserID);
        try {
            try (FileOutputStream wt = new FileOutputStream(new File(f + "/pass.txt"))) {
                wt.write(encryptData(pw));
                wt.flush();
            }
            JOptionPane.showMessageDialog(null, "Password Changed Successfully", "Change Password", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (HeadlessException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Operation failed to execute. System thrown an exception : " + ex.getMessage(), "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        return false;
    }

    public void saveFriends(String name, String friends) {
        File f = new File("users/" + name);
        try {
            try (FileOutputStream wt = new FileOutputStream(new File(f + "/friends.txt"))) {
                wt.write(encryptData(friends));
                wt.flush();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Operation failed to execute. System thrown an exception : " + ex.getMessage(), "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    public String[] getFriends(String user) {
        stk.clear();
        File f = new File("users/" + user + "/friends.txt");
        if (f.exists()) {
            String data = decrypt(f);
            Scanner in = new Scanner(data);
            stk.add(in.nextLine());
            while (in.hasNextLine()) {
                stk.add(in.nextLine());
            }
            return stk.toArray(new String[0]);
        }
        return null;
    }

    public void saveRequest(String name, String request) {
        File f = new File("users/" + name);
        try {
            try (FileOutputStream wt = new FileOutputStream(new File(f + "/request.txt"))) {
                wt.write(encryptData(request));
                wt.flush();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Operation failed to execute. System thrown an exception : " + ex.getMessage(), "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    public String[] getRequest(String user) {
        stk.clear();
        File f = new File("users/" + user + "/request.txt");
        if (f.exists()) {
            String data = decrypt(f);
            Scanner in = new Scanner(data);
            stk.add(in.nextLine());
            while (in.hasNextLine()) {
                stk.add(in.nextLine());
            }
            return stk.toArray(new String[0]);
        }
        return null;
    }

    public void saveAcceptedUsername(String user, String Acceptor) {
        stk.clear();
        File f = new File("users/" + user + "/friends.txt");
        String data = decrypt(f);
        Scanner in = new Scanner(data);
        stk.add(in.nextLine());
        while (in.hasNextLine()) {
            stk.add(in.nextLine());
        }
        try {
            try (FileOutputStream wt = new FileOutputStream(f)) {
                if (!stk.isEmpty()) {
                    while (!stk.isEmpty()) {
                        wt.write(encryptData(stk.pop() + "\n"));
                    }
                }
                wt.write(encryptData(Acceptor));
                wt.flush();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Operation failed to execute. System thrown an exception : " + ex.getMessage(), "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    public void RemoveFriendStatus(String user, String Remover) {
        stk.clear();
        File f = new File("users/" + user + "/friends.txt");
        String data = decrypt(f);
        Scanner in = new Scanner(data);
        stk.add(in.nextLine());
        while (in.hasNextLine()) {
            stk.add(in.nextLine());
        }
        try {
            try (FileOutputStream wt = new FileOutputStream(f)) {
                if (!stk.isEmpty()) {
                    while (!stk.isEmpty()) {
                        if (stk.peek().equals(Remover)) {
                            stk.pop();
                        } else {
                            wt.write(encryptData(stk.pop() + "\n"));
                        }
                    }
                }
                wt.flush();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Operation failed to execute. System thrown an exception : " + ex.getMessage(), "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    public void savePath(String user, String path) {
        File f = new File("users/" + user + "/ImagePath.txt");
        try {
            try (FileOutputStream wt = new FileOutputStream(f)) {
                wt.write(this.encryptData(path));
                wt.flush();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Operation failed to execute. System thrown an exception : " + ex.getMessage(), "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    public String getImagePath(String user) {
        File f = new File("users/" + user + "/ImagePath.txt");
        if (f.exists()) {
            Scanner in = new Scanner(this.decrypt(f));
            String path = in.nextLine();
            return path;
        }
        return null;
    }

    public boolean checkIfExists(String path) {
        File f = new File(path);
        return f.exists();
    }

    public void saveGlobalChatLog(String chatLog) {
        File f = new File("Logs/Global Chat Log");
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            try (FileOutputStream wt = new FileOutputStream(f + "/log.txt")) {
                wt.write(this.encryptData(chatLog));
                wt.flush();
            }
            JOptionPane.showMessageDialog(null, "Message Successfuly Sent", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Operation failed to execute. System thrown an exception : " + ex.getMessage(), "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    public String[] getGlobalChatLog() {
        stk.clear();
        File f = new File("Logs/Global Chat Log/log.txt");
        if (f.exists()) {
            Scanner in = new Scanner(this.decrypt(f));
            stk.add(in.nextLine());
            while (in.hasNextLine()) {
                stk.add(in.nextLine());
            }
        }
        return stk.toArray(new String[0]);
    }
}
