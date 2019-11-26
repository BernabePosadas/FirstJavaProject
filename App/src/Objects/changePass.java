package Objects;


import Objects.IOFileStream;
import Objects.UserSession;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class changePass extends JDialog implements ActionListener {

    boolean isOkToChange = true;
    boolean process1 = false;
    JLabel lblPrePass = new JLabel("Previous Password:");
    JLabel lblNew = new JLabel("New Password (6 Characters min):");
    JLabel lblConf = new JLabel("Confirm Password:");
    JPasswordField pre = new JPasswordField();
    JPasswordField newPass = new JPasswordField();
    JPasswordField conf = new JPasswordField();
    JButton change = new JButton("Change");
    JButton ret = new JButton("Return");
    ImageIcon icon = new ImageIcon("icon.png");

    public changePass() {
        this.setModal(true);
        this.setTitle("Change Password");
        this.setSize(325, 250);
        this.setLocation(460, 225);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        content();
        setIconImage(this.icon.getImage());
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ret) {
            dispose();
        } else if (e.getSource() == change) {
            IOFileStream io = new IOFileStream();
            if (pre.getText().trim().equals("") || newPass.getText().trim().equals("") || conf.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Fill All The Information Needed", "Change Password", JOptionPane.INFORMATION_MESSAGE);
                pre.setText("");
                newPass.setText("");
                conf.setText("");
                isOkToChange = false;
            } else if (newPass.getText().length() < 6) {
                JOptionPane.showMessageDialog(null, "Password is Too Short, Please Enter A Password At Least 6 Characters", "Change Password", JOptionPane.ERROR_MESSAGE);
                pre.setText("");
                newPass.setText("");
                conf.setText("");
                isOkToChange = false;
            } else if (newPass.getText().equals(conf.getText()) == false) {
                JOptionPane.showMessageDialog(null, "Password Did Not Match At Confirm Password. Please Re-Enter Your Password", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
                pre.setText("");
                newPass.setText("");
                conf.setText("");
                isOkToChange = false;
            } else {
                process1 = true;
            }
            if (process1) {
                boolean checkPass = io.checkPass(pre.getText());
                if (checkPass) {
                    isOkToChange = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Previous Password Incorrect", "Change Password", JOptionPane.ERROR_MESSAGE);
                    pre.setText("");
                    newPass.setText("");
                    conf.setText("");
                    process1 = false;
                    isOkToChange = false;
                }
            }
            if (isOkToChange) {
                boolean getReply = io.changePassword(newPass.getText());
                if (getReply) {
                    dispose();
                }
            }
        }

    }

    void content() {
        Container con = getContentPane();
        con.setLayout(null);
        lblPrePass.setBounds(35, 10, 150, 20);
        pre.setBounds(35, 30, 250, 20);
        lblNew.setBounds(35, 60, 200, 20);
        newPass.setBounds(35, 80, 250, 20);
        lblConf.setBounds(35, 110, 150, 20);
        conf.setBounds(35, 130, 250, 20);
        change.setBounds(60, 170, 100, 20);
        ret.setBounds(160, 170, 100, 20);
        change.addActionListener(this);
        ret.addActionListener(this);

        con.add(lblPrePass);
        con.add(pre);
        con.add(lblNew);
        con.add(newPass);
        con.add(lblConf);
        con.add(conf);
        con.add(change);
        con.add(ret);
    }

}
