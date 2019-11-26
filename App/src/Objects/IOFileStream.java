package Objects;

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class IOFileStream {

    LinkedList<String> stk = new LinkedList();
    String KeyString = "Hunter's Guild";
    String IVString = "Java Project";

    public IOFileStream() {

    }

    public boolean logIn(String user, String pw) {
        try {

            File f = new File("users/" + user);
            if (f.exists() && !user.trim().equals("")) {
                String data = decrypt(new FileInputStream(f + "/pass.txt"), new File("users/" + user + "/pass.txt"));
                JOptionPane.showMessageDialog(null, data, "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
                Scanner in = new Scanner(data);
                String pass = in.nextLine();
                if (pass.equals(pw)) {
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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Log In Failed. Exeption Encountered: " + ex.getMessage(), "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
        }
        return false;

    }

    public void makeUser(String usern) {
        File f = new File("users/" + usern);
        f.mkdirs();
    }

    public boolean register(String name, String usern, String pass, String gen, String dd, String mm, String yy, String add) {
        try {

            FileOutputStream wt = new FileOutputStream(new File("users/" + usern + "/Info.txt"));
            String toEncrypt = name + "\r\n" + gen + "\r\n" + mm + " " + dd + " " + yy + "\r\n" + add;

            wt.write(encryptData(toEncrypt));
            wt.flush();
            wt.close();
            FileOutputStream pw = new FileOutputStream(new File("users/" + usern + "/pass.txt"));
            pw.write(encryptData(pass));
            pw.flush();
            pw.close();
            JOptionPane.showMessageDialog(null, "You Successfully Registered", "Hunter's Guild Registration Office", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "We Capitalized The First Letter Of Your Username And The Rest Are Lower Case For Us To Identify. Do Not Worry, Username Input Is Not Case Sensitive", "Hunter's Guild Registration Office", JOptionPane.INFORMATION_MESSAGE);
            new LogInWin();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Registration Failed, Your Username Might Have Any Of The Folowing: \n/  :  |  *  ?  <  >  \" ", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    private byte[] encryptData(String Message) {
        byte[] data = null;
        try {

            MessageDigest mgds = MessageDigest.getInstance("MD5");
            byte[] key = mgds.digest(KeyString.getBytes());
            byte[] iv = mgds.digest(IVString.getBytes());
            Cipher cip = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(iv);
            SecretKeySpec keyParam = new SecretKeySpec(key, "AES");
            cip.init(Cipher.ENCRYPT_MODE, keyParam, ivParam);
            data = cip.doFinal(Message.getBytes());
        } catch (Exception ex) {

        }
        return data;
    }

    private String decrypt(FileInputStream inStr, File f) {
        String data = "";
        try {
            FileInputStream inputStream = new FileInputStream(f);
            MessageDigest mgds = MessageDigest.getInstance("MD5");
            byte[] key = mgds.digest(KeyString.getBytes());
            byte[] iv = mgds.digest(IVString.getBytes());
            Cipher cip = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(iv);
            SecretKeySpec keyParam = new SecretKeySpec(key, "AES");
            cip.init(Cipher.DECRYPT_MODE, keyParam, ivParam);
            int size = (int) f.length();
            byte[] dataHold = new byte[size];
            inputStream.read(dataHold);
            data = new String(cip.doFinal(dataHold));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
        }
        return data;
    }

    public String[] getInfo(String name) {
        File f = new File("users/" + name);
        try {
            String data = decrypt(new FileInputStream(f + "/Info.txt"), new File("users/" + name + "/Info.txt"));

            Scanner in = new Scanner(data);
            String Info[] = new String[4];
            for (int i = 0; i < Info.length; i++) {
                Info[i] = in.nextLine();
            }
            return Info;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Load Failed, File \"Info.txt\" Is Missing Or Failed To Load", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void savePost(String user, String post) {
        File f = new File("users/" + user);
        try {
            FileOutputStream wt = new FileOutputStream(new File(f + "/post.txt"));
            wt.write(encryptData(post));
            wt.flush();
            wt.close();
            JOptionPane.showMessageDialog(null, "Posted Successfully", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Post Failed To Save: " + ex.getMessage(), "Hunter's Guild", JOptionPane.WARNING_MESSAGE);
        }
    }

    public String[] getPosts(String user) {
        File f = new File("users/" + user);
        try {
            String data = decrypt(new FileInputStream(f + "/post.txt"), new File("users/" + user + "/post.txt"));
            Scanner in = new Scanner(data);
            stk.add(in.nextLine());
            while (in.hasNextLine()) {
                stk.add(in.nextLine());
            }
            String[] Posts = new String[stk.size()];
            for (int i = 0; i < Posts.length; i++) {
                Posts[i] = stk.pop();
            }
            return Posts;
        } catch (Exception ex) {

        }
        return null;
    }

    public boolean edit(String full, String gen, String dd, String mm, String yy, String addr) {
        File f = new File("users/" + UserSession.CurrentUser.UserID);
        try {
            FileOutputStream wt = new FileOutputStream(new File(f + "/Info.txt"));
            String toEncrypt = full + "\r\n" + gen + "\r\n" + mm + " " + dd + " " + yy + "\r\n" + addr;
            wt.write(encryptData(toEncrypt));
            wt.flush();
            wt.close();
            JOptionPane.showMessageDialog(null, "Your Profile Successfully Edited", "Edit Profile", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Edit Failed: " + ex.getMessage(), "Edit Profile", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean checkPass(String pw) {
        File f = new File("users/" + UserSession.CurrentUser.UserID);
        try {
            String data = decrypt(new FileInputStream(f + "/pass.txt"), new File("users/" + UserSession.CurrentUser.UserID + "/pass.txt"));
            Scanner in = new Scanner(data);
            String pass = in.nextLine();
            if (pass.equals(pw)) {
                return true;
            }
        } catch (Exception ex) {
            
        }
        return false;
    }

    public boolean changePassword(String pw) {
        File f = new File("users/" + UserSession.CurrentUser.UserID);
        try {
            FileOutputStream wt = new FileOutputStream(new File(f + "/pass.txt"));
            wt.write(encryptData(pw));
            wt.flush();
            wt.close();
            JOptionPane.showMessageDialog(null, "Password Changed Successfully", "Change Password", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Password Saving Failed: " + ex.getMessage(), "Change Password", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public void saveFriends(String name, String friends) {
        File f = new File("users/" + name);
        try {
            FileOutputStream wt = new FileOutputStream(new File(f + "/friends.txt"));
            wt.write(encryptData(friends));
            wt.flush();
            wt.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Saving Friends Failed: " + ex.getMessage(), "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String[] getFriends(String user) {
        File f = new File("users/" + user);
        try {
            String data = decrypt(new FileInputStream(f + "/friends.txt"), new File("users/" + user + "/friends.txt"));
            Scanner in = new Scanner(data);
            stk.add(in.nextLine());
            while (in.hasNextLine()) {
                stk.add(in.nextLine());
            }
            String[] Friends = new String[stk.size()];
            for (int i = 0; i < Friends.length; i++) {
                Friends[i] = stk.pop();
            }
            return Friends;
        } catch (Exception ex) {

        }
        return null;
    }

    public void saveRequest(String name, String request) {
        File f = new File("users/" + name);
        try {
            FileOutputStream wt = new FileOutputStream(new File(f + "/request.txt"));
            wt.write(encryptData(request));
            wt.flush();
            wt.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Saving Request Failed: " + ex.getMessage(), "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String[] getRequest(String user) {
        File f = new File("users/" + user);
        try {
            String data = decrypt(new FileInputStream(f + "/request.txt"), new File("users/" + user + "/request.txt"));
            Scanner in = new Scanner(data);
            stk.add(in.nextLine());
            while (in.hasNextLine()) {
                stk.add(in.nextLine());
            }
            String[] request = new String[stk.size()];
            for (int i = 0; i < request.length; i++) {
                request[i] = stk.pop();
            }
            return request;
        } catch (Exception ex) {

        }
        return null;
    }

    public void saveAcceptedUsername(String user, String Acceptor) {
        File f = new File("users/" + user);
        try {
            String data = decrypt(new FileInputStream(f + "/friends.txt"), new File("users/" + user + "/friends.txt"));
            Scanner in = new Scanner(data);
            stk.add(in.nextLine());
            while (in.hasNextLine()) {
                stk.add(in.nextLine());
            }
        } catch (Exception ex) {

        }
        try {
            FileOutputStream wt = new FileOutputStream(new File(f + "/friends.txt"));
            if (stk.size() != 0) {
                while (stk.size() != 0) {
                    wt.write(encryptData(stk.pop() + "\n"));
                }
            }
            wt.write(encryptData(Acceptor));
            wt.flush();
            wt.close();
        } catch (Exception ex) {

        }
    }

    public void RemoveFriendStatus(String user, String Remover) {
        File f = new File("users/" + user);
        try {
            String data = decrypt(new FileInputStream(f + "/friends.txt"), new File("users/" + user + "/friends.txt"));
            Scanner in = new Scanner(data);
            stk.add(in.nextLine());
            while (in.hasNextLine()) {
                stk.add(in.nextLine());
            }
        } catch (Exception ex) {

        }
        try {
            FileOutputStream wt = new FileOutputStream(new File(f + "/friends.txt"));
            if (stk.size() != 0) {
                while (stk.size() != 0) {
                    if (stk.peek().equals(Remover)) {
                        stk.pop();
                    } else {
                        wt.write(encryptData(stk.pop() + "\n"));
                    }
                }
            }
            wt.flush();
            wt.close();
        } catch (Exception ex) {

        }
    }

    public void savePath(String user, String path) {
        File f = new File("users/" + user);
        try {
            FileOutputStream wt = new FileOutputStream(new File(f + "/ImagePath.txt"));
            wt.write(encryptData(path));
            wt.flush();
            wt.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Saving Request Failed: " + ex.getMessage(), "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getImagePath(String user) {
        File f = new File("users/" + user);
        try {
            Scanner in = new Scanner(new FileInputStream(f + "/ImagePath.txt"));
            String path = in.nextLine();
            return path;
        } catch (Exception ex) {

        }
        return null;
    }

    public boolean checkIfExists(String path) {
        File f = new File(path);
        if (f.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public void saveGlobalChatLog(String chatLog) {
        File f = new File("Logs/Global Chat Log");
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            FileWriter wt = new FileWriter(f + "/log.txt");
            wt.write(chatLog);
            wt.flush();
            wt.close();
            JOptionPane.showMessageDialog(null, "Message Successfuly Sent", "Hunter's Guild", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Sending Failed", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String[] getGlobalChatLog() {
        File f = new File("Logs/Global Chat Log");
        try {
            Scanner in = new Scanner(new FileInputStream(f + "/log.txt"));
            stk.add(in.nextLine());
            while (in.hasNextLine()) {
                stk.add(in.nextLine());
            }
            String log[] = new String[stk.size()];
            for (int i = 0; i < log.length; i++) {
                log[i] = stk.pop();
            }
            return log;
        } catch (Exception ex) {

        }
        return null;
    }
}
