package Objects;


import Objects.Rectangles;
import Objects.LogInWin;
import Objects.IOFileStream;
import Objects.UserSession;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Register extends JFrame implements ActionListener {

    boolean register = true;
    boolean isLeapYear = false;
    String[] days = getDays(31);
    String[] month = {
        "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
    };
    Rectangles rect1 = new Rectangles();
    String[] year = getYears(117);
    ImageIcon background = new ImageIcon("RegisterBack.png");
    ImageIcon chokora = new ImageIcon("chocola.png");
    ImageIcon Speech = new ImageIcon("ChocolaSpeechBubble.png");
    ImageIcon regHandler = new ImageIcon("RegisterFormHandler.png");
    ImageIcon seal = new ImageIcon("Seal.png");
    JLabel RegFormBanner = new JLabel("Registration Form");
    JLabel RegSeal = new JLabel(seal);
    JLabel Handler = new JLabel(regHandler);
    JLabel back = new JLabel(background);
    JLabel chocola = new JLabel(chokora);
    JLabel SpeechBubble = new JLabel(Speech);
    JLabel name = new JLabel("Your Full Name:");
    JLabel username = new JLabel("Username:");
    JLabel pass = new JLabel("Password: (6 Characters Min)");
    JLabel confirm = new JLabel("Confirm Password");
    JLabel gen = new JLabel("Gender:");
    JLabel birth = new JLabel("Birthdate:");
    JLabel add = new JLabel("Address");
    JTextField nam = new JTextField();
    JTextField usern = new JTextField();
    JTextArea bachou = new JTextArea();
    JScrollPane scr = new JScrollPane(bachou);
    JPasswordField passw = new JPasswordField();
    JPasswordField conf = new JPasswordField();
    JRadioButton gen1 = new JRadioButton("Male");
    JRadioButton gen2 = new JRadioButton("Female");
    JComboBox dd = new JComboBox(days);
    JComboBox mm = new JComboBox(month);
    JComboBox yy = new JComboBox(year);
    JButton Reg = new JButton("Register");
    JButton ret = new JButton("Return to Login Page");
    ImageIcon icon = new ImageIcon("icon.png");
    ButtonGroup gend = new ButtonGroup();
    boolean process1 = false;

    public Register() {
        this.setTitle("Hunter's Guild: Registration Office");
        this.setSize(1100, 700);
        this.setLocation(150, 20);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        content();
        setIconImage(this.icon.getImage());
        setVisible(true);
    }

    public String[] getYears(int yr) {
        String[] years = new String[yr];
        for (int i = 0; i < 117; i++) {
            years[i] = 1900 + i + "";

        }
        return years;
    }

    public String[] getDays(int day) {
        String[] nichi = new String[day];
        for (int i = 0; i < day; i++) {
            nichi[i] = 1 + i + "";
        }
        return nichi;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Reg) {
            String fn = nam.getText();
            String[] fnCheck = fn.split(" ");
            File f = new File("users/" + usern.getText());
            if (nam.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Full Name Not Set. Please Enter Your Name", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
                register = false;
            } else if (usern.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Username Not Set. Please Enter Your Username", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
                register = false;
            } else if (f.exists()) {
                JOptionPane.showMessageDialog(null, "Username Is Already Taken. Please Enter A New Username", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
                usern.setText("");
                register = false;
            } else if (passw.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Password Not Set. Please Enter Your Password", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
                passw.setText("");
                conf.setText("");
                register = false;
            } else if (passw.getText().length() < 6) {
                JOptionPane.showMessageDialog(null, "Password is Too Short. Please Enter A Password At Least 6 Characters", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
                passw.setText("");
                conf.setText("");
                register = false;
            } else if (passw.getText().equals(conf.getText()) == false) {
                JOptionPane.showMessageDialog(null, "Password Did Not Match At Confirm Password, Please Re-Enter Your Password", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
                passw.setText("");
                conf.setText("");
                register = false;
            } else if (!gen1.isSelected() && !gen2.isSelected()) {
                JOptionPane.showMessageDialog(null, "Gender Not Se. Please Specify Your Gender", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
                register = false;
            } else if (bachou.getText().trim().equals("")) {
                if (JOptionPane.showConfirmDialog(null, "Address Not Set. Leave it unset?", "Hunter's Guild Registration Office", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    bachou.setText("(Not Set)");
                    register = true;
                    process1 = true;
                } else {
                    register = false;
                }
            } else {
                process1 = true;
            }
            if (process1) {
                if (fnCheck.length < 2 || (fnCheck[1].length() == 1 && fnCheck.length == 2)) {
                    if (JOptionPane.showConfirmDialog(null, "Is This Really Your Full Name? : \n" + nam.getText(), "Hunter's Guild Registration Office", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                        register = false;
                        process1 = false;
                    } else {
                        register = true;
                    }
                } else {
                    register = true;
                }
            }
            if (register) {
                String[] getFullName = nam.getText().split(" ");
                String Fullnam = "";
                for (int i = 0; i < getFullName.length; i++) {
                    Fullnam += getFullName[i].substring(0, 1).toUpperCase() + getFullName[i].substring(1).toLowerCase() + " ";
                }
                String a = "";
                if (gen1.isSelected()) {
                    a = "Male";

                } else if (gen2.isSelected()) {
                    a = "Female";
                }
                IOFileStream io = new IOFileStream();
                io.makeUser(usern.getText().substring(0, 1).toUpperCase() + usern.getText().substring(1).toLowerCase());
                boolean getReply = io.register(Fullnam, usern.getText().substring(0, 1).toUpperCase() + usern.getText().substring(1).toLowerCase(), passw.getText(), a, (String) dd.getSelectedItem(), (String) mm.getSelectedItem(), (String) yy.getSelectedItem(), bachou.getText());
                if (getReply) {
                    dispose();
                }
            }
        } else if (e.getSource() == ret) {
            new LogInWin();
            this.dispose();
        } else if (e.getSource() == mm) {
            int selected = dd.getSelectedIndex();
            if (mm.getSelectedItem().toString().equals("January") || mm.getSelectedItem().toString().equals("March") || mm.getSelectedItem().toString().equals("May") || mm.getSelectedItem().toString().equals("July") || mm.getSelectedItem().toString().equals("August") || mm.getSelectedItem().toString().equals("October") || mm.getSelectedItem().toString().equals("December")) {
                dd.removeAllItems();
                for (int i = 1; i <= 31; i++) {
                    dd.addItem(i + "");
                }
                dd.setSelectedIndex(selected);
            } else if (mm.getSelectedItem().toString().equals("February")) {
                dd.removeAllItems();
                checkLeapYear();
                if (isLeapYear) {
                    for (int i = 1; i <= 29; i++) {
                        dd.addItem(i + "");
                    }
                    if (selected > 28) {
                        dd.setSelectedIndex(0);
                    } else {
                        dd.setSelectedIndex(selected);
                    }
                } else {
                    for (int i = 1; i <= 28; i++) {
                        dd.addItem(i + "");
                    }
                    if (selected > 27) {
                        dd.setSelectedIndex(0);
                    } else {
                        dd.setSelectedIndex(selected);
                    }
                }
            } else if (mm.getSelectedItem().toString().equals("April") || mm.getSelectedItem().toString().equals("June") || mm.getSelectedItem().toString().equals("September") || mm.getSelectedItem().toString().equals("November")) {
                dd.removeAllItems();
                for (int i = 1; i <= 30; i++) {
                    dd.addItem(i + "");
                }
                if (selected > 29) {
                    dd.setSelectedIndex(0);
                } else {
                    dd.setSelectedIndex(selected);
                }
            }
        } else if (e.getSource() == yy) {
            int selected = dd.getSelectedIndex();
            if (mm.getSelectedItem().toString().equals("February")) {
                dd.removeAllItems();
                checkLeapYear();
                if (isLeapYear) {
                    for (int i = 1; i <= 29; i++) {
                        dd.addItem(i + "");
                    }
                    if (selected > 28) {
                        dd.setSelectedIndex(0);
                    } else {
                        dd.setSelectedIndex(selected);
                    }
                } else {
                    for (int i = 1; i <= 28; i++) {
                        dd.addItem(i + "");
                    }
                    if (selected > 27) {
                        dd.setSelectedIndex(0);
                    } else {
                        dd.setSelectedIndex(selected);
                    }
                }
            }
        }
    }
    // wha? why i didnt just return the query result? 
    void checkLeapYear() {
        if (Integer.parseInt(yy.getSelectedItem().toString()) % 4 != 0) {
            isLeapYear = false;
        } else if (Integer.parseInt(yy.getSelectedItem().toString()) % 100 != 0) {
            isLeapYear = true;
        } else if (Integer.parseInt(yy.getSelectedItem().toString()) % 400 != 0) {
            isLeapYear = false;
        } else {
            isLeapYear = true;
        }
    }

    void content() {
        Container con = getContentPane();
        con.setLayout(null);
        Font RegBannerFont = new Font("Comic Sans MS", Font.PLAIN, 17);
        back.setBounds(0, 0, this.getWidth(), this.getHeight());
        yy.setSelectedIndex(116);
        name.setBounds(750, 170, 100, 20);
        nam.setBounds(750, 190, 250, 20);
        username.setBounds(750, 210, 100, 20);
        usern.setBounds(750, 230, 250, 20);
        pass.setBounds(750, 250, 250, 20);
        passw.setBounds(750, 270, 250, 20);
        confirm.setBounds(750, 290, 150, 20);
        conf.setBounds(750, 310, 250, 20);
        gen.setBounds(750, 330, 100, 20);
        gen1.setBounds(750, 350, 70, 20);
        gen2.setBounds(830, 350, 70, 20);
        birth.setBounds(750, 370, 100, 20);
        dd.setBounds(750, 390, 70, 20);
        mm.setBounds(820, 390, 90, 20);
        yy.setBounds(910, 390, 80, 20);
        add.setBounds(750, 410, 100, 20);
        bachou.setBounds(750, 430, 250, 50);
        scr.setBounds(750, 430, 250, 50);
        Reg.setBounds(750, 490, 250, 30);
        ret.setBounds(750, 520, 250, 30);
        chocola.setBounds(0, 0, 550, 700);
        SpeechBubble.setBounds(300, 30, 400, 200);
        Handler.setBounds(700, 30, 350, 630);
        RegSeal.setBounds(750, 120, 50, 50);
        RegFormBanner.setBounds(820, 120, 160, 40);
        RegFormBanner.setFont(RegBannerFont);

        mm.addActionListener(this);
        yy.addActionListener(this);
        Reg.addActionListener(this);
        ret.addActionListener(this);

        con.add(name);
        con.add(nam);
        con.add(username);
        con.add(usern);
        con.add(pass);
        con.add(passw);
        con.add(confirm);
        con.add(conf);
        con.add(gen);
        gend.add(gen1);
        gend.add(gen2);
        con.add(gen1);
        con.add(gen2);
        con.add(birth);
        con.add(dd);
        con.add(mm);
        con.add(yy);
        con.add(add);
        con.add(scr);
        con.add(Reg);
        con.add(ret);
        con.add(RegFormBanner);
        con.add(RegSeal);
        con.add(Handler);
        con.add(SpeechBubble);
        con.add(chocola);
        con.add(back);
    }

}
