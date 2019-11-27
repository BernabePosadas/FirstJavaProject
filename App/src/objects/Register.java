package objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Register extends JFrame implements ActionListener {

    private final DateBuilder date_builder = new DateBuilder();
    private final String[] days = date_builder.getDays(31);
    private final String[] month = {
        "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
    };
    private final String[] year = date_builder.getYears(117);
    private final ImageIcon background = new ImageIcon("RegisterBack.png");
    private final ImageIcon chokora = new ImageIcon("chocola.png");
    private final ImageIcon Speech = new ImageIcon("ChocolaSpeechBubble.png");
    private final ImageIcon regHandler = new ImageIcon("RegisterFormHandler.png");
    private final ImageIcon seal = new ImageIcon("Seal.png");
    private final JLabel RegFormBanner = new JLabel("Registration Form");
    private final JLabel RegSeal = new JLabel(seal);
    private final JLabel Handler = new JLabel(regHandler);
    private final JLabel back = new JLabel(background);
    private final JLabel chocola = new JLabel(chokora);
    private final JLabel SpeechBubble = new JLabel(Speech);
    private final JLabel name = new JLabel("Your Full Name:");
    private final JLabel username = new JLabel("Username:");
    private final JLabel pass = new JLabel("Password: (6 Characters Min)");
    private final JLabel confirm = new JLabel("Confirm Password");
    private final JLabel gen = new JLabel("Gender:");
    private final JLabel birth = new JLabel("Birthdate:");
    private final JLabel add = new JLabel("Address");
    private final JTextField nam = new JTextField();
    private final JTextField usern = new JTextField();
    private final JTextArea bachou = new JTextArea();
    private final JScrollPane scr = new JScrollPane(bachou);
    private final JPasswordField passw = new JPasswordField();
    private final JPasswordField conf = new JPasswordField();
    private final JRadioButton gen1 = new JRadioButton("Male");
    private final JRadioButton gen2 = new JRadioButton("Female");
    private final JComboBox dd = new JComboBox(days);
    private final JComboBox mm = new JComboBox(month);
    private final JComboBox yy = new JComboBox(year);
    private final JButton Reg = new JButton("Register");
    private final JButton ret = new JButton("Return to Login Page");
    private final ImageIcon icon = new ImageIcon("icon.png");
    private final ButtonGroup gend = new ButtonGroup();

    public Register() {
        this.setTitle("Hunter's Guild: Registration Office");
        this.setSize(1100, 700);
        this.setLocation(150, 20);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.content();
        setIconImage(this.icon.getImage());
        setVisible(true);
    }

    public void renderMonths() {
        int selected = dd.getSelectedIndex();
        dd.removeAllItems();
        switch (mm.getSelectedItem().toString()) {
            case "January":
            case "March":
            case "May":
            case "July":
            case "August":
            case "October":
            case "December":
                for (int i = 1; i <= 31; i++) {
                    dd.addItem(i + "");
                }
                dd.setSelectedIndex(selected);
                break;
            case "February":
                this.renderFebruary();
                break;
            case "April":
            case "June":
            case "September":
            case "November":
                for (int i = 1; i <= 30; i++) {
                    dd.addItem(i + "");
                }
                if (selected > 29) {
                    dd.setSelectedIndex(0);
                } else {
                    dd.setSelectedIndex(selected);
                }
                break;
            default:
                break;
        }
    }

    public void renderFebruary() {
        int selected = dd.getSelectedIndex();
        if (mm.getSelectedItem().toString().equals("February")) {
            dd.removeAllItems();
            if (isLeapYear()) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Reg) {
            User user = new User(usern.getText());
            String[] getFullName = nam.getText().split(" ");
            String Fullnam = "";
            for (String fullName : getFullName) {
                Fullnam += fullName.substring(0, 1).toUpperCase() + fullName.substring(1).toLowerCase() + " ";
            }
            user.FullName = Fullnam;
            user.BirthDay = mm.getSelectedItem() + " " + dd.getSelectedItem() + " " + yy.getSelectedItem();
            user.Address = bachou.getText();
            String a;
            if (gen1.isSelected()) {
                a = "Male";

            } else if (gen2.isSelected()) {
                a = "Female";
            } else {
                a = "";
            }
            user.Gender = a;
            if (user.ValidateUserObject()) {
                File f = new File("users/" + user.UserID);
                if (!f.exists()) {
                    if (this.validatePassword()) {
                        IOFileStream io = new IOFileStream();
                        io.makeUser(usern.getText().substring(0, 1).toUpperCase() + usern.getText().substring(1).toLowerCase());
                        boolean getReply = io.register(user, passw.getText());
                        if (getReply) {
                            new ProfileViewer(user);
                            this.dispose();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry but username specified is already taken. Please specify a different username", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == ret) {
            new LogInWin();
            this.dispose();
        } else if (e.getSource() == mm) {
            this.renderMonths();
        } else if (e.getSource() == yy) {

        }
    }

    private boolean validatePassword() {
        if (passw.getText().equals(conf.getText())) {
            if (passw.getText().length() < 6) {
                JOptionPane.showMessageDialog(null, "Password cannot be less than 6 characters", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Password Does not match with confirmation textbox", "Hunter's Guild Registration Office", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean isLeapYear() {
        if (Integer.parseInt(yy.getSelectedItem().toString()) % 4 != 0) {
            return false;
        } else if (Integer.parseInt(yy.getSelectedItem().toString()) % 100 != 0) {
            return true;
        } else {
            return (Integer.parseInt(yy.getSelectedItem().toString()) % 400 != 0);
        }
    }

    private void content() {
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
