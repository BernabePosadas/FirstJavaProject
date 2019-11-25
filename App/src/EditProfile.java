
import Objects.ProgramUser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditProfile extends JDialog implements ActionListener {

    boolean isOkToSave = true;
    boolean process1 = false;
    boolean isLeapYear = false;
    String[] days = getDays(31);
    String[] month = {
        "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
    };
    String[] year = getYears(117);
    ImageIcon proPic = new ImageIcon();
    JLabel pic = new JLabel(proPic);
    JLabel lblFullName = new JLabel("FullName:");
    JLabel lblGender = new JLabel("Gender:");
    JLabel lblBDate = new JLabel("Birth Date:");
    JLabel lblAdd = new JLabel("Address:");
    JLabel lblPass = new JLabel("Password: ");
    JComboBox dd = new JComboBox(days);
    JComboBox mm = new JComboBox(month);
    JComboBox yy = new JComboBox(year);
    JTextField fullName = new JTextField();
    JTextField addr = new JTextField();
    JRadioButton gen1 = new JRadioButton("Male");
    JRadioButton gen2 = new JRadioButton("Female");
    JButton changePass = new JButton("Change Password");
    JButton save = new JButton("Save");
    JButton ret = new JButton("Return");
    JButton changePicture = new JButton("Change Picture");
    ButtonGroup genGrp = new ButtonGroup();

    public EditProfile() {
        this.setModal(true);
        this.setTitle("Edit Profile");
        this.setSize(580, 300);
        this.setLocation(350, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        content();
        getPreviousInfo();
        setIconImage(ProgramUser.icon.getImage());
        setVisible(true);
    }

    void getPreviousInfo() {
        IOFileStream io = new IOFileStream();
        String get = ProgramUser.CurrentUser;
        String[] Info = io.getInfo(get);
        String path = io.getImagePath(get);
        if (Info == null) {
            JOptionPane.showMessageDialog(null, "Error: getInfo() Returned Null", "Edit Profile", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } else {
            boolean failedToLoad = false;
            String fn = Info[0];
            String gen = Info[1];
            String bday = Info[2];
            String address = Info[3];
            fullName.setText(fn);
            if (gen.trim().equalsIgnoreCase("Male")) {
                gen1.setSelected(true);
            } else if (gen.trim().equalsIgnoreCase("Female")) {
                gen2.setSelected(true);
            }
            String[] getBDate = bday.split(" ");
            int day = Integer.parseInt(getBDate[1]);
            int years = Integer.parseInt(getBDate[2]);
            if (getBDate[0].equalsIgnoreCase("January")) {
                mm.setSelectedIndex(0);
            } else if (getBDate[0].equalsIgnoreCase("February")) {
                mm.setSelectedIndex(1);
            } else if (getBDate[0].equalsIgnoreCase("March")) {
                mm.setSelectedIndex(2);
            } else if (getBDate[0].equalsIgnoreCase("April")) {
                mm.setSelectedIndex(3);
            } else if (getBDate[0].equalsIgnoreCase("May")) {
                mm.setSelectedIndex(4);
            } else if (getBDate[0].equalsIgnoreCase("June")) {
                mm.setSelectedIndex(5);
            } else if (getBDate[0].equalsIgnoreCase("July")) {
                mm.setSelectedIndex(6);
            } else if (getBDate[0].equalsIgnoreCase("August")) {
                mm.setSelectedIndex(7);
            } else if (getBDate[0].equalsIgnoreCase("September")) {
                mm.setSelectedIndex(8);
            } else if (getBDate[0].equalsIgnoreCase("October")) {
                mm.setSelectedIndex(9);
            } else if (getBDate[0].equalsIgnoreCase("November")) {
                mm.setSelectedIndex(10);
            } else if (getBDate[0].equalsIgnoreCase("December")) {
                mm.setSelectedIndex(11);
            }
            dd.setSelectedIndex(day - 1);
            yy.setSelectedIndex(years - 1900);
            addr.setText(address);
            if (path == null) {
                proPic = new ImageIcon("DefaultProfilePic.png");
            } else {
                if (io.checkIfExists(path) == false) {
                    JOptionPane.showMessageDialog(null, "Image Failed To Load Or Moved To Another Location", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
                    failedToLoad = true;
                } else {
                    try {
                        proPic = new ImageIcon(path);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Image Failed To Load Or Moved To Another Location", "Hunter's Guild", JOptionPane.ERROR_MESSAGE);
                        failedToLoad = true;
                    }
                }
            }
            if (failedToLoad) {
                proPic = new ImageIcon("DefaultProfilePic.png");
            }
            pic.setIcon(proPic);
            pic.setBounds(40, 40, 100, 100);
            getContentPane().add(pic);
        }

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
        if (e.getSource() == mm) {
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
        } else if (e.getSource() == ret) {
            dispose();
        } else if (e.getSource() == save) {
            String fn = fullName.getText();
            String[] fnCheck = fn.split(" ");
            if (fullName.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Full Name Is Empty. Please Enter Your Name", "Edit Profile", JOptionPane.ERROR_MESSAGE);
                isOkToSave = false;
            } else if (!gen1.isSelected() && !gen2.isSelected()) {
                JOptionPane.showMessageDialog(null, "Gender Not Set. Please Specify Your Gender", "Edit Profile", JOptionPane.ERROR_MESSAGE);
                isOkToSave = false;
            } else if (addr.getText().trim().equals("")) {
                if (JOptionPane.showConfirmDialog(null, "Address Not Set. Leave it unset?", "Edit Profile", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    addr.setText("(Not Set)");
                    isOkToSave = true;
                    process1 = true;
                } else {
                    isOkToSave = false;
                }
            } else {
                process1 = true;
            }
            if (process1) {
                if (fnCheck.length < 2 || (fnCheck[1].length() == 1 && fnCheck.length == 2)) {
                    if (JOptionPane.showConfirmDialog(null, "Is This Really Your Full Name? : \n" + fullName.getText(), "Edit Profile", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                        isOkToSave = false;
                        process1 = false;
                    } else {
                        isOkToSave = true;
                    }
                } else {
                    isOkToSave = true;
                }
            }
            if (isOkToSave) {
                String[] getFullName = fullName.getText().split(" ");
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
                boolean getReply = io.edit(Fullnam, a, (String) dd.getSelectedItem(), (String) mm.getSelectedItem(), (String) yy.getSelectedItem(), addr.getText());
                if (getReply) {
                    dispose();
                }
            }
        } else if (e.getSource() == changePass) {
            new changePass();
        } else if (e.getSource() == changePicture) {
            boolean done = false;
            String filePath = "";
            while (!done) {
                filePath = JOptionPane.showInputDialog(null, "Enter the path of the image file. file must be in .jpg, .png, .gif extension \nExample C:\\Users\\onkyo\\Documents\\shigure.png \n If the picture does not load by 1min, it means it failed to get the image\nUse 100x100pixels picture for best results \n");
                if (filePath.equals("")) {
                    JOptionPane.showMessageDialog(null, "Operation Failed: Inputbox is Empty", "Edit Profile", JOptionPane.ERROR_MESSAGE);
                    done = false;
                } else {
                    done = true;
                }
            }
            IOFileStream io = new IOFileStream();
            boolean checkFile = io.checkIfExists(filePath);
            if (checkFile) {
                io.savePath(ProgramUser.CurrentUser, filePath);
                JOptionPane.showMessageDialog(null, "Profile Picture Changed Successfully and Saved", "Edit Profile", JOptionPane.INFORMATION_MESSAGE);
                getPreviousInfo();
            } else {
                JOptionPane.showMessageDialog(null, "File Not Found", "File Checker", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // wha? why i didn't just return the result?
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
        lblFullName.setBounds(250, 10, 70, 20);
        fullName.setBounds(330, 10, 200, 20);
        lblGender.setBounds(250, 40, 100, 20);
        gen1.setBounds(250, 60, 70, 20);
        gen2.setBounds(330, 60, 70, 20);
        lblPass.setBounds(250, 100, 70, 20);
        changePass.setBounds(330, 100, 200, 20);
        lblBDate.setBounds(250, 130, 70, 20);
        dd.setBounds(250, 150, 90, 20);
        mm.setBounds(340, 150, 90, 20);
        yy.setBounds(430, 150, 100, 20);
        lblAdd.setBounds(250, 180, 70, 20);
        addr.setBounds(250, 200, 280, 20);
        save.setBounds(290, 230, 100, 20);
        ret.setBounds(390, 230, 100, 20);
        changePicture.setBounds(35, 160, 130, 20);

        mm.addActionListener(this);
        yy.addActionListener(this);
        ret.addActionListener(this);
        save.addActionListener(this);
        changePass.addActionListener(this);
        changePicture.addActionListener(this);

        con.add(lblFullName);
        con.add(fullName);
        con.add(lblGender);
        genGrp.add(gen1);
        genGrp.add(gen2);
        con.add(gen1);
        con.add(gen2);
        con.add(lblPass);
        con.add(changePass);
        con.add(lblBDate);
        con.add(dd);
        con.add(mm);
        con.add(yy);
        con.add(lblAdd);
        con.add(addr);
        con.add(save);
        con.add(ret);
        con.add(changePicture);
    }

}
